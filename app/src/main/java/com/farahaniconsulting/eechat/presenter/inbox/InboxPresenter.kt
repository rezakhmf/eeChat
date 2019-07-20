package com.farahaniconsulting.eechat.presenter.inbox

import com.farahaniconsulting.eechat.ui.inbox.InboxProviderView
import javax.inject.Inject

class InboxPresenter @Inject constructor() : InboxPresenterProivder {

//    @Inject
//    lateinit var flightPricesInOutBound: FlightInfoBiz // inja call db

    override fun getInbox() {
        // return db result for inbox
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var inboxProviderView: InboxProviderView? = null


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
