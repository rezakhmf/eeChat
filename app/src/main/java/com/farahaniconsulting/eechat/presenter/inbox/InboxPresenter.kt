package com.farahaniconsulting.eechat.presenter.inbox

import com.farahaniconsulting.eechat.db.EEChatDb
import com.farahaniconsulting.eechat.db.InboxDao
import com.farahaniconsulting.eechat.ui.inbox.InboxProviderView
import com.farahaniconsulting.eechat.vo.Inbox
import javax.inject.Inject

class InboxPresenter @Inject constructor() : InboxPresenterProivder {

    // Dependencies
    @Inject
    lateinit var db: EEChatDb
    @Inject
    lateinit var inboxDao: InboxDao
    private var inboxProviderView: InboxProviderView? = null


    override fun getInbox() {
        db.runInTransaction {
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
