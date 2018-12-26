package com.github.satoshun.rxmediaplayer2

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import androidx.appcompat.app.AppCompatActivity
import androidx.media2.MediaPlayer
import androidx.media2.UriMediaItem
import com.github.satoshun.reactivex.media2.events
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

private const val SAMPLE = "https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_10mb.mp4"
//private const val SAMPLE = "https://bitmovin-a.akamaihd.net/content/art-of-motion_drm/m3u8s/11331.m3u8"

class MainActivity : AppCompatActivity() {
  private val disposables = CompositeDisposable()

  private lateinit var player: MediaPlayer

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    player = MediaPlayer(this)

    fun eventsObserve() {
      disposables.add(
        player
          .events()
          .subscribe {
            Log.d("event", it.toString())
          }
      )
    }

    fun drmtest() {
//      disposables.add(
//          player.drmEvents()
//              .subscribe {
//                Log.d("drm events", it.toString())
//              }
//      )

      // todo
//      player.prepareDrm(UUID.randomUUID())
    }

    eventsObserve()
//    drmtest()

    player.setMediaItem(
      UriMediaItem
        .Builder(this, Uri.parse(SAMPLE))
        .build()
    )
//    val fd = assets.openFd("big_buck_bunny.mp4")
//    player.setDataSource(
//        FileDataSourceDesc2
//            .Builder(
//                fd.fileDescriptor,
//                fd.startOffset,
//                fd.length
//            )
//            .build()
//    )
    player.prepare()
    player.play()

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

  override fun onDestroy() {
    disposables.clear()
//    player.releaseDrm()
    super.onDestroy()
  }
}
