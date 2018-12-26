package com.github.satoshun.media2

import androidx.media.AudioAttributesCompat
import androidx.media2.MediaItem
import androidx.media2.MediaMetadata
import androidx.media2.MediaPlayer
import androidx.media2.MediaTimestamp
import androidx.media2.SessionPlayer
import androidx.media2.TimedMetaData
import androidx.media2.VideoSize

sealed class MediaPlayerEvent {
  data class TimedMetaDataAvailable(
    val mp: MediaPlayer,
    val item: MediaItem,
    val data: TimedMetaData
  ) : MediaPlayerEvent()

  data class MediaTimeDiscontinuity(
    val mp: MediaPlayer,
    val item: MediaItem,
    val timestamp: MediaTimestamp
  ) : MediaPlayerEvent()

  data class SubtitleData(
    val mp: MediaPlayer,
    val item: MediaItem,
    val data: androidx.media2.SubtitleData
  ) : MediaPlayerEvent()

  data class Info(
    val mp: MediaPlayer,
    val item: MediaItem,
    val what: Int,
    val extra: Int
  ) : MediaPlayerEvent()

  data class VideoSizeChanged(
    val mp: MediaPlayer,
    val item: MediaItem,
    val size: VideoSize
  ) : MediaPlayerEvent()

  data class Error(
    val mp: MediaPlayer,
    val item: MediaItem,
    val what: Int,
    val extra: Int
  ) : MediaPlayerEvent()
}

sealed class SessionPlayerEvent : MediaPlayerEvent() {
  data class RepeatModeChanged(
    val player: SessionPlayer,
    val repeatMode: Int
  ) : SessionPlayerEvent()

  data class PlaylistChanged(
    val player: SessionPlayer,
    val list: MutableList<MediaItem>?,
    val metadata: MediaMetadata?
  ) : SessionPlayerEvent()

  data class PlaybackCompleted(
    val player: SessionPlayer
  ) : SessionPlayerEvent()

  data class SeekCompleted(
    val player: SessionPlayer,
    val position: Long
  ) : SessionPlayerEvent()

  data class PlayerStateChanged(
    val player: SessionPlayer,
    val playerState: Int
  ) : SessionPlayerEvent()

  data class AudioAttributesChanged(
    val player: SessionPlayer,
    val attributes: AudioAttributesCompat?
  ) : SessionPlayerEvent()

  data class PlaylistMetadataChanged(
    val player: SessionPlayer,
    val metadata: MediaMetadata?
  ) : SessionPlayerEvent()

  data class CurrentMediaItemChanged(
    val player: SessionPlayer,
    val item: MediaItem
  ) : SessionPlayerEvent()

  data class BufferingStateChanged(
    val player: SessionPlayer,
    val item: MediaItem?,
    val buffState: Int
  ) : SessionPlayerEvent()

  data class ShuffleModeChanged(
    val player: SessionPlayer,
    val shuffleMode: Int
  ) : SessionPlayerEvent()

  data class PlaybackSpeedChanged(
    val player: SessionPlayer,
    val playbackSpeed: Float
  ) : SessionPlayerEvent()
}
