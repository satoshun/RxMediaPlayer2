package com.github.satoshun.reactivex.media2

import android.app.Activity
import androidx.media2.FileDataSourceDesc2
import androidx.media2.MediaPlayer2

fun Activity.prepareTestPlayer(): MediaPlayer2 {
  val player = MediaPlayer2.create(this)
  val fd = assets.openFd("big_buck_bunny.mp4")
  val desc = FileDataSourceDesc2
      .Builder(
          fd.fileDescriptor,
          fd.startOffset,
          fd.length
      )
      .build()
  player.setDataSource(desc)
  return player
}
