package dev.onecoffeeplz.zamechalka

import android.app.Application
import dev.onecoffeeplz.zamechalka.di.appModule
import dev.onecoffeeplz.zamechalka.di.dataModule
import dev.onecoffeeplz.zamechalka.di.domainModule
import dev.onecoffeeplz.zamechalka.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    dataModule,
                    domainModule,
                    presentationModule,
                )
            )
        }
    }
}