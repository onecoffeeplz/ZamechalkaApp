package dev.onecoffeeplz.zamechalka.domain.repository

import kotlinx.coroutines.CoroutineScope

interface AudioRecordRepository {
    suspend fun startRecording(scope: CoroutineScope): Result<Unit>
    suspend fun stopRecording(): Result<String>
    suspend fun deleteRecording(path: String): Result<Unit>
}