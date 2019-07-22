package com.farahaniconsulting.eechat.ui.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.farahaniconsulting.eechat.R
import com.farahaniconsulting.eechat.presenter.message.ComposeMessagePresenter
import com.farahaniconsulting.eechat.ui.common.BaseFragment
import com.farahaniconsulting.eechat.ui.common.extensions.currentTimeUTC
import com.farahaniconsulting.eechat.ui.common.extensions.invisible
import com.farahaniconsulting.eechat.ui.common.extensions.visible
import com.farahaniconsulting.eechat.vo.Message
import kotlinx.android.synthetic.main.message_fragment.*
import kotlinx.android.synthetic.main.message_fragment.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class ComposeMessageFragment @Inject constructor() : BaseFragment(), ComposeMessageProviderView {


    @Inject
    lateinit var composeMessagePresenter: ComposeMessagePresenter
    @Inject
    lateinit var composeMessageRVAdapter: ComposeMessageRVAdapter


    override fun layoutId() = R.layout.message_fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        composeMessagePresenter.setView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(layoutId(), container, false)

        with(view.messageListRV) {
            setHasFixedSize(true)
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        sendMessage.setOnClickListener { view ->
            val inboxId = arguments?.getLong("inboxId", 0L)

            inboxId?.let {
                val msg = Message(inboxId = it, content = userMessage.text.toString(), amISender = true, isRead = false, date = Date().currentTimeUTC())
                GlobalScope.launch {
                    composeMessagePresenter.sendMessage(msg)
                }
            }
        }
    }

    private fun initializeView() {
        GlobalScope.launch {
            composeMessagePresenter.getMessage()
        }
        messageListRV.visible()
    }


    override fun showMessageList(messages: List<Message>) {

        GlobalScope.launch (Dispatchers.Main) {
            composeMessageRVAdapter.reloadMessage(messages)

            personPhoneNumber.invisible()

            messageListRV.recycledViewPool.setMaxRecycledViews(0,20)
            messageListRV.adapter = composeMessageRVAdapter
            messageListRV.scrollToPosition(messages.size - 1)
        }
    }

    override fun loadingStarted() {
        notify("loading...")
    }

    override fun loadingFailed(errorMessage: String?) {
        notify(errorMessage.toString())
    }

}