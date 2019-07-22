package com.farahaniconsulting.eechat.di

import com.farahaniconsulting.eechat.ui.MainActivity
import com.farahaniconsulting.eechat.ui.message.ComposeMessageFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity



    @ContributesAndroidInjector(modules = [ComposeMessageFragmentModule::class])
    abstract fun bindComposeMessageFragment(): ComposeMessageFragment
}