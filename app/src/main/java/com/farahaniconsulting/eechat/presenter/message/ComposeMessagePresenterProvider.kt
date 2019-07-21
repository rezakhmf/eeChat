package com.farahaniconsulting.eechat.presenter.message

import com.farahaniconsulting.eechat.ui.inbox.InboxProviderView
import com.farahaniconsulting.eechat.ui.message.ComposeMessageProviderView
import com.farahaniconsulting.eechat.vo.Inbox
import com.farahaniconsulting.eechat.vo.Message

interface ComposeMessagePresenterProvider {
    suspend fun sendMessage(message: Message)
    suspend fun getMessage()
    fun setView(composeMessageProviderView: ComposeMessageProviderView)
    fun destroy()
}