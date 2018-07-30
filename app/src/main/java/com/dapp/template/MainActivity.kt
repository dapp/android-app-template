package com.dapp.template

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.dapp.template.presenter.MainPresenter
import com.dapp.template.view.MainActivityView
import com.dapp.template.view.MainView
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject lateinit var presenter: MainPresenter
    private lateinit var view: MainView

    private lateinit var textMessage: TextView

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                textMessage.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                textMessage.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                textMessage.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        setContentView(R.layout.activity_main)

        textMessage = findViewById<View>(R.id.message) as TextView
        val navigation = findViewById<View>(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        view = MainActivityView(textMessage)
    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(view)
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }
}