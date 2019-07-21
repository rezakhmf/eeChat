package com.farahaniconsulting.eechat.ui.inbox

import com.farahaniconsulting.eechat.vo.Inbox


interface InboxProviderView {
    fun showInboxList(inbox: List<Inbox>)
    fun loadingStarted()
    fun loadingFailed(errorMessage: String?)
}