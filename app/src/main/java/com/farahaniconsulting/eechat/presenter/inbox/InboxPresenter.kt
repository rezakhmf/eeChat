package com.farahaniconsulting.eechat.presenter.inbox

import androidx.room.withTransaction
import com.farahaniconsulting.eechat.db.EEChatDb
import com.farahaniconsulting.eechat.db.InboxDao
import com.farahaniconsulting.eechat.ui.inbox.InboxProviderView
import com.farahaniconsulting.eechat.vo.Inbox
import javax.inject.Inject

class InboxPresenter @Inject constructor() : InboxPresenterProvider {

    // Dependencies
    @Inject
    lateinit var db: EEChatDb
    @Inject
    lateinit var inboxDao: InboxDao
    private var inboxProviderView: InboxProviderView? = null

    override suspend fun insertInbox(inbox: Inbox) {
        db.withTransaction {
            inboxDao.insert(inbox)
            inboxProviderView?.showMessageComposer()
        }
    }

    override suspend fun getInbox() {
        db.withTransaction {
            val inboxList = inboxDao.getAllInbox()
            inboxProviderView?.showInboxList(inboxList)
        }
    }

    override fun setView(inboxProviderView: InboxProviderView) {
        this.inboxProviderView = inboxProviderView
    }

    override fun destroy() {
        inboxProviderView = null
    }

    private fun onFlightPricesFetchError(throwable: Throwable) {
        if (isViewAttached()) {
            inboxProviderView?.loadingFailed(throwable.message)
        } else {
            // do nothing
        }
    }

    private fun isViewAttached(): Boolean {
        return inboxProviderView != null
    }

}
