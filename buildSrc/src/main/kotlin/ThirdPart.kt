/**
 *  author : Jia yu xi
 *  date : 2022/8/25 23:03:03
 *  description :
 */
object ThirdPart {

    /**网络请求**/
    object Retrofit {
        //网路请求库retrofit
        private const val RETROFIT_VERSION = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"

        //gson转换器
        const val convertGson = "com.squareup.retrofit2:converter-gson:$RETROFIT_VERSION"

        //scalars转换器
        const val convertScalars = "com.squareup.retrofit2:converter-scalars:$RETROFIT_VERSION"

        //
        const val adapterRxjava3 = "com.squareup.retrofit2:adapter-rxjava3:3.0.0"

    }

    object RxJava3 {
        // rxJava3
        const val rxJava = "io.reactivex.rxjava3:rxjava:3.0.4"
        const val rxAndroid = "io.reactivex.rxjava3:rxandroid:3.0.0"
    }

    object OkHttp {
        //okhttp
        private const val version = "4.9.0"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val urlConnection = "com.squareup.okhttp3:okhttp-urlconnection:$version"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    //线圈（Kotlin 协程支持的 Android 图像加载库）
    object Coil {
        // 图片加载框架
        const val coilKtx = "io.coil-kt:coil:2.0.0-rc03"

        //要添加 GIF 支持，请导入扩展库
        const val coilGifKtx = ("io.coil-kt:coil-gif:2.0.0-rc03")

        //要添加 SVG 支持，请导入扩展库
        const val coilSvgKtx = ("io.coil-kt:coil-svg:2.0.0-rc03")
    }

    /** 权限申请*/
    object Permission {
        const val rxPermission = "com.tbruyelle.rxpermissions2:rxpermissions:0.9.3@aar"
        const val xxPermission = "com.github.getActivity:XXPermissions:15.0"
    }

    /*******************************窗口、控件和相关工具***********************************/
    //插入即用的dialog
    //项目地址：https://github.com/afollestad/material-dialogs
    object MaterialDialogs {
        private const val version = "3.3.0"
        const val core = "com.afollestad.material-dialogs:core:$version"
        const val input = "com.afollestad.material-dialogs:input:$version"
        const val color = "com.afollestad.material-dialogs:color:$version"
        const val files = "com.afollestad.material-dialogs:files:$version"
        const val datetime = "com.afollestad.material-dialogs:datetime:$version"
        const val bottomSheets = "com.afollestad.material-dialogs:bottomsheets:$version"
        const val lifecycle = "com.afollestad.material-dialogs:lifecycle:$version"
    }

    /*******************************依赖注入***********************************/
    object DI {
        object Koin {
            private const val koin_version = "2.2.2"

            const val core = "org.koin:koin-core:$koin_version}"
            const val android = "org.koin:koin-android:$koin_version"
            const val androidxViewModel = "org.koin:koin-androidx-viewmodel:$koin_version"
            const val androidScope = "org.koin:koin-android-scope:$$koin_version"
        }

        object Dagger {
            //tip:可搭配Hilt使用
            private const val version = "2.37"
            const val dagger = "com.google.dagger:dagger:$version"

            //use annotationProcessor ,not implementation
            const val compiler = "com.google.dagger:dagger-compiler::$version"
        }
    }

    /*******************************依赖注入***********************************/

    object Map {
        const val navi_3dmap = "com.amap.api:navi-3dmap:latest.integration"
        const val location = "compile 'com.amap.api:location:latest.integration"
        const val searchLocation = "com.amap.api:search:latest.integration"
    }

    //轮播图
    const val bannerVp = "com.github.zhpanvip:bannerviewpager:3.5.6"

    //状态栏
    const val immersionBarKtx = "com.gyf.immersionbar:immersionbar-ktx:3.0.0"

    //上下拉刷新
    const val refreshLayoutKernel = "com.scwang.smart:refresh-layout-kernel:2.0.1"
    const val refreshHeader = "com.scwang.smart:refresh-header-classics:2.0.1"

    //RecycleView适配器工具
    const val baseRecycleViewHelper = "io.github.cymchad:BaseRecyclerViewAdapterHelper:4.0.0-beta01"

    //带侧滑菜单的列表
    const val xRecycleView = "com.yanzhenjie.recyclerview:x:1.3.2"

    //好用的指示器indicator
    const val magicIndicator = "com.github.hackware1993:MagicIndicator:1.7.0"

    //屏幕适配方案
    const val autoSize = "me.jessyan:autosize:1.2.1"

    //界面ui状态管理
    const val loadSir = "com.kingja.loadsir:loadsir:1.3.8"

    //通过标签直接写shape
    const val backgroundLibrary = "com.noober.background:core:1.6.5"

    //常用的工具类
    const val utilCodex = "com.blankj:utilcodex:1.30.0"

    // MMKV
    const val mmkv = "com.tencent:mmkv:1.2.14"

    //腾讯bug上报收集
    const val bugly = "com.tencent.bugly:crashreport_upgrade:latest.release"

    //内存泄露检测库
    //use debugImplementation
    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:2.7"

    //BLE蓝牙操作工具
    const val rxAndroidBle = "com.polidea.rxandroidble2:rxandroidble:1.12.1"

    //Replaying share
    const val rxjavaReplayingShare = "com.jakewharton.rx2:replaying-share:2.2.0"

    //ARouter
    const val aRouter = "com.alibaba:arouter-api:1.5.2"
    const val aRouterCompiler = "com.alibaba:arouter-compiler:1.5.2"

    //Android-Iconics
    const val iconCore = "com.mikepenz:iconics-core:5.3.1"

}