package com.dapp.template.presenter

import com.dapp.template.model.MainActivityModel
import com.dapp.template.model.SearchResults
import com.dapp.template.utils.Schedulers
import com.dapp.template.view.MainView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

class MainPresenter @Inject constructor(private val model: MainActivityModel,
                                        private val schedulers: Schedulers) {
    private var disposable: Disposable? = null
    private lateinit var view: MainView
    private var modelData: SearchResults? = null

    fun attachView(view: MainView) {
        this.view = view
    }

    fun onResume() {
        model.loadData("seinfeld").subscribeOn(schedulers.ioThread)
                .observeOn(schedulers.mainThread)
                .subscribe(object: Observer<SearchResults> {
                    override fun onComplete() { }

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onNext(t: SearchResults) {
                        modelData = t
                        present()
                    }

                    override fun onError(e: Throwable) {
                        Timber.e("Something went wrong: ${e.message}")
                    }

                })
    }

    fun onPause() {
        disposable?.dispose()
    }

    fun present() {
        modelData?.relatedTopics?.firstOrNull()?.text?.let {
            view.setMessageText(it)
        }
    }
}