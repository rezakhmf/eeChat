package com.farahaniconsulting.eechat.ui.inbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.farahaniconsulting.eechat.R
import com.farahaniconsulting.eechat.presenter.inbox.InboxPresenter
import com.farahaniconsulting.eechat.ui.common.BaseFragment
import com.farahaniconsulting.eechat.ui.common.extensions.currentTimeUTC
import com.farahaniconsulting.eechat.ui.common.extensions.invisible
import com.farahaniconsulting.eechat.ui.common.extensions.replaceFragment
import com.farahaniconsulting.eechat.ui.common.extensions.visible
import com.farahaniconsulting.eechat.ui.message.ComposeMessageFragment
import com.farahaniconsulting.eechat.vo.Inbox
import kotlinx.android.synthetic.main.inbox_fragment.*
import kotlinx.android.synthetic.main.inbox_fragment.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class InboxFragment @Inject constructor() : BaseFragment(), InboxProviderView {

    @Inject
    lateinit var inboxPresenter: InboxPresenter
    @Inject
    lateinit var inboxRVAdapter: InboxRVAdapter
    @Inject
    lateinit var composeMessageFragment: ComposeMessageFragment


    override fun layoutId() = R.layout.inbox_fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inboxPresenter.setView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(layoutId(), container, false)

        with(view.inboxListRV) {
            setHasFixedSize(true)
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    private fun initializeView() {

        GlobalScope.launch {
            inboxPresenter.getInbox()
        }
        emptyView.visible()
        inboxListRV.invisible()
        composeMessageBtn.setOnClickListener { view ->

            GlobalScope.launch {
                val inbox = Inbox(
                    personImageUrl = "",
                    personName = "user: " + (0..Int.MAX_VALUE).random().toString(),
                    lastMessageContent = "",
                        lastMessageDate = Date().currentTimeUTC()
                )
                inboxPresenter.insertInbox(inbox)
            }
        }
    }

    override fun showMessageComposer() {
        val inboxArgs = Bundle()
        inboxArgs.putLong("inboxId", 0L)
        composeMessageFragment.arguments = inboxArgs
        getBaseActivity()?.replaceFragment(composeMessageFragment, R.id.mainContainer)
    }

    override fun showInboxList(inbox: List<Inbox>) {

        if (inbox.isEmpty()) return

        GlobalScope.launch (Dispatchers.Main) {

            inboxRVAdapter.reloadInbox(inbox)

            inboxListRV.recycledViewPool.setMaxRecycledViews(0, 20)
            inboxListRV.adapter = inboxRVAdapter
            inboxRVAdapter.notifyDataSetChanged()
            emptyView.invisible()
            inboxListRV.visible()
        }
    }

    override fun loadingStarted() {
        notify("loading...")
    }

    override fun loadingFailed(errorMessage: String?) {
        notify(errorMessage.toString())
    }

}