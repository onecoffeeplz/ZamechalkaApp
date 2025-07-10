package dev.onecoffeeplz.zamechalka.domain.usecase.recording

import kotlinx.coroutines.CoroutineScope

interface StartRecordingUseCase {
    suspend operator fun invoke(scope: CoroutineScope): Result<Unit>
}