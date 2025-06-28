package dev.onecoffeeplz.zamechalka.data.mapper

import dev.onecoffeeplz.zamechalka.data.source.local.db.entity.NoteEntity
import dev.onecoffeeplz.zamechalka.domain.model.Note

fun Note.toNoteEntity() = NoteEntity(
    id = this.id,
    name = this.name,
    path = this.path,
    duration = this.duration,
    text = this.text,
    tags = this.tags,
    createdAt = this.createdAt,
)

fun NoteEntity.toNote() = Note(
    id = this.id,
    name = this.name,
    path = this.path,
    duration = this.duration,
    text = this.text,
    tags = this.tags,
    createdAt = this.createdAt,
)