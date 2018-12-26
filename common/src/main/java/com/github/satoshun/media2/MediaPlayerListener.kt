package com.github.satoshun.media2

import androidx.media.AudioAttributesCompat
import androidx.media2.MediaItem
import androidx.media2.MediaMetadata
import androidx.media2.MediaPlayer
import androidx.media2.MediaTimestamp
import androidx.media2.SessionPlayer
import androidx.media2.SubtitleData
import androidx.media2.TimedMetaData
import androidx.media2.VideoSize

abstract class MediaPlayerListener : MediaPlayer.PlayerCallback(),
  Function1<MediaPlayerEvent, Unit> {

  final override fun onRepeatModeChanged(player: SessionPlayer, repeatMode: Int) {
    super.onRepeatModeChanged(player, repeatMode)
    invoke(SessionPlayerEvent.RepeatModeChanged(player, repeatMode))
  }

  final override fun onPlaylistChanged(player: SessionPlayer, list: MutableList<MediaItem>?, metadata: MediaMetadata?) {
    super.onPlaylistChanged(player, list, metadata)
    invoke(SessionPlayerEvent.PlaylistChanged(player, list, metadata))
  }

  final override fun onPlaybackCompleted(player: SessionPlayer) {
    super.onPlaybackCompleted(player)
    invoke(SessionPlayerEvent.PlaybackCompleted(player))
  }

  final override fun onSeekCompleted(player: SessionPlayer, position: Long) {
    super.onSeekCompleted(player, position)
    invoke(SessionPlayerEvent.SeekCompleted(player, position))
  }

  final override fun onPlayerStateChanged(player: SessionPlayer, playerState: Int) {
    super.onPlayerStateChanged(player, playerState)
    invoke(SessionPlayerEvent.PlayerStateChanged(player, playerState))
  }

  final override fun onAudioAttributesChanged(player: SessionPlayer, attributes: AudioAttributesCompat?) {
    super.onAudioAttributesChanged(player, attributes)
    invoke(SessionPlayerEvent.AudioAttributesChanged(player, attributes))
  }

  final override fun onPlaylistMetadataChanged(player: SessionPlayer, metadata: MediaMetadata?) {
    super.onPlaylistMetadataChanged(player, metadata)
    invoke(SessionPlayerEvent.PlaylistMetadataChanged(player, metadata))
  }

  final override fun onCurrentMediaItemChanged(player: SessionPlayer, item: MediaItem) {
    super.onCurrentMediaItemChanged(player, item)
    invoke(SessionPlayerEvent.CurrentMediaItemChanged(player, item))
  }

  final override fun onBufferingStateChanged(player: SessionPlayer, item: MediaItem?, buffState: Int) {
    super.onBufferingStateChanged(player, item, buffState)
    invoke(
      SessionPlayerEvent.BufferingStateChanged(
        player,
        item,
        buffState
      )
    )
  }

  final override fun onShuffleModeChanged(player: SessionPlayer, shuffleMode: Int) {
    super.onShuffleModeChanged(player, shuffleMode)
    invoke(SessionPlayerEvent.ShuffleModeChanged(player, shuffleMode))
  }

  final override fun onPlaybackSpeedChanged(player: SessionPlayer, playbackSpeed: Float) {
    super.onPlaybackSpeedChanged(player, playbackSpeed)
    invoke(SessionPlayerEvent.PlaybackSpeedChanged(player, playbackSpeed))
  }

  final override fun onTimedMetaDataAvailable(mp: MediaPlayer, item: MediaItem, data: TimedMetaData) {
    super.onTimedMetaDataAvailable(mp, item, data)
    invoke(MediaPlayerEvent.TimedMetaDataAvailable(mp, item, data))
  }

  final override fun onMediaTimeDiscontinuity(mp: MediaPlayer, item: MediaItem, timestamp: MediaTimestamp) {
    super.onMediaTimeDiscontinuity(mp, item, timestamp)
    invoke(MediaPlayerEvent.MediaTimeDiscontinuity(mp, item, timestamp))
  }

  final override fun onSubtitleData(mp: MediaPlayer, item: MediaItem, data: SubtitleData) {
    super.onSubtitleData(mp, item, data)
    invoke(MediaPlayerEvent.SubtitleData(mp, item, data))
  }

  final override fun onInfo(mp: MediaPlayer, item: MediaItem, what: Int, extra: Int) {
    super.onInfo(mp, item, what, extra)
    invoke(MediaPlayerEvent.Info(mp, item, what, extra))
  }

  final override fun onVideoSizeChanged(mp: MediaPlayer, item: MediaItem, size: VideoSize) {
    super.onVideoSizeChanged(mp, item, size)
    invoke(MediaPlayerEvent.VideoSizeChanged(mp, item, size))
  }

  final override fun onError(mp: MediaPlayer, item: MediaItem, what: Int, extra: Int) {
    super.onError(mp, item, what, extra)
    invoke(MediaPlayerEvent.Error(mp, item, what, extra))
  }
}
