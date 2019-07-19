package com.farahaniconsulting.eechat.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Message",
    foreignKeys = arrayOf(
        ForeignKey(
        entity = Inbox::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("inbox_id")
        )
    )
)
data class Message(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "inbox_id")
    val inboxId: Long,
    @ColumnInfo(name = "content")
    val content: String?,
    @ColumnInfo(name = "date")
    val date: Date?,
    @ColumnInfo(name = "am_i_sender")
    val amISender: Boolean = false,
    @ColumnInfo(name = "is_read")
    val isRead: Boolean = false
)