package com.farahaniconsulting.eechat.ui.inbox

abstract class ListItem {

    abstract val type: Int

    companion object {
        val TYPE_INBOX_INFO = 0
    }
}