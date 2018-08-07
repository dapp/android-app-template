/*
 *
 *  * Copyright 2018 Mark Dappollone
 *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 *
 */

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