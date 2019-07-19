package com.farahaniconsulting.eechat.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.farahaniconsulting.eechat.vo.Inbox

@Dao
interface InboxDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(inbox: Inbox)

    @Query("SELECT * FROM inbox")
    fun getAllInbox(): LiveData<Array<Inbox>>
}