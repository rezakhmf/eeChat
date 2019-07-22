package com.farahaniconsulting.eechat.di

import com.farahaniconsulting.eechat.ui.MainActivity
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun bindMainActivity(): MainActivity {
        return MainActivity()
    }

}