package com.api.pokemones

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.kotzilla.sdk.analytics.koin.analytics
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

@HiltAndroidApp
class MyApp : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            analytics()
        }
    }

}