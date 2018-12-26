package com.github.satoshun.reactivex.media2

import android.app.Activity
import androidx.media2.FileMediaItem
import androidx.media2.MediaPlayer

fun Activity.prepareTestPlayer(): MediaPlayer {
  val player = MediaPlayer(this)
  val fd = assets.openFd("big_buck_bunny.mp4")
  val item = FileMediaItem
    .Builder(
      fd.fileDescriptor,
      fd.startOffset,
      fd.length
    )
    .build()
  player.setMediaItem(item)
  return player
}
