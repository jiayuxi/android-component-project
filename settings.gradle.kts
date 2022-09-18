pluginManagement {
    repositories {
        maven { setUrl("https://jitpack.io") }
        maven { setUrl("https://maven.rongcloud.cn/repository/maven-releases/") }
        maven { setUrl("https://maven.aliyun.com/repository/public") }
        maven { setUrl("https://maven.aliyun.com/repository/google") }
        maven { setUrl("https://maven.aliyun.com/repository/jcenter") }
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { setUrl("https://jitpack.io") }
        maven { setUrl("https://maven.rongcloud.cn/repository/maven-releases/") }
        maven { setUrl("https://maven.aliyun.com/repository/public") }
        maven { setUrl("https://maven.aliyun.com/repository/google") }
        maven { setUrl("https://maven.aliyun.com/repository/jcenter") }
        google()
        mavenCentral()
    }
}
rootProject.name = "android-component-project"
include(":app")
include(":lib_network")
include(":lib_common")
include(":module_map")
include(":module_login")
include(":lib_navigation")
include(":lib_phone")
include(":lib_mediarecorder")
