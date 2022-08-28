plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = VersionConfig.compileSdk

    defaultConfig {
        minSdk = VersionConfig.minSdk
        targetSdk = VersionConfig.targetSdk

        testInstrumentationRunner = VersionConfig.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    // XML资源文件防重名冲突，规定资源文件必须要以指定名称作为前缀命名
    resourcePrefix = "lib_common_"
}

dependencies {

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appcompat)
    implementation(AndroidX.appcompatResources)
    implementation(Google.material)
    //协程
    api(Kotlin.Coroutine.core)
    api(Kotlin.Coroutine.coreAndroid)
    //
    api(AndroidX.cardview)
    api(AndroidX.activityKtx)
    api(AndroidX.fragmentKtx)
    api(AndroidX.swiperefreshlayout)
    api(AndroidX.collectionKtx)
    api(AndroidX.constraintlayout)
    api(AndroidX.gridLayout)
    api(AndroidX.recyclerView)
    api(AndroidX.recyclerViewSelection)
    // viewPage
    api(AndroidX.ViewPager.viewpager2)
    // room
    api(AndroidX.Room.roomKtx)
    api(AndroidX.Room.roomPaging)
    // lifecycle
    api(AndroidX.Lifecycle.compiler)
    api(AndroidX.Lifecycle.viewModelKtx)
    api(AndroidX.Lifecycle.viewModelSavedState)
    api(AndroidX.Lifecycle.runtimeKtx)
    api(AndroidX.Lifecycle.liveDataKtx)
    api(AndroidX.Lifecycle.java8)
    api(AndroidX.Lifecycle.service)
    api(AndroidX.Lifecycle.process)
    //
    api(AndroidX.Paging3.runtime)
    api(AndroidX.Paging3.common)
    //
    api(Google.material)
    //
    api(AndroidX.Navigation.runtime)
    api(AndroidX.Navigation.fragment)
    api(AndroidX.Navigation.uiKtx)
    //
    api(ThirdPart.RxJava3.rxJava)
    api(ThirdPart.RxJava3.rxAndroid)
    api(ThirdPart.rxAndroidBle)
    api(ThirdPart.rxjavaReplayingShare)
    //
    api(ThirdPart.Coil.coilKtx)
    api(ThirdPart.Coil.coilGifKtx)
    api(ThirdPart.Coil.coilSvgKtx)
    //
    api(ThirdPart.MaterialDialogs.color)
    api(ThirdPart.MaterialDialogs.bottomSheets)
    api(ThirdPart.MaterialDialogs.core)
    api(ThirdPart.MaterialDialogs.lifecycle)
//    api(ThirdPart.bannerVp)
    api(ThirdPart.immersionBarKtx)
    api(ThirdPart.refreshLayoutKernel)
    api(ThirdPart.refreshHeader)
//    api(ThirdPart.baseRecycleViewHelper)
    api(ThirdPart.xRecycleView)
//    api(ThirdPart.magicIndicator)
    api(ThirdPart.autoSize)
    api(ThirdPart.loadSir)
    api(ThirdPart.backgroundLibrary)
    api(ThirdPart.mmkv)
    //
    api(ThirdPart.utilCodex)

    testImplementation(Testing.testJunit)
    androidTestImplementation(Testing.androidJunit)
    androidTestImplementation(Testing.espresso)
}