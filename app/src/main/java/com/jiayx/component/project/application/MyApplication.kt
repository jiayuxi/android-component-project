package com.jiayx.component.project.application

import android.app.Application
import android.util.Log

/**
 *  author : Jia yu xi
 *  date : 2022/8/28 16:27:27
 *  description :
 */
class MyApplication : Application()  {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d("jia_application", "onTerminate: 程序销毁")
    }
}