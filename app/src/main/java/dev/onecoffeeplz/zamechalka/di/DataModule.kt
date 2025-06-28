package dev.onecoffeeplz.zamechalka.di

import androidx.room.Room
import dev.onecoffeeplz.zamechalka.data.source.local.db.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "zamechalka_app.db"
        ).build()
    }

}