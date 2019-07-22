package com.farahaniconsulting.eechat.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.farahaniconsulting.eechat.vo.Message
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class EEChatDatabaseTest {

    private lateinit var messageDao: MessageDao
    private lateinit var eeChatDb: EEChatDb

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        eeChatDb = Room.inMemoryDatabaseBuilder(
            context, EEChatDb::class.java).build()
        messageDao = eeChatDb.messageDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        eeChatDb.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertMessageAndAssertMessageContentInDB() {
        val message: Message = Message(inboxId = 0,
                                        content = "test message",
                                        amISender = true,
                                        isRead = false,
                                        date = "2019/11/12")
        GlobalScope.launch {
            messageDao.insert(message)
            val msg = messageDao.findMessageByInbox(0)
            assertEquals(msg[0].inboxId, message.inboxId)
            assertEquals(msg[0].content, message.content)
            assertEquals(msg[0].amISender, message.amISender)
            assertEquals(msg[0].isRead, message.isRead)
            assertEquals(msg[0].date, message.date)
        }

    }

    @Test
    @Throws(Exception::class)
    fun insertMessageAndAssertMessageContentNotFaultyInDB() {
        val message: Message = Message(inboxId = 0,
            content = "test message",
            amISender = true,
            isRead = false,
            date = "2019/11/12")
        GlobalScope.launch {
            messageDao.insert(message)
            val msg = messageDao.findMessageByInbox(0)
            assertNotEquals(msg[0].inboxId, 1)
            assertNotEquals(msg[0].content,"wrong message")
            assertNotEquals(msg[0].amISender, false)
            assertNotEquals(msg[0].isRead, true)
            assertNotEquals(msg[0].date, "2019/04/03")
        }

    }
}