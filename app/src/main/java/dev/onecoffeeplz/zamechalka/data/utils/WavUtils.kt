package dev.onecoffeeplz.zamechalka.data.utils

import android.media.AudioRecord
import java.io.File
import java.io.RandomAccessFile

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

        // File size minus 8 bytes
        header[4] = (totalDataLen and 0xff).toByte()
        header[5] = ((totalDataLen shr 8) and 0xff).toByte()
        header[6] = ((totalDataLen shr 16) and 0xff).toByte()
        header[7] = ((totalDataLen shr 24) and 0xff).toByte()

        // WAVE header
        header[8] = 'W'.code.toByte()
        header[9] = 'A'.code.toByte()
        header[10] = 'V'.code.toByte()
        header[11] = 'E'.code.toByte()

        // fmt chunk
        header[12] = 'f'.code.toByte()
        header[13] = 'm'.code.toByte()
        header[14] = 't'.code.toByte()
        header[15] = ' '.code.toByte()

        // Subchunk Size (16 for PCM)
        header[16] = 16
        header[17] = 0
        header[18] = 0
        header[19] = 0

        // AudioFormat (1 for PCM)
        header[20] = 1
        header[21] = 0

        // Number of channels
        header[22] = channels.toByte()
        header[23] = 0

        // SampleRate
        header[24] = (audioRecord.sampleRate and 0xff).toByte()
        header[25] = ((audioRecord.sampleRate shr 8) and 0xff).toByte()
        header[26] = ((audioRecord.sampleRate shr 16) and 0xff).toByte()
        header[27] = ((audioRecord.sampleRate shr 24) and 0xff).toByte()

        // ByteRate
        header[28] = (byteRate and 0xff).toByte()
        header[29] = ((byteRate shr 8) and 0xff).toByte()
        header[30] = ((byteRate shr 16) and 0xff).toByte()
        header[31] = ((byteRate shr 24) and 0xff).toByte()

        // BlockAlign
        val blockAlign = (channels * 16 / 8)
        header[32] = blockAlign.toByte()
        header[33] = 0

        // BitsPerSample
        header[34] = 16.toByte()
        header[35] = 0

        // Data chunk
        header[36] = 'd'.code.toByte()
        header[37] = 'a'.code.toByte()
        header[38] = 't'.code.toByte()
        header[39] = 'a'.code.toByte()

        // File size
        header[40] = (totalAudioLen and 0xff).toByte()
        header[41] = ((totalAudioLen shr 8) and 0xff).toByte()
        header[42] = ((totalAudioLen shr 16) and 0xff).toByte()
        header[43] = ((totalAudioLen shr 24) and 0xff).toByte()

        // Add header to the beginning of file
        RandomAccessFile(outputFile, "rw").use { raf ->
            raf.seek(0)
            raf.write(header)
        }
    }
}