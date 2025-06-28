package dev.onecoffeeplz.zamechalka.data.source.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val path: String,
    val text: String?,
    val tags: String?,
    val createdAt: Long = System.currentTimeMillis(),
)
