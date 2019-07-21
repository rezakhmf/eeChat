package com.farahaniconsulting.eechat.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.farahaniconsulting.eechat.vo.Inbox
import com.farahaniconsulting.eechat.vo.Message

@Dao
interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(message: Message)

    @Query("SELECT * FROM message WHERE inbox_id = :inboxId")
    suspend fun findMessageByInbox(inboxId: Long): List<Message>
}