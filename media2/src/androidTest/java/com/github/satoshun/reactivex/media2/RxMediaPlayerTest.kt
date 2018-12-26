package com.github.satoshun.reactivex.media2

import androidx.media2.MediaPlayer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.github.satoshun.media2.reactivex.events
import org.hamcrest.core.IsInstanceOf
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class RxMediaPlayerTest {
  @Rule @JvmField val rule = ActivityTestRule(TestActivity::class.java)

  private lateinit var player: MediaPlayer

  @Before
  fun setUp() {
    player = rule.activity.prepareTestPlayer()
  }

  @After
  fun tearDown() {
    player.reset()
  }

  @Test fun create_player() {
    val test = player.events().test()

    player.prepare()

    val event = test.firstWithAwait()
    Assert.assertThat(event, IsInstanceOf(SessionPlayerEvent.BufferingStateChanged::class.java))
  }
}
