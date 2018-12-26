package com.github.satoshun.reactivex.media2

import android.os.Looper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.atomic.AtomicBoolean

// borrow code from io.reactivex.android.MainThreadDisposable
internal interface MainDisposable : Disposable {
  val unsubscribed: AtomicBoolean

  override fun isDisposed(): Boolean {
    return unsubscribed.get()
  }

  override fun dispose() {
    if (unsubscribed.compareAndSet(false, true)) {
      if (Looper.myLooper() == Looper.getMainLooper()) {
        onDispose()
      } else {
        AndroidSchedulers.mainThread().scheduleDirect { onDispose() }
      }
    }
  }

  fun onDispose()
}
