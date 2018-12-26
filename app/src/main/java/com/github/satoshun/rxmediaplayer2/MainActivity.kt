package com.github.satoshun.rxmediaplayer2

import android.net.Uri
import android.os.Bundle
import android.view.SurfaceHolder
import androidx.appcompat.app.AppCompatActivity
import androidx.media2.MediaPlayer
import androidx.media2.SessionPlayer
import androidx.media2.UriMediaItem
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors

private const val SAMPLE = "https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_10mb.mp4"
//private const val SAMPLE = "https://bitmovin-a.akamaihd.net/content/art-of-motion_drm/m3u8s/11331.m3u8"

class MainActivity : AppCompatActivity() {
  private val disposables = CompositeDisposable()

  private lateinit var player: MediaPlayer

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    player = MediaPlayer(this)

//    player.set

    fun eventtest() {
      player.registerPlayerCallback(
        Executors.newFixedThreadPool(1),
        object : SessionPlayer.PlayerCallback() {
        }
      )
//      disposables.add(
//        player.events()
//          .subscribe {
//            Log.d("event", it.toString())
//            when (it) {
//              is MediaPlayer2Event.Info -> {
//                if (it.what == MediaPlayer2.MEDIA_INFO_PREPARED) {
//                  player.play()
//                }
//              }
//            }
//          }
//      )
    }

    fun connectortest() {
//      disposables.add(
//          player.mediaPlayerConnector.events()
//              .subscribe {
//                Log.d("connector event", it.toString())
//              }
//      )
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

    eventtest()
//    connectortest()
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
