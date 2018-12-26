package com.github.satoshun.media2.reactivex

import androidx.media2.SessionPlayer
import com.github.satoshun.media2.SessionPlayerEvent
import com.github.satoshun.media2.SessionPlayerListener
import io.reactivex.Observable
import io.reactivex.Observer
import java.util.concurrent.Executor
import java.util.concurrent.atomic.AtomicBoolean

internal class SessionPlayerObservable(
  private val player: SessionPlayer,
  private val executor: Executor
) : Observable<SessionPlayerEvent>() {
  override fun subscribeActual(observer: Observer<in SessionPlayerEvent>) {
    val listener = Listener(player, observer, executor)
    observer.onSubscribe(listener)
    listener.subscribe()
  }

  private class Listener(
    private val player: SessionPlayer,
    private val observer: Observer<in SessionPlayerEvent>,
    private val executor: Executor
  ) : SessionPlayerListener(),
    MainDisposable {
    override val unsubscribed = AtomicBoolean()

    fun subscribe() {
      player.registerPlayerCallback(executor, this)
    }

    override fun onDispose() {
      player.unregisterPlayerCallback(this)
    }

    override fun invoke(event: SessionPlayerEvent) {
      if (isDisposed) return
      observer.onNext(event)
    }
  }
}
