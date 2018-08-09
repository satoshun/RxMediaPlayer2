package com.github.satoshun.reactivex.media2

import androidx.annotation.CheckResult
import androidx.media2.MediaPlayer2
import io.reactivex.Observable

@CheckResult
fun MediaPlayer2.events(): Observable<MediaPlayer2Event> {
  return MediaPlayer2Observable(this)
}
