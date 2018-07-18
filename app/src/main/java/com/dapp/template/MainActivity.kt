package com.dapp.template

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.dapp.howstheweather.R
import com.dapp.template.model.MainActivityModel
import com.dapp.template.model.SearchResults
import dagger.android.AndroidInjection
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject lateinit var model: MainActivityModel

    private var disposable: Disposable? = null
    private var textMessage: TextView? = null

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                textMessage!!.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                textMessage!!.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                textMessage!!.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        setContentView(R.layout.activity_main)

        textMessage = findViewById<View>(R.id.message) as TextView?
        val navigation = findViewById<View>(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    override fun onResume() {
        super.onResume()
        model.loadData("seinfeld").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: Observer<SearchResults> {
                    override fun onComplete() { }

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onNext(t: SearchResults) {
                        textMessage?.text = t.relatedTopics.firstOrNull()?.text
                    }

                    override fun onError(e: Throwable) {
                        Timber.e("Something went wrong: ${e.message}")
                    }

                })
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}