package dev.onecoffeeplz.zamechalka.domain.repository

interface AudioPlayerRepository {
    fun preparePlayer(
        filePath: String,
        onPrepared: () -> Unit,
        onCompletion: () -> Unit,
        onError: (Throwable) -> Unit,
    ): Result<Unit>

    fun startPlayer(): Result<Unit>
    fun pausePlayer(): Result<Unit>
    fun release(): Result<Unit>
    fun currentPosition(): Long?
}