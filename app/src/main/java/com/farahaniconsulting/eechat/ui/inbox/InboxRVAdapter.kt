package com.farahaniconsulting.eechat.ui.inbox

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farahaniconsulting.eechat.R
import com.farahaniconsulting.eechat.vo.Inbox
import kotlinx.android.synthetic.main.inbox_item.view.*
import java.util.*
import javax.inject.Inject

class InboxRVAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var context: Context? = null
    private var inboxList = Collections.emptyList<Inbox>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        context = parent?.context
        val layoutInflater = LayoutInflater.from(context)

        when (viewType) {
            ListItem.TYPE_INBOX_INFO -> {
                val inboxItemView = layoutInflater.inflate(R.layout.inbox_item, parent, false)
                return InboxItemViewHolder(inboxItemView)
            }
            else -> throw IllegalStateException("unsupported view type")
        }
    }

    override fun getItemCount(): Int {
        return inboxList.size
    }

    override fun getItemViewType(position: Int): Int {
        return ListItem.TYPE_INBOX_INFO
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val inboxInfo = inboxList.get(position)
        val viewType = getItemViewType(position)

        when (viewType) {
            ListItem.TYPE_INBOX_INFO -> {
                holder.itemView.personName.text = inboxInfo.personName
                holder.itemView.lastMessage.text = inboxInfo.lastMessageContent
                holder.itemView.lastMessageDate.text = inboxInfo.lastMessageDate
            }
        }
    }

    fun reloadInbox(inboxList: MutableList<Inbox>) {
        this.inboxList.clear()
        this.inboxList = inboxList
    }

    class InboxItemViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val personName = itemView.personName
        val lastMessage = itemView.lastMessage
        val lastMessageDate = itemView.lastMessageDate
    }

}