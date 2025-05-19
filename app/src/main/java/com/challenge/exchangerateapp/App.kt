package com.challenge.exchangerateapp

import android.app.Application
import com.challenge.exchangerateapp.core.di.coreModule
import com.challenge.exchangerateapp.core.di.databaseModule
import com.challenge.exchangerateapp.core.di.networkModule
import com.challenge.exchangerateapp.di.appModule
import com.challenge.exchangerateapp.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                databaseModule,
                networkModule,
                coreModule,
                domainModule,
                appModule
            )
        }
    }
}