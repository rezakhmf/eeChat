package com.farahaniconsulting.eechat.di

import com.farahaniconsulting.eechat.view.MainActivity
import dagger.Component
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityBuilder {

    @ContributesAndroidInjector(modules = [])
    abstract fun bindMainActivity(): MainActivity
}