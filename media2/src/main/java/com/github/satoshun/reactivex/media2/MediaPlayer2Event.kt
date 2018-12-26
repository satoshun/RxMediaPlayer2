package com.github.satoshun.reactivex.media2

import androidx.media2.MediaItem
import androidx.media2.MediaPlayer
import androidx.media2.MediaTimestamp
import androidx.media2.TimedMetaData

sealed class MediaPlayer2Event {
  data class MediaTimeDiscontinuity(
    val mp: MediaPlayer,
    val dsd: MediaItem,
    val timestamp: MediaTimestamp
  ) : MediaPlayer2Event()

  data class Info(
    val mp: MediaPlayer,
    val dsd: MediaItem,
    val what: Int,
    val extra: Int
  ) : MediaPlayer2Event()

  data class Error(
    val mp: MediaPlayer,
    val dsd: MediaItem,
    val what: Int,
    val extra: Int
  ) : MediaPlayer2Event()

  data class SubtitleData(
    val mp: MediaPlayer,
    val dsd: MediaItem,
    val data: androidx.media2.SubtitleData
  ) : MediaPlayer2Event()

  data class VideoSizeChanged(
    val mp: MediaPlayer,
    val dsd: MediaItem,
    val width: Int,
    val height: Int
  ) : MediaPlayer2Event()

  data class TimedMetaDataAvailable(
    val mp: MediaPlayer,
    val dsd: MediaItem,
    val data: TimedMetaData
  ) : MediaPlayer2Event()

  data class CallCompleted(
    val mp: MediaPlayer,
    val dsd: MediaItem,
    val what: Int,
    val status: Int
  ) : MediaPlayer2Event()

  data class CommandLabelReached(
    val mp: MediaPlayer,
    val label: Any
  ) : MediaPlayer2Event()
}
