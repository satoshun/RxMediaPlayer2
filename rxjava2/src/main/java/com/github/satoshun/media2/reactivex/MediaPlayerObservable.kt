package com.github.satoshun.media2.reactivex

import androidx.media2.MediaPlayer
import com.github.satoshun.media2.MediaPlayerEvent
import com.github.satoshun.media2.MediaPlayerListener
import io.reactivex.Observable
import io.reactivex.Observer
import java.util.concurrent.Executor
import java.util.concurrent.atomic.AtomicBoolean

internal class MediaPlayerObservable(
  private val player: MediaPlayer,
  private val executor: Executor
) : Observable<MediaPlayerEvent>() {
  override fun subscribeActual(observer: Observer<in MediaPlayerEvent>) {
    val listener = Listener(player, observer, executor)
    observer.onSubscribe(listener)
    listener.subscribe()
  }

  internal class Listener(
    private val player: MediaPlayer,
    private val observer: Observer<in MediaPlayerEvent>,
    private val executor: Executor
  ) : MediaPlayerListener(),
    MainDisposable {
    override val unsubscribed = AtomicBoolean()

    fun subscribe() {
      player.registerPlayerCallback(executor, this)
    }

    override fun onDispose() {
      player.unregisterPlayerCallback(this)
    }

    override fun invoke(event: MediaPlayerEvent) {
      if (isDisposed) return
      observer.onNext(event)
    }
  }
}
