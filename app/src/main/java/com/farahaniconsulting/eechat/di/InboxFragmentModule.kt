package com.farahaniconsulting.eechat.di

import com.farahaniconsulting.eechat.presenter.inbox.InboxPresenter
import com.farahaniconsulting.eechat.presenter.inbox.InboxPresenterProivder
import com.farahaniconsulting.eechat.ui.inbox.InboxFragment
import com.farahaniconsulting.eechat.ui.inbox.InboxProviderView
import com.farahaniconsulting.eechat.ui.inbox.InboxRVAdapter
import dagger.Module
import dagger.Provides

@Module
class InboxFragmentModule {

    @Provides
    fun provideInboxPresenter() : InboxPresenterProivder {
        return InboxPresenter()
    }

    @Provides
    fun provideInboxRVAdaptor() : InboxRVAdapter {
        return InboxRVAdapter()
    }

    @Provides
    fun provideInboxProviderView(inboxFragment: InboxFragment) : InboxProviderView {
        return inboxFragment
    }

}