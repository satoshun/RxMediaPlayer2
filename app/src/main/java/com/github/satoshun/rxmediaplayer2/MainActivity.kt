package com.github.satoshun.rxmediaplayer2

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.media2.MediaPlayer2
import androidx.media2.UriDataSourceDesc2
import com.github.satoshun.reactivex.media2.events

private const val SAMPLE = "https://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4"

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val player = MediaPlayer2.create(this)
    val dispose = player.events()
        .subscribe {
          Log.d("player", it.toString())
        }

    player.setDataSource(UriDataSourceDesc2
        .Builder(this, Uri.parse(SAMPLE))
        .build()
    )
    player.prepare()
  }
}
