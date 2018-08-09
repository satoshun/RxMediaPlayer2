package com.github.satoshun.reactivex.media2

import androidx.annotation.CheckResult
import androidx.media2.DataSourceDesc2
import androidx.media2.MediaPlayer2
import androidx.media2.MediaTimestamp2
import androidx.media2.SubtitleData2
import androidx.media2.TimedMetaData2
import io.reactivex.Observable
import io.reactivex.Observer
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean

@CheckResult
fun MediaPlayer2.events(): Observable<MediaPlayer2Event> {
  return MediaPlayer2Observable(this)
}

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

internal class MediaPlayer2Observable(
  private val player: MediaPlayer2
) : Observable<MediaPlayer2Event>() {
  override fun subscribeActual(observer: Observer<in MediaPlayer2Event>) {
    val listener = MediaPlayer2Listener(player, observer)
    observer.onSubscribe(listener)
    listener.subscribe()
  }
}

internal class MediaPlayer2Listener(
  private val player: MediaPlayer2,
  private val observer: Observer<in MediaPlayer2Event>,
  private val exector: Executor = Executors.newCachedThreadPool() // todo
) : MediaPlayer2.EventCallback(),
    MainDisposable {

  override val unsubscribed = AtomicBoolean()

  fun subscribe() {
    player.setEventCallback(exector, this)
  }

  override fun onDispose() {
    player.clearEventCallback()
  }

  override fun onMediaTimeDiscontinuity(
    mp: MediaPlayer2,
    dsd: DataSourceDesc2,
    timestamp: MediaTimestamp2
  ) {
    if (isDisposed) return
    observer.onNext(MediaPlayer2Event.MediaTimeDiscontinuity(mp, dsd, timestamp))
  }

  override fun onInfo(mp: MediaPlayer2, dsd: DataSourceDesc2, what: Int, extra: Int) {
    if (isDisposed) return
    observer.onNext(MediaPlayer2Event.Info(mp, dsd, what, extra))
  }

  override fun onError(mp: MediaPlayer2, dsd: DataSourceDesc2, what: Int, extra: Int) {
    if (isDisposed) return
    observer.onNext(MediaPlayer2Event.Error(mp, dsd, what, extra))
  }

  override fun onSubtitleData(mp: MediaPlayer2, dsd: DataSourceDesc2, data: SubtitleData2) {
    if (isDisposed) return
    observer.onNext(MediaPlayer2Event.SubtitleData(mp, dsd, data))
  }

  override fun onVideoSizeChanged(mp: MediaPlayer2, dsd: DataSourceDesc2, width: Int, height: Int) {
    if (isDisposed) return
    observer.onNext(MediaPlayer2Event.VideoSizeChanged(mp, dsd, width, height))
  }

  override fun onTimedMetaDataAvailable(mp: MediaPlayer2, dsd: DataSourceDesc2, data: TimedMetaData2) {
    if (isDisposed) return
    observer.onNext(MediaPlayer2Event.TimedMetaDataAvailable(mp, dsd, data))
  }

  override fun onCallCompleted(mp: MediaPlayer2, dsd: DataSourceDesc2, what: Int, status: Int) {
    if (isDisposed) return
    observer.onNext(MediaPlayer2Event.CallCompleted(mp, dsd, what, status))
  }

  override fun onCommandLabelReached(mp: MediaPlayer2, label: Any) {
    if (isDisposed) return
    observer.onNext(MediaPlayer2Event.CommandLabelReached(mp, label))
  }
}
