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
}

dependencies {

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appcompat)
    implementation(AndroidX.appcompatResources)
    implementation(Google.material)
    implementation(AndroidX.Navigation.uiKtx)
    implementation(AndroidX.Navigation.fragment)
    testImplementation(Testing.testJunit)
    androidTestImplementation(Testing.androidJunit)
    androidTestImplementation(Testing.espresso)
}