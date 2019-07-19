package com.farahaniconsulting.eechat

import com.farahaniconsulting.eechat.di.DaggerEEChatAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class EEChatApp: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerEEChatAppComponent.factory().create(this)
    }
}