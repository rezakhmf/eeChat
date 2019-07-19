package com.farahaniconsulting.eechat.di

import com.farahaniconsulting.eechat.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityBuilder {

    @ContributesAndroidInjector(modules = [])
    abstract fun bindMainActivity(): MainActivity
}