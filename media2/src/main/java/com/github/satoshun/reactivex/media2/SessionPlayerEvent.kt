package com.github.satoshun.reactivex.media2

import androidx.media.AudioAttributesCompat
import androidx.media2.MediaItem
import androidx.media2.MediaMetadata
import androidx.media2.SessionPlayer

sealed class SessionPlayerEvent {
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
