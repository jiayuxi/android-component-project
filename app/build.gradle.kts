plugins {
    id("com.android.application")
    id("kotlin-android") //kotlin("android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    compileSdk = VersionConfig.compileSdk
    defaultConfig {
        applicationId = VersionConfig.applicationId
        minSdk = VersionConfig.minSdk
        targetSdk = VersionConfig.targetSdk
        versionCode = VersionConfig.versionCode
        versionName = VersionConfig.versionName
        testInstrumentationRunner = VersionConfig.testInstrumentationRunner
        multiDexEnabled = true
        flavorDimensions.add("default")
        ndk {
            //不配置则默认构建并打包所有可用的ABI
            //same with gradle-> abiFilters 'x86_64','armeabi-v7a','arm64-v8a'
            abiFilters.addAll(arrayListOf("armeabi-v7a"))
        }
        kapt {
            arguments {
                arg("AROUTER_MODULE_NAME", project.getName())
            }
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file(VersionConfig.SigningConfig.KEYSTORE_FILE)
            storePassword = VersionConfig.SigningConfig.STORE_PASSWORD
            keyAlias = VersionConfig.SigningConfig.KEY_ALIAS
            keyPassword = VersionConfig.SigningConfig.KEY_PASSWORD
        }
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
        }
        getByName("release") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
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
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    productFlavors {
        create("ComponentTest") {
            dimension = "default"
        }
        create("ComponentProduct") {
            dimension = "default"
        }
    }
    android.applicationVariants.all {
        val buildTypeName = this.buildType.name
        outputs.all {
            if (this is com.android.build.gradle.internal.api.ApkVariantOutputImpl) {
                this.outputFileName =
                    "Component_v${defaultConfig.versionName}_${VersionConfig.date}_${buildTypeName}.apk"
            }
        }
    }
}

dependencies {
    // 引用 jar 和 libs
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    implementation(project(mapOf("path" to ":lib_common")))
    implementation(project(mapOf("path" to ":lib_network")))
    implementation(project(mapOf("path" to ":module_login")))
    implementation(project(mapOf("path" to ":module_map")))
    implementation(project(mapOf("path" to ":lib_mediarecorder")))
    // appcompat
    implementation(AndroidX.appcompat)
    implementation(AndroidX.appcompatResources)
    implementation(AndroidX.startup)
    //
    kapt(ThirdPart.ARoute.compiler)
    // 测试
    testImplementation(Testing.testJunit)
    androidTestImplementation(Testing.androidJunit)
    androidTestImplementation(Testing.espresso)
}