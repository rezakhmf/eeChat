package com.farahaniconsulting.eechat.di

import com.farahaniconsulting.eechat.EEChatApp
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, EEChatAppModule::class])
interface EEChatAppComponent: AndroidInjector<EEChatApp> {

    @Component.Factory
    abstract class Factory: AndroidInjector.Factory<EEChatApp>
}