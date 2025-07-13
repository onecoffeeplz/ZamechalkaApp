package dev.onecoffeeplz.zamechalka.data.source.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val path: String,
    val duration: Long,
    val text: String?,
    val tags: String?,
    val createdAt: Long,
)
