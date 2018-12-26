package com.github.satoshun.media2.coroutines

import androidx.media2.SessionPlayer
import com.github.satoshun.media2.SessionPlayerEvent
import com.github.satoshun.media2.SessionPlayerListener
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import java.util.concurrent.Executor
import java.util.concurrent.Executors

fun SessionPlayer.events(
  capacity: Int = Channel.RENDEZVOUS,
  executor: Executor = Executors.newSingleThreadExecutor()
): Channel<SessionPlayerEvent> {
  val channel = Channel<SessionPlayerEvent>(capacity)
  val listener = Listener(channel)
  registerPlayerCallback(executor, listener)
  channel.invokeOnClose {
    unregisterPlayerCallback(listener)
  }
  return channel
}

private class Listener(
  private val channel: SendChannel<SessionPlayerEvent>
) : SessionPlayerListener() {
  override fun invoke(event: SessionPlayerEvent) {
    if (channel.isClosedForSend) return
    channel.offer(event)
  }
}
