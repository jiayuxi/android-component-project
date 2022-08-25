plugins {
    id("com.android.application")
    id("kotlin-android") //kotlin("android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android{
    compileSdk = VersionConfig.compileSdk
    defaultConfig{
        applicationId = VersionConfig.applicationId
        minSdk= VersionConfig.minSdk
        targetSdk = VersionConfig.targetSdk
        versionCode = VersionConfig.versionCode
        versionName = VersionConfig.versionName
        testInstrumentationRunner = VersionConfig.testInstrumentationRunner
        multiDexEnabled = true
        ndk {
            //不配置则默认构建并打包所有可用的ABI
            //same with gradle-> abiFilters 'x86_64','armeabi-v7a','arm64-v8a'
            abiFilters.addAll(arrayListOf("armeabi-v7a"))
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget =  "1.8"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    // appcompat
    implementation(AndroidX.appcompat)
    implementation(AndroidX.appcompatResources)
    // 测试
    testImplementation (Testing.testJunit)
    androidTestImplementation (Testing.androidJunit)
    androidTestImplementation (Testing.espresso)
}