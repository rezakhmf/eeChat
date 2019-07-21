package com.farahaniconsulting.eechat.presenter.message

import androidx.room.withTransaction
import com.farahaniconsulting.eechat.db.EEChatDb
import com.farahaniconsulting.eechat.db.MessageDao
import com.farahaniconsulting.eechat.ui.common.extensions.currentTimeUTC
import com.farahaniconsulting.eechat.ui.message.ComposeMessageProviderView
import com.farahaniconsulting.eechat.vo.Message
import java.util.*
import javax.inject.Inject

class ComposeMessagePresenter @Inject constructor() : ComposeMessagePresenterProvider {

    // Dependencies
    @Inject
    lateinit var db: EEChatDb
    @Inject
    lateinit var messageDao: MessageDao
    var composeMessageProviderView: ComposeMessageProviderView? = null

    override suspend fun sendMessage(message: Message) {
        db.withTransaction {
            messageDao.insert(message)
            //Auto generated response
            val responseMsg = Message(inboxId = message.inboxId,
                content = "your tracking ticket id is: ".plus((0..Int.MAX_VALUE).random().toString()),
                date = Date().currentTimeUTC(),
                isRead = true,
                amISender = false)
            messageDao.insert(responseMsg)
            responseMsg
            val messages = messageDao.findMessageByInbox(inboxId = 0)
            composeMessageProviderView?.showMessageList(messages)
        }
    }

    override suspend fun getMessage() {
        db.withTransaction {
            val messages = messageDao.findMessageByInbox(inboxId = 0)
            composeMessageProviderView?.showMessageList(messages)
        }
    }


    override fun setView(composeMessageProviderView: ComposeMessageProviderView) {
        this.composeMessageProviderView = composeMessageProviderView
    }

    override fun destroy() {
        composeMessageProviderView = null
    }

    private fun onFlightPricesFetchError(throwable: Throwable) {
        if (isViewAttached()) {
            composeMessageProviderView?.loadingFailed(throwable.message)
        } else {
            // do nothing
        }
    }

    private fun isViewAttached(): Boolean {
        return composeMessageProviderView != null
    }

}
