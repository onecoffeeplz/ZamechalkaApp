package dev.onecoffeeplz.zamechalka.data.repository

import android.Manifest
import android.content.Context
import android.media.AudioRecord
import androidx.annotation.RequiresPermission
import dev.onecoffeeplz.zamechalka.data.source.local.audio.AudioRecordFactory
import dev.onecoffeeplz.zamechalka.data.utils.WavUtils
import dev.onecoffeeplz.zamechalka.domain.repository.AudioRepository
import java.io.File
import java.io.FileOutputStream

class AudioRepositoryImpl(
    private val audioRecordFactory: AudioRecordFactory,
    private val context: Context,
) : AudioRepository {
    private var audioRecord: AudioRecord? = null
    private var outputFile: File? = null

    @RequiresPermission(Manifest.permission.RECORD_AUDIO)
    override suspend fun startRecording(): Result<Unit> = runCatching {
        val fileName = "recording_${System.currentTimeMillis()}.wav"
        val dir = File(context.filesDir, "audio")
        if (!dir.exists()) {
            dir.mkdirs()
        }
        outputFile = File(dir, fileName)

        audioRecord = audioRecordFactory.create()
        audioRecord?.startRecording()

        Thread {
            writeAudioDataToFile()
        }.start()
    }

    override suspend fun stopRecording(): Result<String> = runCatching {
        WavUtils(audioRecord!!, outputFile!!).addWavHeader()

        audioRecord?.let {
            it.stop()
            it.release()
        }
        audioRecord = null

        outputFile?.absolutePath ?: throw IllegalStateException("Output file was not created")
    }

    private fun writeAudioDataToFile() {
        val bufferSize = audioRecord!!.bufferSizeInFrames
        val buffer = ByteArray(bufferSize)
        FileOutputStream(outputFile).use { os ->
            while (audioRecord?.recordingState == AudioRecord.RECORDSTATE_RECORDING) {
                val read = audioRecord?.read(buffer, 0, buffer.size) ?: 0
                if (read > 0) {
                    os.write(buffer, 0, read)
                }
            }
        }
    }
}
