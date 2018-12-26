package com.github.satoshun.reactivex.media2

///**
// * event stream of [MediaPlayer2.EventCallback]
// */
//@CheckResult
//fun MediaPlayer2.events(
//  executor: Executor = Executors.newSingleThreadExecutor()
//): Observable<MediaPlayer2Event> {
//  return MediaPlayer2Observable(this, executor)
//}
//
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
