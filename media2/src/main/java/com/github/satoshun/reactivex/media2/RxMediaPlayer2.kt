package com.github.satoshun.reactivex.media2

import androidx.annotation.CheckResult
import androidx.media2.MediaPlayer2
import io.reactivex.Observable
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * event stream of [MediaPlayer2.EventCallback]
 */
@CheckResult
fun MediaPlayer2.events(
  executor: Executor = Executors.newSingleThreadExecutor()
): Observable<MediaPlayer2Event> {
  return MediaPlayer2Observable(this, executor)
}
