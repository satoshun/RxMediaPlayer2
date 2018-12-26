package com.github.satoshun.media2.coroutines

import androidx.media2.MediaPlayer
import com.github.satoshun.media2.MediaPlayerEvent
import com.github.satoshun.media2.MediaPlayerListener
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import java.util.concurrent.Executor
import java.util.concurrent.Executors

fun MediaPlayer.events(
  capacity: Int = Channel.RENDEZVOUS,
  executor: Executor = Executors.newSingleThreadExecutor()
): Channel<MediaPlayerEvent> {
  val channel = Channel<MediaPlayerEvent>(capacity)
  val listener = MediaListener(channel)
  registerPlayerCallback(executor, listener)
  channel.invokeOnClose {
    unregisterPlayerCallback(listener)
  }
  return channel
}

private class MediaListener(
  private val channel: SendChannel<MediaPlayerEvent>
) : MediaPlayerListener() {
  override fun invoke(event: MediaPlayerEvent) {
    if (channel.isClosedForSend) return
    channel.offer(event)
  }
}
