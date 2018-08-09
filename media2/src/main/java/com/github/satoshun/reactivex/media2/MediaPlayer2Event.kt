package com.github.satoshun.reactivex.media2

import androidx.media2.DataSourceDesc2
import androidx.media2.MediaPlayer2
import androidx.media2.MediaTimestamp2
import androidx.media2.SubtitleData2
import androidx.media2.TimedMetaData2

sealed class MediaPlayer2Event {
  data class MediaTimeDiscontinuity(
    val mp: MediaPlayer2,
    val dsd: DataSourceDesc2,
    val timestamp: MediaTimestamp2
  ) : MediaPlayer2Event()

  data class Info(
    val mp: MediaPlayer2,
    val dsd: DataSourceDesc2,
    val what: Int,
    val extra: Int
  ) : MediaPlayer2Event()

  data class Error(
    val mp: MediaPlayer2,
    val dsd: DataSourceDesc2,
    val what: Int,
    val extra: Int
  ) : MediaPlayer2Event()

  data class SubtitleData(
    val mp: MediaPlayer2,
    val dsd: DataSourceDesc2,
    val data: SubtitleData2
  ) : MediaPlayer2Event()

  data class VideoSizeChanged(
    val mp: MediaPlayer2,
    val dsd: DataSourceDesc2,
    val width: Int,
    val height: Int
  ) : MediaPlayer2Event()

  data class TimedMetaDataAvailable(
    val mp: MediaPlayer2,
    val dsd: DataSourceDesc2,
    val data: TimedMetaData2
  ) : MediaPlayer2Event()

  data class CallCompleted(
    val mp: MediaPlayer2,
    val dsd: DataSourceDesc2,
    val what: Int,
    val status: Int
  ) : MediaPlayer2Event()

  data class CommandLabelReached(
    val mp: MediaPlayer2,
    val label: Any
  ) : MediaPlayer2Event()
}
