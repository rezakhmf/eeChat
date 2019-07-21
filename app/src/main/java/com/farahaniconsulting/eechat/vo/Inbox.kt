package com.farahaniconsulting.eechat.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inbox")
data class Inbox(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val inboxId: Long = 0,
    @ColumnInfo(name = "person_image_url")
    val personImageUrl: String?,
    @ColumnInfo(name = "person_name")
    val personName: String?,
    @ColumnInfo(name = "last_message_content")
    val lastMessageContent: String?,
    @ColumnInfo(name = "last_message_date")
    val lastMessageDate: String?
)