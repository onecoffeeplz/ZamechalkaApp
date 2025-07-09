package dev.onecoffeeplz.zamechalka.di

import android.media.MediaPlayer
import dev.onecoffeeplz.zamechalka.data.repository.AudioPlayerRepositoryImpl
import dev.onecoffeeplz.zamechalka.domain.repository.AudioPlayerRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    factory<MediaPlayer> {
        MediaPlayer()
    }
    singleOf(::AudioPlayerRepositoryImpl) { bind<AudioPlayerRepository>() }
}