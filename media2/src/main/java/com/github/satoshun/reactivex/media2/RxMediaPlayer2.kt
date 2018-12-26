package com.github.satoshun.reactivex.media2

import androidx.annotation.CheckResult
import androidx.media2.MediaPlayer
import androidx.media2.SessionPlayer
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

/**
 * event stream of [MediaPlayer.PlayerCallback]
 */
@CheckResult
fun MediaPlayer.events(
  executor: Executor = Executors.newSingleThreadExecutor()
): Observable<MediaPlayerEvent> {
  return MediaPlayerObservable(this, executor)
}

///**
// * event stream of [MediaPlayer2.DrmEventCallback]
// */
//@CheckResult
//fun MediaPlayer2.drmEvents(
//  executor: Executor = Executors.newSingleThreadExecutor()
//): Observable<MediaPlayer2DrmEvent> {
//  return DrmEventObservable(this, executor)
//}
//
///**
// * event stream of [MediaPlayer2.OnDrmConfigHelper]
// */
//@CheckResult
//fun MediaPlayer2.drmConfig(): Observable<MediaPlayer2DrmConfig> {
//  return DrmConfigObservable(this)
//}
