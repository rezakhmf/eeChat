package com.farahaniconsulting.eechat.ui.common.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.currentTimeUTC() : String {
    val sdf = SimpleDateFormat("hh:mm a")
    val currentDate = sdf.format(Date())
    return currentDate.toString()
}