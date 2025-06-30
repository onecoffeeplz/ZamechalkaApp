package dev.onecoffeeplz.zamechalka.domain.repository

interface AudioRepository {
    suspend fun startRecording(): Result<Unit>
    suspend fun stopRecording(): Result<String>
}