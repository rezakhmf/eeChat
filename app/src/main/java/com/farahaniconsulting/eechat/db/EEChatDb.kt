package com.farahaniconsulting.eechat.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.farahaniconsulting.eechat.vo.Inbox
import com.farahaniconsulting.eechat.vo.Message


@Database(
    entities = [
        Inbox::class,
        Message::class
                ], version = 0, exportSchema = false)
abstract class EEChatDb: RoomDatabase() {

    abstract fun inboxDao():
    abstract fun repoDao():
}