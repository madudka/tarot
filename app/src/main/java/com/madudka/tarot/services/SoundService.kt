package com.madudka.tarot.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.madudka.tarot.R
import com.madudka.tarot.view.App.settings

class SoundService : Service() {
    private var mediaPlayer: MediaPlayer? = null
    private var binder = SSBinder()

    fun start() = mediaPlayer?.start()
    fun pause() = mediaPlayer?.pause()
    private fun stop() = mediaPlayer?.let {
        it.stop()
        it.release()
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.background_sound)
        mediaPlayer?.apply {
            isLooping = true
            setVolume(20F, 20F)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stop()
    }

    inner class SSBinder : Binder(){
        fun getService(): SoundService = this@SoundService
    }

    override fun onBind(intent: Intent?): IBinder {
        if (settings.music) start()
        return binder
    }
}