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
    implementation(Kotlin.Coroutine.core)
    implementation(Kotlin.Coroutine.coreAndroid)
    testImplementation(Testing.testJunit)
    androidTestImplementation(Testing.testJunit)
    androidTestImplementation(Testing.espresso)
}