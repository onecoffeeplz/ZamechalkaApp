package dev.onecoffeeplz.zamechalka.data.utils

import android.media.AudioRecord
import java.io.File
import java.io.RandomAccessFile

// Get from https://github.com/squti/Android-Wave-Recorder
class WavUtils(private val audioRecord: AudioRecord, private val outputFile: File) {

    fun addWavHeader() {
        val totalAudioLen = outputFile.length()
        val totalDataLen = totalAudioLen + 36
        val channels = audioRecord.channelCount
        val byteRate = audioRecord.sampleRate * channels * 16 / 8

        val header = ByteArray(44)

        // RIFF header
        header[0] = 'R'.code.toByte()
        header[1] = 'I'.code.toByte()
        header[2] = 'F'.code.toByte()
        header[3] = 'F'.code.toByte()
        writeInt(header, 4, totalDataLen.toInt())
        header[8] = 'W'.code.toByte()
        header[9] = 'A'.code.toByte()
        header[10] = 'V'.code.toByte()
        header[11] = 'E'.code.toByte()

        // fmt chunk
        header[12] = 'f'.code.toByte()
        header[13] = 'm'.code.toByte()
        header[14] = 't'.code.toByte()
        header[15] = ' '.code.toByte()
        writeInt(header, 16, 16) // Subchunk1Size for PCM
        writeShort(header, 20, 1.toShort()) // AudioFormat PCM
        writeShort(header, 22, channels.toShort())
        writeInt(header, 24, audioRecord.sampleRate)
        writeInt(header, 28, byteRate)
        writeShort(header, 32, (channels * 16 / 8).toShort()) // Block align
        writeShort(header, 34, 16.toShort()) // Bits per sample

        // data chunk
        header[36] = 'd'.code.toByte()
        header[37] = 'a'.code.toByte()
        header[38] = 't'.code.toByte()
        header[39] = 'a'.code.toByte()
        writeInt(header, 40, totalAudioLen.toInt())

        // Add header to the beginning of file
        RandomAccessFile(outputFile, "rw").use { raf ->
            raf.seek(0)
            raf.write(header)
        }
    }

    private fun writeInt(header: ByteArray, offset: Int, value: Int) {
        header[offset] = (value and 0xff).toByte()
        header[offset + 1] = ((value shr 8) and 0xff).toByte()
        header[offset + 2] = ((value shr 16) and 0xff).toByte()
        header[offset + 3] = ((value shr 24) and 0xff).toByte()
    }

    private fun writeShort(header: ByteArray, offset: Int, value: Short) {
        header[offset] = (value.toInt() and 0xff).toByte()
        header[offset + 1] = ((value.toInt() shr 8) and 0xff).toByte()
    }
}