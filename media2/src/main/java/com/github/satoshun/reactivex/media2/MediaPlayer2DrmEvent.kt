package com.github.satoshun.reactivex.media2

import androidx.media2.DataSourceDesc2
import androidx.media2.MediaPlayer2

sealed class MediaPlayer2DrmEvent {
  class DrmInfo(
    val mp: MediaPlayer2,
    val dsd: DataSourceDesc2?,
    val drmInfo: MediaPlayer2.DrmInfo?
  ) : MediaPlayer2DrmEvent()

  class DrmPrepared(
    val mp: MediaPlayer2,
    val dsd: DataSourceDesc2?,
    val status: Int
  ) : MediaPlayer2DrmEvent()
}
