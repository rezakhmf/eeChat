package com.farahaniconsulting.eechat.ui

import android.os.Bundle
import com.farahaniconsulting.eechat.R
import com.farahaniconsulting.eechat.ui.common.BaseActivity
import com.farahaniconsulting.eechat.ui.common.extensions.addFragment
import com.farahaniconsulting.eechat.ui.message.ComposeMessageFragment
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var composeMessageFragment: ComposeMessageFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(composeMessageFragment, R.id.mainContainer)
    }
}
