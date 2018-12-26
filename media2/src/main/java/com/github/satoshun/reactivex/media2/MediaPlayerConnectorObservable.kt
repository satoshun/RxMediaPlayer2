package com.github.satoshun.reactivex.media2

//internal class MediaPlayerConnectorObservable(
//  private val connector: MediaPlayerConnector,
//  private val executor: Executor
//) : Observable<MediaPlayerConnectorEvent>() {
//  override fun subscribeActual(observer: Observer<in MediaPlayerConnectorEvent>) {
//    val listener = Listener(connector, observer)
//    observer.onSubscribe(listener)
//    connector.registerPlayerEventCallback(executor, listener)
//  }
//
//  private class Listener(
//    private val connector: MediaPlayerConnector,
//    private val observer: Observer<in MediaPlayerConnectorEvent>
//  ) : MediaPlayerConnector.PlayerEventCallback(),
//    MainDisposable {
//    override val unsubscribed: AtomicBoolean = AtomicBoolean()
//
//    override fun onDispose() {
//      connector.unregisterPlayerEventCallback(this)
//    }
//
//    override fun onSeekCompleted(mpb: MediaPlayerConnector, position: Long) {
//      if (isDisposed) return
//      observer.onNext(MediaPlayerConnectorEvent.SeekCompleted(mpb, position))
//    }
//
//    override fun onMediaPrepared(mpb: MediaPlayerConnector, dsd: DataSourceDesc2) {
//      if (isDisposed) return
//      observer.onNext(MediaPlayerConnectorEvent.MediaPrepared(mpb, dsd))
//    }
//
//    override fun onCurrentDataSourceChanged(mpb: MediaPlayerConnector, dsd: DataSourceDesc2?) {
//      if (isDisposed) return
//      observer.onNext(MediaPlayerConnectorEvent.CurrentDataSourceChanged(mpb, dsd))
//    }
//
//    override fun onPlayerStateChanged(mpb: MediaPlayerConnector, state: Int) {
//      if (isDisposed) return
//      observer.onNext(MediaPlayerConnectorEvent.PlayerStateChanged(mpb, state))
//    }
//
//    override fun onBufferingStateChanged(mpb: MediaPlayerConnector, dsd: DataSourceDesc2, state: Int) {
//      if (isDisposed) return
//      observer.onNext(
//          MediaPlayerConnectorEvent.BufferingStateChanged(
//              mpb,
//              dsd,
//              state
//          )
//      )
//    }
//
//    override fun onPlaybackSpeedChanged(mpb: MediaPlayerConnector, speed: Float) {
//      if (isDisposed) return
//      observer.onNext(MediaPlayerConnectorEvent.PlaybackSpeedChanged(mpb, speed))
//    }
//  }
//}
