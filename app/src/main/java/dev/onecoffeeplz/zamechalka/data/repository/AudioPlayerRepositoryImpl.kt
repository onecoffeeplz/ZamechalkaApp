package dev.onecoffeeplz.zamechalka.data.repository

import android.media.MediaPlayer
import dev.onecoffeeplz.zamechalka.domain.repository.AudioPlayerRepository

class AudioPlayerRepositoryImpl(private var mediaPlayer: MediaPlayer) : AudioPlayerRepository {
    override fun preparePlayer(
        filePath: String,
        onPrepared: () -> Unit,
        onCompletion: () -> Unit,
        onError: (Throwable) -> Unit,
    ): Result<Unit> = runCatching {
        mediaPlayer.reset()
        mediaPlayer.setOnPreparedListener {
            onPrepared()
        }
        mediaPlayer.setOnCompletionListener {
            onCompletion()
        }
        mediaPlayer.setOnErrorListener { mp, what, extra ->
            onError(Exception("MediaPlayer error code is $what"))
            true
        }
        mediaPlayer.setDataSource(filePath)
        mediaPlayer.prepareAsync()
    }

    override fun startPlayer(): Result<Unit> = runCatching {
        if (mediaPlayer.isPlaying) {
            return@runCatching
        } else {
            mediaPlayer.start()
        }
    }

    override fun pausePlayer(): Result<Unit> = runCatching {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
    }

    override fun release(): Result<Unit> = runCatching {
        mediaPlayer.reset()
    }

    override fun currentPosition(): Long? {
        return try {
            if (mediaPlayer.isPlaying || mediaPlayer.currentPosition > 0) {
                mediaPlayer.currentPosition.toLong()
            } else {
                null
            }
        } catch (e: IllegalStateException) {
            null
        }
    }
}