package com.github.satoshun.reactivex.media2

import androidx.media2.DataSourceDesc2
import androidx.media2.MediaPlayer2
import io.reactivex.Observable
import io.reactivex.Observer
import java.util.concurrent.Executor
import java.util.concurrent.atomic.AtomicBoolean

internal class DrmEventObservable(
  private val player: MediaPlayer2,
  private val executor: Executor
) : Observable<MediaPlayer2DrmEvent>() {
  override fun subscribeActual(observer: Observer<in MediaPlayer2DrmEvent>) {
    val listener = Listener(player, observer)
    observer.onSubscribe(listener)
    player.setDrmEventCallback(executor, listener)
  }

  private class Listener(
    private val player: MediaPlayer2,
    private val observer: Observer<in MediaPlayer2DrmEvent>
  ) : MediaPlayer2.DrmEventCallback(),
    MainDisposable {
    override val unsubscribed: AtomicBoolean = AtomicBoolean()

    override fun onDrmInfo(mp: MediaPlayer2, dsd: DataSourceDesc2?, drmInfo: MediaPlayer2.DrmInfo?) {
      if (isDisposed) return
      observer.onNext(MediaPlayer2DrmEvent.DrmInfo(mp, dsd, drmInfo))
    }

    override fun onDrmPrepared(mp: MediaPlayer2, dsd: DataSourceDesc2?, status: Int) {
      if (isDisposed) return
      observer.onNext(MediaPlayer2DrmEvent.DrmPrepared(mp, dsd, status))
    }

    override fun onDispose() {
      player.clearDrmEventCallback()
    }
  }
}
