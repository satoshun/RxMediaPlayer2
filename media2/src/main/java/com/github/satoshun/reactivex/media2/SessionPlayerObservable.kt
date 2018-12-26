package com.github.satoshun.reactivex.media2

import androidx.media.AudioAttributesCompat
import androidx.media2.MediaItem
import androidx.media2.MediaMetadata
import androidx.media2.SessionPlayer
import io.reactivex.Observable
import io.reactivex.Observer
import java.util.concurrent.Executor
import java.util.concurrent.atomic.AtomicBoolean

internal class SessionPlayerObservable(
  private val player: SessionPlayer,
  private val executor: Executor
) : Observable<SessionPlayerEvent>() {
  override fun subscribeActual(observer: Observer<in SessionPlayerEvent>) {
    val listener = Listener(player, observer, executor)
    observer.onSubscribe(listener)
    listener.subscribe()
  }

  private class Listener(
    private val player: SessionPlayer,
    private val observer: Observer<in SessionPlayerEvent>,
    private val executor: Executor
  ) : SessionPlayer.PlayerCallback(),
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
      observer.onNext(SessionPlayerEvent.AudioAttributesChanged(player, attributes))
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
      observer.onNext(SessionPlayerEvent.BufferingStateChanged(player, item, buffState))
    }

    override fun onShuffleModeChanged(player: SessionPlayer, shuffleMode: Int) {
      super.onShuffleModeChanged(player, shuffleMode)
      if (isDisposed) return
      observer.onNext(SessionPlayerEvent.ShuffleModeChanged(player, shuffleMode))
    }

    override fun onPlaybackSpeedChanged(player: SessionPlayer, playbackSpeed: Float) {
      super.onPlaybackSpeedChanged(player, playbackSpeed)
      if (isDisposed) return
      observer.onNext(SessionPlayerEvent.PlaybackSpeedChanged(player, playbackSpeed))
    }
  }
}
