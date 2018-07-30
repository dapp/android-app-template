package com.dapp.template.view

import android.widget.TextView

interface MainView {
    fun setMessageText(text: String)
}

class MainActivityView(private val textMessage: TextView) : MainView {
    override fun setMessageText(text: String) {
        textMessage.text = text
    }
}