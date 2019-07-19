package com.farahaniconsulting.eechat.di

import android.content.Context
import com.farahaniconsulting.eechat.EEChatApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(MainActivityBuilder::class))
class EEChatAppModule {

    @Provides
    @Singleton
    fun provideContext(app: EEChatApp) : Context {
        return app.applicationContext
    }
}