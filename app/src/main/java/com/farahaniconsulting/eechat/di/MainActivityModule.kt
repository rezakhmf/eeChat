package com.farahaniconsulting.eechat.di

import com.farahaniconsulting.eechat.ui.inbox.MainActivity
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun bindMainActivity(mainActivity: MainActivity): MainActivity {
        return MainActivity()
    }

}