package com.github.satoshun.reactivex.media2

import androidx.media2.DataSourceDesc2
import androidx.media2.MediaPlayerConnector

sealed class MediaPlayerConnectorEvent {
  class SeekCompleted(
    val mpb: MediaPlayerConnector,
    val position: Long
  ) : MediaPlayerConnectorEvent()

  class MediaPrepared(
    val mpb: MediaPlayerConnector,
    val dsd: DataSourceDesc2
  ) : MediaPlayerConnectorEvent()

  class CurrentDataSourceChanged(
    val mpb: MediaPlayerConnector,
    val dsd: DataSourceDesc2?
  ) : MediaPlayerConnectorEvent()

  class PlayerStateChanged(
    val mpb: MediaPlayerConnector,
    val state: Int
  ) : MediaPlayerConnectorEvent()

  class BufferingStateChanged(
    val mpb: MediaPlayerConnector,
    val dsd: DataSourceDesc2,
    val state: Int
  ) : MediaPlayerConnectorEvent()

  class PlaybackSpeedChanged(
    val mpb: MediaPlayerConnector,
    val speed: Float
  ) : MediaPlayerConnectorEvent()
}
