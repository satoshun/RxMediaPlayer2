package com.github.satoshun.reactivex.media2

import androidx.media2.DataSourceDesc2
import androidx.media2.MediaPlayer2
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.MainThreadDisposable

internal class DrmConfigObservable(
  private val player: MediaPlayer2
) : Observable<MediaPlayer2DrmConfig>() {
  override fun subscribeActual(observer: Observer<in MediaPlayer2DrmConfig>) {
    val listener = Listener(player, observer)
    observer.onSubscribe(listener)
    player.setOnDrmConfigHelper(listener)
  }

  private class Listener(
    private val player: MediaPlayer2,
    private val observer: Observer<in MediaPlayer2DrmConfig>
  ) : MainThreadDisposable(),
    MediaPlayer2.OnDrmConfigHelper {
    override fun onDrmConfig(mp: MediaPlayer2, dsd: DataSourceDesc2) {
      if (isDisposed) return
      observer.onNext(MediaPlayer2DrmConfig(mp, dsd))
    }

    override fun onDispose() {
      player.setOnDrmConfigHelper(null)
    }
  }
}
