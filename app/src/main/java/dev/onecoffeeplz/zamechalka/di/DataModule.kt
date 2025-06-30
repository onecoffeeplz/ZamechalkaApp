package dev.onecoffeeplz.zamechalka.di

import androidx.room.Room
import dev.onecoffeeplz.zamechalka.data.repository.AudioRepositoryImpl
import dev.onecoffeeplz.zamechalka.data.repository.NoteRepositoryImpl
import dev.onecoffeeplz.zamechalka.data.source.local.audio.AudioRecordFactory
import dev.onecoffeeplz.zamechalka.data.source.local.db.AppDatabase
import dev.onecoffeeplz.zamechalka.domain.repository.AudioRepository
import dev.onecoffeeplz.zamechalka.domain.repository.NoteRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "zamechalka_app.db"
        ).build()
    }
    single { get<AppDatabase>().noteDao() }

    single { AudioRecordFactory() }
    singleOf(::AudioRepositoryImpl) { bind<AudioRepository>() }

    factoryOf(::NoteRepositoryImpl) { bind<NoteRepository>() }

}