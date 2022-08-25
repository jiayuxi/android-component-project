/**
 *  author : Jia yu xi
 *  date : 2022/8/24 22:22:22
 *  description :
 */
object AndroidX {
    private const val appcompat_version = "1.6.0-beta01"
    const val appcompat = "androidx.appcompat:appcompat:$appcompat_version"

    // For loading and tinting drawables on older versions of the platform
    const val appcompatResources = "androidx.appcompat:appcompat-resources:$appcompat_version"

    // core + ktx 扩展函数
    const val coreKtx = "androidx.core:core-ktx:1.8.0"

    // collection + ktx 扩展函数
    //Collection 扩展程序包含在 Android 的节省内存的集合库中使用的实用函数，包括 ArrayMap、LongSparseArray、LruCache 等等。
    const val collectionKtx = "androidx.collection:collection-ktx:1.2.0"

    // Kotlin activity
    private const val activity_version = "1.6.0-rc01"
    const val activityKtx = "androidx.activity:activity-ktx:$activity_version"

    // kotlin fragment
    private const val fragment_version = "1.6.0-alpha02"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:$fragment_version"

    //约束布局
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.1.4"

    //卡片控件
    const val cardview = "androidx.cardview:cardview:1.0.0"

    //recyclerView
    const val recyclerView = "androidx.recyclerview:recyclerview:1.2.1"

    // For control over item selection of both touch and mouse driven selection
    const val recyclerViewSelection = "androidx.recyclerview:recyclerview-selection:1.1.0"

    //swiperefreshlayout
    const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01"

    // startup
    const val startup = "androidx.startup:startup-runtime:1.2.0-alpha01"

    // 实现网格布局
    const val gridLayout = "androidx.gridlayout:gridlayout:1.0.0"

    object WorkManage {
        private const val work_version = "2.7.1"

        // Kotlin + coroutines
        const val workKtx = "androidx.work:work-runtime-ktx:$work_version"

        // optional - RxJava2 support
        const val workRxjava = "androidx.work:work-rxjava2:$work_version"
    }

    object ViewPager {
        const val viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"
    }

    object Room {
        private const val room_version = "2.4.3"
        const val roomKtx = ("androidx.room:room-ktx:$room_version")
        const val roomPaging = ("androidx.room:room-paging:2.5.0-alpha02")
    }

    object Paging3 {
        private const val paging_version = "3.1.1"
        const val runtime = ("androidx.paging:paging-runtime:$paging_version")

        // for java use annotationProcessor , for kotlin use kapt
        const val common = ("androidx.paging:paging-common:$paging_version")
    }

    object Navigation {
        private const val nav_version = "2.5.1"
        const val runtime = ("androidx.navigation:navigation-runtime-ktx:2.5.1")
        const val fragment = ("androidx.navigation:navigation-fragment-ktx:$nav_version")
        const val uiKtx = ("androidx.navigation:navigation-ui-ktx:$nav_version")

    }

    object Lifecycle {
        private const val lifecycle_version = "2.6.0-alpha01"

        // ViewModel
        const val viewModelKtx = ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")

        // ViewModel utilities for Compose
        const val compose = ("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")

        // LiveData
        const val liveDataKtx = ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

        // Lifecycles only (without ViewModel or LiveData)
        const val runtimeKtx = ("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")

        // Saved state module for ViewModel
        const val viewModelSavedState =
            ("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")

        ///Annotation processor 注释处理器
        //use kapt,not implementation
        const val compiler = ("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")

        // alternately - if using Java8, use the following instead of lifecycle-compiler
        const val java8 = ("androidx.lifecycle:lifecycle-common-java8:$lifecycle_version")

        // optional - helpers for implementing LifecycleOwner in a Service
        const val service = ("androidx.lifecycle:lifecycle-service:$lifecycle_version")

        // optional - ProcessLifecycleOwner provides a lifecycle for the whole application process
        const val process = ("androidx.lifecycle:lifecycle-process:$lifecycle_version")
    }

    object Hilt {
        // hilt
        const val common = ("com.google.dagger:hilt-android:2.38.1")
        // kapt
        const val androidCompiler = ("com.google.dagger:hilt-android-compiler:2.38.1")
        // viewModel
        const val lifecycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha01"
        // When using Java annotationProcessor kapt
        const val compiler = "androidx.hilt:hilt-compiler:1.0.0-alpha01"
        const val gradlePlugin = ("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
    }
}