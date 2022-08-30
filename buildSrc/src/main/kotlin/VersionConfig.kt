import org.gradle.internal.file.impl.DefaultFileMetadata.file
import java.text.SimpleDateFormat
import java.util.*

/**
 *  author : Jia yu xi
 *  date : 2022/8/24 21:50:50
 *  description :
 */
object VersionConfig {
    const val compileSdk = 33
    const val applicationId = "com.jiayx.component.project"
    const val  minSdk = 25
    const val  targetSdk = 33

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    const val versionCode = 100000
    const val versionName = "1.0.0"
    //
    val date: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    object SigningConfig{
        //kts下必须用这种路径，不然debug会找不到签名
        const val KEYSTORE_FILE= "../keystore/component.jks"
        const val STORE_PASSWORD="jia123456"
        const val KEY_ALIAS="component"
        const val KEY_PASSWORD="jia123456"
    }

}