package com.github.satoshun.rxmediaplayer2

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import androidx.appcompat.app.AppCompatActivity
import androidx.media2.MediaPlayer2
import androidx.media2.UriDataSourceDesc2
import com.github.satoshun.reactivex.media2.MediaPlayer2Event
import com.github.satoshun.reactivex.media2.events
import kotlinx.android.synthetic.main.activity_main.*

private const val SAMPLE = "https://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4"

class MainActivity : AppCompatActivity() {
  private lateinit var player: MediaPlayer2

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    player = MediaPlayer2.create(this)
    val dispose = player.events()
        .subscribe {
          Log.d("player", it.toString())
          when (it) {
            is MediaPlayer2Event.Info -> {
              // todo
              if (it.what == 100) player.play()
            }
          }
        }

    player.setDataSource(UriDataSourceDesc2
        .Builder(this, Uri.parse(SAMPLE))
        .build()
    )
    player.prepare()

    // todo
    surface.holder.addCallback(object : SurfaceHolder.Callback {
      override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
      }

      override fun surfaceDestroyed(holder: SurfaceHolder?) {
      }

      override fun surfaceCreated(holder: SurfaceHolder) {
        player.setSurface(holder.surface)
      }
    })
  }
}
