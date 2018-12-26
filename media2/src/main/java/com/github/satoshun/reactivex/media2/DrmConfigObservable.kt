package com.github.satoshun.reactivex.media2

//internal class DrmConfigObservable(
//  private val player: MediaPlayer
//) : Observable<MediaPlayer2DrmConfig>() {
//  override fun subscribeActual(observer: Observer<in MediaPlayer2DrmConfig>) {
//    val listener = Listener(player, observer)
//    observer.onSubscribe(listener)
//    player.setOnDrmConfigHelper(listener)
//  }
//
//  private class Listener(
//    private val player: MediaPlayer,
//    private val observer: Observer<in MediaPlayer2DrmConfig>
//  ) : MainThreadDisposable(),
//    MediaPlayer.OnDrmConfigHelper {
//    override fun onDrmConfig(mp: MediaPlayer, dsd: MediaItem) {
//      if (isDisposed) return
//      observer.onNext(MediaPlayer2DrmConfig(mp, dsd))
//    }
//
//    override fun onDispose() {
//      player.setOnDrmConfigHelper(null)
//    }
//  }
//}
