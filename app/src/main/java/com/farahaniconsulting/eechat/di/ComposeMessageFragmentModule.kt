package com.farahaniconsulting.eechat.di

import com.farahaniconsulting.eechat.presenter.message.ComposeMessagePresenter
import com.farahaniconsulting.eechat.presenter.message.ComposeMessagePresenterProvider
import com.farahaniconsulting.eechat.ui.message.ComposeMessageFragment
import com.farahaniconsulting.eechat.ui.message.ComposeMessageProviderView
import com.farahaniconsulting.eechat.ui.message.ComposeMessageRVAdapter
import dagger.Module
import dagger.Provides

@Module
class ComposeMessageFragmentModule {

    @Provides
    fun provideComposeMessagePresenter() : ComposeMessagePresenterProvider {
        return ComposeMessagePresenter()
    }

    @Provides
    fun provideComposeMessageRVAdaptor() : ComposeMessageRVAdapter {
        return ComposeMessageRVAdapter()
    }

    @Provides
    fun provideComposeMessageProviderView(composeMessageFragment: ComposeMessageFragment) : ComposeMessageProviderView {
        return composeMessageFragment
    }
}