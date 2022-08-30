plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
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
}

dependencies {
    implementation(AndroidX.coreKtx)
    implementation(Kotlin.Coroutine.core)
    implementation(Kotlin.Coroutine.coreAndroid)
    //
    implementation(ThirdPart.OkHttp.okhttp)
    implementation(ThirdPart.OkHttp.loggingInterceptor)
    //
    implementation(ThirdPart.Retrofit.retrofit)
    implementation(ThirdPart.Retrofit.convertGson)
    //
    implementation(ThirdPart.RxJava3.rxJava)
    implementation(ThirdPart.RxJava3.rxAndroid)
    //
    testImplementation(Testing.testJunit)
    androidTestImplementation(Testing.testJunit)
    androidTestImplementation(Testing.espresso)
}