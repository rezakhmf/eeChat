package com.farahaniconsulting.eechat.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.farahaniconsulting.eechat.vo.Message


@Database(
    entities = [
        Message::class],
    version = 1,
    exportSchema = false)
abstract class EEChatDb: RoomDatabase() {

    abstract fun messDao(): MessageDao
}