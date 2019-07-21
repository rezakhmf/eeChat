package com.farahaniconsulting.eechat.vo

import androidx.room.*
import java.util.*

@Entity(tableName = "message",
    indices = arrayOf(
        Index(value = arrayOf("id"), unique = true),
        Index(value = arrayOf("inbox_id"), unique = false))
        )
data class Message(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "inbox_id")
    val inboxId: Long,
    @ColumnInfo(name = "content")
    val content: String?,
    @ColumnInfo(name = "date")
    val date: String?,
    @ColumnInfo(name = "am_i_sender")
    val amISender: Boolean = false,
    @ColumnInfo(name = "is_read")
    val isRead: Boolean = false
)
