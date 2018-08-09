package com.github.satoshun.reactivex.media2

import androidx.media2.DataSourceDesc2
import androidx.media2.MediaPlayer2
import androidx.media2.MediaTimestamp2
import androidx.media2.SubtitleData2
import androidx.media2.TimedMetaData2
import io.reactivex.Observable
import io.reactivex.Observer
import java.util.concurrent.Executor
import java.util.concurrent.atomic.AtomicBoolean

internal class MediaPlayer2Observable(
  private val player: MediaPlayer2,
  private val executor: Executor
) : Observable<MediaPlayer2Event>() {
  override fun subscribeActual(observer: Observer<in MediaPlayer2Event>) {
    val listener = Listener(player, observer, executor)
    observer.onSubscribe(listener)
    listener.subscribe()
  }

  private class Listener(
    private val player: MediaPlayer2,
    private val observer: Observer<in MediaPlayer2Event>,
    private val executor: Executor
  ) : MediaPlayer2.EventCallback(),
    MainDisposable {

    override val unsubscribed = AtomicBoolean()

    fun subscribe() {
      player.setEventCallback(executor, this)
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
}
