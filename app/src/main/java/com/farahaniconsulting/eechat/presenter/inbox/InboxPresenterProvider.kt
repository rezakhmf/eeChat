package com.farahaniconsulting.eechat.presenter.inbox

import com.farahaniconsulting.eechat.ui.inbox.InboxProviderView
import com.farahaniconsulting.eechat.vo.Inbox

interface InboxPresenterProvider {
    suspend fun getInbox()
    suspend fun insertInbox(inbox: Inbox)
    fun setView(inboxProviderView: InboxProviderView)
    fun destroy()
}