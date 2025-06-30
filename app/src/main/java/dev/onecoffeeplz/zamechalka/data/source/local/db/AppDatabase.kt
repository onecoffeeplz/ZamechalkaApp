package dev.onecoffeeplz.zamechalka.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.onecoffeeplz.zamechalka.data.source.local.db.dao.NoteDao
import dev.onecoffeeplz.zamechalka.data.source.local.db.entity.NoteEntity

@Database(version = 1, entities = [NoteEntity::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}