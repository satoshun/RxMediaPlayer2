package com.github.satoshun.media2.coroutines

import androidx.media2.SessionPlayer
import com.github.satoshun.media2.SessionPlayerEvent
import kotlinx.coroutines.channels.Channel
import java.util.concurrent.Executor
import java.util.concurrent.Executors

fun SessionPlayer.events(
  capacity: Int = Channel.RENDEZVOUS,
  executor: Executor = Executors.newSingleThreadExecutor()
): Channel<SessionPlayerEvent> {
  val channel = Channel<SessionPlayerEvent>(capacity)
  return channel
}
