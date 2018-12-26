package com.github.satoshun.reactivex.media2

//@RunWith(AndroidJUnit4::class)
//class MediaPlayerConnectorTest {
//  @Rule @JvmField val rule = ActivityTestRule(TestActivity::class.java)
//
//  private lateinit var player: MediaPlayer2
//
//  @Before
//  fun setUp() {
//    player = rule.activity.prepareTestPlayer()
//  }
//
//  @After
//  fun tearDown() {
//    player.reset()
//  }
//
//  @Test fun create_player2() {
//    val test = player.mediaPlayerConnector.events().test()
//    player.prepare()
//
//    test
//        .firstWithAwait()
//        .isInstanceOf<MediaPlayerConnectorEvent.BufferingStateChanged>()
//  }
//}
