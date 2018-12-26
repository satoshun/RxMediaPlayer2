package com.github.satoshun.media2.reactivex

import androidx.media.AudioAttributesCompat
import androidx.media2.MediaItem
import androidx.media2.MediaMetadata
import androidx.media2.MediaPlayer
import androidx.media2.MediaTimestamp
import androidx.media2.SessionPlayer
import androidx.media2.SubtitleData
import androidx.media2.TimedMetaData
import androidx.media2.VideoSize
import com.github.satoshun.reactivex.media2.MediaPlayerEvent
import com.github.satoshun.reactivex.media2.SessionPlayerEvent
import io.reactivex.Observable
import io.reactivex.Observer
import java.util.concurrent.Executor
import java.util.concurrent.atomic.AtomicBoolean

internal class MediaPlayerObservable(
  private val player: MediaPlayer,
  private val executor: Executor
) : Observable<MediaPlayerEvent>() {
  override fun subscribeActual(observer: Observer<in MediaPlayerEvent>) {
    val listener = Listener(player, observer, executor)
    observer.onSubscribe(listener)
    listener.subscribe()
  }

  internal class Listener(
    private val player: MediaPlayer,
    private val observer: Observer<in MediaPlayerEvent>,
    private val executor: Executor
  ) : MediaPlayer.PlayerCallback(),
    MainDisposable {
    override val unsubscribed = AtomicBoolean()

    fun subscribe() {
      player.registerPlayerCallback(executor, this)
    }

    override fun onDispose() {
      player.unregisterPlayerCallback(this)
    }

    override fun onRepeatModeChanged(player: SessionPlayer, repeatMode: Int) {
      super.onRepeatModeChanged(player, repeatMode)
      if (isDisposed) return
      observer.onNext(SessionPlayerEvent.RepeatModeChanged(player, repeatMode))
    }

    override fun onPlaylistChanged(player: SessionPlayer, list: MutableList<MediaItem>?, metadata: MediaMetadata?) {
      super.onPlaylistChanged(player, list, metadata)
      if (isDisposed) return
      observer.onNext(SessionPlayerEvent.PlaylistChanged(player, list, metadata))
    }

    override fun onPlaybackCompleted(player: SessionPlayer) {
      super.onPlaybackCompleted(player)
      if (isDisposed) return
      observer.onNext(SessionPlayerEvent.PlaybackCompleted(player))
    }

    override fun onSeekCompleted(player: SessionPlayer, position: Long) {
      super.onSeekCompleted(player, position)
      if (isDisposed) return
      observer.onNext(SessionPlayerEvent.SeekCompleted(player, position))
    }

    override fun onPlayerStateChanged(player: SessionPlayer, playerState: Int) {
      super.onPlayerStateChanged(player, playerState)
      if (isDisposed) return
      observer.onNext(SessionPlayerEvent.PlayerStateChanged(player, playerState))
    }

    override fun onAudioAttributesChanged(player: SessionPlayer, attributes: AudioAttributesCompat?) {
      super.onAudioAttributesChanged(player, attributes)
      if (isDisposed) return
      observer.onNext(
        SessionPlayerEvent.AudioAttributesChanged(
          player,
          attributes
        )
      )
    }

    override fun onPlaylistMetadataChanged(player: SessionPlayer, metadata: MediaMetadata?) {
      super.onPlaylistMetadataChanged(player, metadata)
      if (isDisposed) return
      observer.onNext(SessionPlayerEvent.PlaylistMetadataChanged(player, metadata))
    }

    override fun onCurrentMediaItemChanged(player: SessionPlayer, item: MediaItem) {
      super.onCurrentMediaItemChanged(player, item)
      if (isDisposed) return
      observer.onNext(SessionPlayerEvent.CurrentMediaItemChanged(player, item))
    }

    override fun onBufferingStateChanged(player: SessionPlayer, item: MediaItem?, buffState: Int) {
      super.onBufferingStateChanged(player, item, buffState)
      if (isDisposed) return
      observer.onNext(
        SessionPlayerEvent.BufferingStateChanged(
          player,
          item,
          buffState
        )
      )
    }

    override fun onShuffleModeChanged(player: SessionPlayer, shuffleMode: Int) {
      super.onShuffleModeChanged(player, shuffleMode)
      if (isDisposed) return
      observer.onNext(SessionPlayerEvent.ShuffleModeChanged(player, shuffleMode))
    }

    override fun onPlaybackSpeedChanged(player: SessionPlayer, playbackSpeed: Float) {
      super.onPlaybackSpeedChanged(player, playbackSpeed)
      if (isDisposed) return
      observer.onNext(
        SessionPlayerEvent.PlaybackSpeedChanged(
          player,
          playbackSpeed
        )
      )
    }

    override fun onTimedMetaDataAvailable(mp: MediaPlayer, item: MediaItem, data: TimedMetaData) {
      super.onTimedMetaDataAvailable(mp, item, data)
      if (isDisposed) return
      observer.onNext(MediaPlayerEvent.TimedMetaDataAvailable(mp, item, data))
    }

    override fun onMediaTimeDiscontinuity(mp: MediaPlayer, item: MediaItem, timestamp: MediaTimestamp) {
      super.onMediaTimeDiscontinuity(mp, item, timestamp)
      if (isDisposed) return
      observer.onNext(MediaPlayerEvent.MediaTimeDiscontinuity(mp, item, timestamp))
    }

    override fun onSubtitleData(mp: MediaPlayer, item: MediaItem, data: SubtitleData) {
      super.onSubtitleData(mp, item, data)
      if (isDisposed) return
      observer.onNext(MediaPlayerEvent.SubtitleData(mp, item, data))
    }

    override fun onInfo(mp: MediaPlayer, item: MediaItem, what: Int, extra: Int) {
      super.onInfo(mp, item, what, extra)
      if (isDisposed) return
      observer.onNext(MediaPlayerEvent.Info(mp, item, what, extra))
    }

    override fun onVideoSizeChanged(mp: MediaPlayer, item: MediaItem, size: VideoSize) {
      super.onVideoSizeChanged(mp, item, size)
      if (isDisposed) return
      observer.onNext(MediaPlayerEvent.VideoSizeChanged(mp, item, size))
    }

    override fun onError(mp: MediaPlayer, item: MediaItem, what: Int, extra: Int) {
      super.onError(mp, item, what, extra)
      if (isDisposed) return
      observer.onNext(MediaPlayerEvent.Error(mp, item, what, extra))
    }
  }
}
