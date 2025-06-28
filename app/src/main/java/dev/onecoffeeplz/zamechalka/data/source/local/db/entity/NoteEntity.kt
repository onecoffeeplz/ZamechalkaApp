package dev.onecoffeeplz.zamechalka.data.source.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String? = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
    val path: String,
    val duration: Long,
    val text: String?,
    val tags: String?,
    val createdAt: Long = System.currentTimeMillis(),
)
