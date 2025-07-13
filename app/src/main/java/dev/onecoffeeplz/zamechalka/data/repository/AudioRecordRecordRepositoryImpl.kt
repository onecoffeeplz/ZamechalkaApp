package dev.onecoffeeplz.zamechalka.data.repository

import android.Manifest
import android.content.Context
import android.media.AudioRecord
import androidx.annotation.RequiresPermission
import dev.onecoffeeplz.zamechalka.data.source.local.audio.AudioRecordFactory
import dev.onecoffeeplz.zamechalka.data.utils.WavUtils
import dev.onecoffeeplz.zamechalka.domain.repository.AudioRecordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import java.io.File
import kotlin.coroutines.coroutineContext

class AudioRecordRecordRepositoryImpl(
    private val audioRecordFactory: AudioRecordFactory,
    private val context: Context,
) : AudioRecordRepository {
    private var audioRecord: AudioRecord? = null
    private var outputFile: File? = null
    private var recordingJob: Job? = null

    @RequiresPermission(Manifest.permission.RECORD_AUDIO)
    override suspend fun startRecording(scope: CoroutineScope): Result<Unit> = runCatching {
        val fileName = "recording_${System.currentTimeMillis()}.wav"
        val dir = File(context.filesDir, "audio")
        if (!dir.exists()) {
            dir.mkdirs()
        }
        outputFile = File(dir, fileName)

        audioRecord = audioRecordFactory.create()
            ?: throw IllegalStateException("AudioRecord could not be created")
        audioRecord?.startRecording()

        recordingJob = scope.launch(Dispatchers.IO) {
            writeAudioDataToFile()
        }
    }

    override suspend fun stopRecording(): Result<String> = runCatching {
        val record = audioRecord ?: throw IllegalStateException("AudioRecord is not initialized")
        val file = outputFile ?: throw IllegalStateException("Output file was not created")

        WavUtils(record, file).addWavHeader()

        audioRecord?.let {
            it.stop()
            it.release()
        }
        audioRecord = null

        recordingJob?.cancelAndJoin()
        recordingJob = null

        file.absolutePath ?: throw IllegalStateException("Output file was not created")
    }

    override suspend fun deleteRecording(path: String): Result<Unit> = runCatching {
        val filePath = File(path)
        filePath.delete()
    }

    private suspend fun writeAudioDataToFile() {
        val record = audioRecord ?: return
        val minBufferSize = AudioRecord.getMinBufferSize(
            record.sampleRate,
            record.channelConfiguration,
            record.audioFormat
        )
        val buffer = ByteArray(minBufferSize)

        outputFile?.outputStream().use { os ->
            while (record.recordingState == AudioRecord.RECORDSTATE_RECORDING) {
                coroutineContext.ensureActive()
                val read = record.read(buffer, 0, buffer.size)
                if (read > 0) {
                    os?.write(buffer, 0, read)
                }
            }
        }
    }
}
