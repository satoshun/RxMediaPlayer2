package com.github.satoshun.media2.sample

import androidx.appcompat.app.AppCompatActivity
import com.github.satoshun.coroutine.autodispose.lifecycle.autoDisposeInterceptor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseActivity : AppCompatActivity(),
  CoroutineScope {
  private val job = Job()
  override val coroutineContext
    get() = job +
      Dispatchers.Main +
      autoDisposeInterceptor()
}
