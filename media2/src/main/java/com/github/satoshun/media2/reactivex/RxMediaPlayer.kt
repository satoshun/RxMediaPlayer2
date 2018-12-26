package com.github.satoshun.media2.reactivex

import androidx.annotation.CheckResult
import androidx.media2.MediaPlayer
import com.github.satoshun.media2.MediaPlayerEvent
import io.reactivex.Observable
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * event stream of [MediaPlayer.PlayerCallback]
 */
@CheckResult
fun MediaPlayer.events(
  executor: Executor = Executors.newSingleThreadExecutor()
): Observable<MediaPlayerEvent> {
  return MediaPlayerObservable(this, executor)
}
