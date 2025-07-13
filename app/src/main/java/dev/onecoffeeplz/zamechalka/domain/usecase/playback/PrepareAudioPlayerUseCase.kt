package dev.onecoffeeplz.zamechalka.domain.usecase.playback

interface PrepareAudioPlayerUseCase {
     operator fun invoke(
        filePath: String,
        onPrepared: () -> Unit,
        onCompletion: () -> Unit,
        onError: (Throwable) -> Unit
    ): Result<Unit>
}