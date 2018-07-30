package com.dapp.template.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

interface Schedulers {
    val ioThread: Scheduler
    val mainThread: Scheduler
}

class MySchedulers : Schedulers {
    override val ioThread = io.reactivex.schedulers.Schedulers.io()
    override val mainThread: Scheduler = AndroidSchedulers.mainThread()
}