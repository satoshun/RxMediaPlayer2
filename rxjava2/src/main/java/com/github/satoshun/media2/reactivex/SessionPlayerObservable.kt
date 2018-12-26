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
    observer: Observer<in SessionPlayerEvent>,
    private val executor: Executor
  ) : MainDisposable {
    init {
    }

    private val actual = PlayerObserver(observer)

    override val unsubscribed = AtomicBoolean()

    fun subscribe() {
      player.registerPlayerCallback(executor, actual)
    }

    override fun onDispose() {
      player.unregisterPlayerCallback(actual)
    }

    private inner class PlayerObserver(
      private val observer: Observer<in SessionPlayerEvent>
    ) : SessionPlayerListener() {
      override fun invoke(event: SessionPlayerEvent) {
        if (isDisposed) return
        observer.onNext(event)
      }
    }
  }
}
