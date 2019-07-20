package com.farahaniconsulting.eechat.ui.inbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.farahaniconsulting.eechat.R
import com.farahaniconsulting.eechat.presenter.inbox.InboxPresenter
import com.farahaniconsulting.eechat.ui.common.BaseFragment
import com.farahaniconsulting.eechat.ui.common.extensions.invisible
import com.farahaniconsulting.eechat.ui.common.extensions.visible
import com.farahaniconsulting.eechat.vo.Inbox
import kotlinx.android.synthetic.main.fragment_inbox.*
import kotlinx.android.synthetic.main.fragment_inbox.view.*
import javax.inject.Inject

class InboxFragment @Inject constructor() : BaseFragment(), InboxProviderView {

    @Inject
    lateinit var inboxPresenter: InboxPresenter
    @Inject
    lateinit var inboxRVAdapter: InboxRVAdapter


    override fun layoutId() = R.layout.fragment_inbox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inboxPresenter?.setView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(layoutId(), container, false)

        with(view.inboxListRV) {
            setHasFixedSize(true)
            val manager = LinearLayoutManager(context)
            layoutManager = manager
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        //showProgress()
    }

    private fun initializeView() {
        emptyView.visible()
        inboxPresenter.getInbox()
        inboxListRV.visible()
    }


    override fun showInboxList(inbox: MutableList<Inbox>) {

        inboxRVAdapter?.reloadInbox(inbox)

        inboxListRV.recycledViewPool.setMaxRecycledViews(0,20)
        inboxListRV.adapter = inboxRVAdapter
        inboxRVAdapter?.notifyDataSetChanged()
        emptyView.invisible()
    }

    override fun loadingStarted() {
        notify("loading...")
    }

    override fun loadingFailed(errorMessage: String?) {
        notify(errorMessage.toString())
    }

}