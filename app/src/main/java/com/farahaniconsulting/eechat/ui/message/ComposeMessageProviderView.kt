package com.farahaniconsulting.eechat.ui.message

import com.farahaniconsulting.eechat.vo.Message

interface ComposeMessageProviderView {
    fun showMessageList(messages: List<Message>)
    fun loadingStarted()
    fun loadingFailed(errorMessage: String?)
}