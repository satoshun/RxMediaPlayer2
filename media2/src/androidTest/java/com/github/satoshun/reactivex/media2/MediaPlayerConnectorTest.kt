package com.github.satoshun.reactivex.media2

import androidx.media2.MediaPlayer2
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MediaPlayerConnectorTest {
  @Rule @JvmField val rule = ActivityTestRule(TestActivity::class.java)

  private lateinit var player: MediaPlayer2

  @Before
  fun setUp() {
    player = rule.activity.prepareTestPlayer()
  }

  @After
  fun tearDown() {
    player.reset()
  }

  @Test fun create_player2() {
    val test = player.mediaPlayerConnector.events().test()
    player.prepare()

    test
        .firstWithAwait()
        .isInstanceOf<MediaPlayerConnectorEvent.BufferingStateChanged>()
  }
}
