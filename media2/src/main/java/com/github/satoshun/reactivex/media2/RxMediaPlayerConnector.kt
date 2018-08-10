package com.github.satoshun.reactivex.media2

import androidx.annotation.CheckResult
import androidx.media2.MediaPlayerConnector
import io.reactivex.Observable
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@CheckResult
fun MediaPlayerConnector.events(
  executor: Executor = Executors.newSingleThreadExecutor()
): Observable<MediaPlayerConnectorEvent> {
  return MediaPlayerConnectorObservable(this, executor)
}
