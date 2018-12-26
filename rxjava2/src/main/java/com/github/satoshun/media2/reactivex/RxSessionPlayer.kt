package com.github.satoshun.media2.reactivex

import androidx.annotation.CheckResult
import androidx.media2.SessionPlayer
import com.github.satoshun.media2.SessionPlayerEvent
import io.reactivex.Observable
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * event stream of [SessionPlayer.PlayerCallback]
 */
@CheckResult
fun SessionPlayer.events(
  executor: Executor = Executors.newSingleThreadExecutor()
): Observable<SessionPlayerEvent> {
  return SessionPlayerObservable(this, executor)
}
