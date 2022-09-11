package com.jiayx.component.project.startup

import android.content.Context
import androidx.startup.Initializer
import com.tencent.mmkv.MMKV

/**
 *  author : Jia yu xi
 *  date : 2022/8/28 16:19:19
 *  description : 初始化 参数
 */
class MyInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        MMKV.initialize(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }

}