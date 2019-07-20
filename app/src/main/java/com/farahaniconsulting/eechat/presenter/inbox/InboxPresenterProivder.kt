package com.farahaniconsulting.eechat.presenter.inbox

import com.farahaniconsulting.eechat.ui.inbox.InboxProviderView

interface InboxPresenterProivder {
    fun getInbox()
    fun setView(inboxProviderView: InboxProviderView)
    fun destroy()
}