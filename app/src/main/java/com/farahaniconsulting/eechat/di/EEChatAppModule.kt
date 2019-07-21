package com.farahaniconsulting.eechat.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.farahaniconsulting.eechat.EEChatApp
import com.farahaniconsulting.eechat.db.EEChatDb
import com.farahaniconsulting.eechat.db.InboxDao
import com.farahaniconsulting.eechat.db.MessageDao
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

    @Provides
    @Singleton
    fun provideApplication(app: EEChatApp) : Application {
        return app
    }

    @Provides
    @Singleton
    fun provideDb(app: Application) : EEChatDb {
        return  Room
            .databaseBuilder(app, EEChatDb::class.java, "eechat.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideInboxDao(db: EEChatDb): InboxDao {
        return db.inboxDao()
    }

    @Provides
    @Singleton
    fun provideMessageDao(db: EEChatDb): MessageDao {
        return db.messDao()
    }
}