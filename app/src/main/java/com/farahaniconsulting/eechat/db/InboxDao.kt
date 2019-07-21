package com.farahaniconsulting.eechat.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.farahaniconsulting.eechat.vo.Inbox

@Dao
interface InboxDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(inbox: Inbox)

    @Query("SELECT * FROM inbox")
    suspend fun getAllInbox(): List<Inbox>
}