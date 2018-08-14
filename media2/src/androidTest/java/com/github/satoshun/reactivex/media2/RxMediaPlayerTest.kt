package com.github.satoshun.reactivex.media2

import androidx.media2.FileDataSourceDesc2
import androidx.media2.MediaPlayer2
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RxMediaPlayerTest {
  @Rule @JvmField val rule = ActivityTestRule(TestActivity::class.java)

  private lateinit var player: MediaPlayer2

  @Before
  fun setUp() {
    player = MediaPlayer2.create(rule.activity)

    val fd = rule.activity.assets.openFd("big_buck_bunny.mp4")
    val desc = FileDataSourceDesc2.Builder(fd.fileDescriptor).build()
    player.setDataSource(desc)
  }

  @Test fun create_player() {
    val test = player.events().test()

    player.prepare()

    test
        .awaitCount(1)
        .values()[0]
        .isInstanceOf<MediaPlayer2Event.CallCompleted>()
  }
}
