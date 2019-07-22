package com.farahaniconsulting.eechat.ui.message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farahaniconsulting.eechat.R
import com.farahaniconsulting.eechat.ui.inbox.ListItem
import com.farahaniconsulting.eechat.vo.Message
import kotlinx.android.synthetic.main.compose_message_sender_item.view.*
import kotlinx.android.synthetic.main.inbox_item.view.textMessageDate
import java.util.*
import javax.inject.Inject
import kotlin.properties.Delegates


class ComposeMessageRVAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>(), AutoUpdatableAdapter {

    private var context: Context? = null

    var messageList: List<Message> by Delegates.observable(emptyList()) {
            prop, old, new ->
        autoNotify(old, new) { o, n -> o.id == n.id }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        context = parent.context
        val layoutInflater = LayoutInflater.from(context)

        when (viewType) {
            ListItem.TYPE_MESSAGE_SENDER_INFO -> {
                val messageItemView = layoutInflater.inflate(R.layout.compose_message_sender_item, parent, false)
                return MessageItemViewHolder(messageItemView)
            }
            ListItem.TYPE_MESSAGE_RECEIVER_INFO -> {
                val messageItemView = layoutInflater.inflate(R.layout.compose_message_reciever_item, parent, false)
                return MessageItemViewHolder(messageItemView)
            }
            else -> throw IllegalStateException("unsupported view type")
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun getItemViewType(position: Int): Int {
        if ((position+1) % 2 != 0) {
            return ListItem.TYPE_MESSAGE_SENDER_INFO
        } else {
            return ListItem.TYPE_MESSAGE_RECEIVER_INFO
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val messageInfo = messageList[position]
        val viewType = getItemViewType(position)

        when (viewType) {
            ListItem.TYPE_MESSAGE_SENDER_INFO -> {
                holder.itemView.textMessage.text = messageInfo.content
                holder.itemView.textMessageDate.text = messageInfo.date
            }
            ListItem.TYPE_MESSAGE_RECEIVER_INFO -> {
                holder.itemView.textMessage.text = messageInfo.content
                holder.itemView.textMessageDate.text = messageInfo.date
            }
        }
    }

    fun reloadMessage(messageList: List<Message>) {
        this.messageList = messageList

    }

    class MessageItemViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val personName = itemView.textMessage
        val lastMessage = itemView.textMessageDate
    }

}