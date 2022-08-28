package com.jiayx.component.project.startup

import android.content.Context
import androidx.startup.Initializer

/**
 *  author : Jia yu xi
 *  date : 2022/8/28 16:19:19
 *  description : 初始化 参数
 */
class MyInitializer : Initializer<Unit> {
    override fun create(context: Context) {

    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }

}