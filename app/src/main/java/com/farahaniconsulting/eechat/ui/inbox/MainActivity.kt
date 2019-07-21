package com.farahaniconsulting.eechat.ui.inbox

import android.os.Bundle
import com.farahaniconsulting.eechat.R
import com.farahaniconsulting.eechat.ui.common.BaseActivity
import com.farahaniconsulting.eechat.ui.common.extensions.addFragment
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var inboxFragment: InboxFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(inboxFragment, R.id.mainContainer)
    }
}
