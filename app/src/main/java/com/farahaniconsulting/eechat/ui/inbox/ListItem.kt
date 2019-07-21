package com.farahaniconsulting.eechat.ui.inbox

abstract class ListItem {

    abstract val type: Int

    companion object {
        val TYPE_INBOX_INFO = 0
        val TYPE_MESSAGE_SENDER_INFO = 1
        val TYPE_MESSAGE_RECEIVER_INFO = 2
    }
}