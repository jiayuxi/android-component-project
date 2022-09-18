package com.jiayx.component.project.activity

import android.hardware.Camera
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jiayx.component.lib_mediarecorder.VideoRecorder
import com.jiayx.component.lib_mediarecorder.VideoRecorderConfig
import com.jiayx.component.project.databinding.ActivityVideoRecordBinding
import java.io.File
import java.util.*


/**
 *  author : Jia yu xi
 *  date : 2022/9/18 16:12:12
 *  description :
 */
class VideoRecord : AppCompatActivity() {
    private val binding by lazy {
        ActivityVideoRecordBinding.inflate(layoutInflater)
    }
    private lateinit var videoRecorder: VideoRecorder
    private lateinit var mCamera: Camera
    private val mIsRecord = false //是否正在录像

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btRecord.setOnClickListener {
            if (!mIsRecord) {
                recordVideo()
                binding.btRecord.text = "stopRecord"
            } else {
                videoRecorder.stopRecord()
            }
        }
    }

    fun recordVideo() {
        val config = VideoRecorderConfig()
        /*camera相关设置部分*/
        /*camera相关设置部分*/
        mCamera =
            Camera.open(android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK) //Camera.CameraInfo.CAMERA_FACING_BACK

        if (mCamera != null) {
            val parameters = mCamera.parameters
            parameters.focusMode = Camera.Parameters.FLASH_MODE_AUTO //对焦设置为自动
            mCamera.parameters = parameters //添加参数
            //设置旋转角度，顺时针方向，因为默认是逆向90度的，这样图像就是正常显示了
            mCamera.setDisplayOrientation(90)
        }
        config.camera = mCamera
        config.cameraRotation = 90
        config.videoHeight = 1080
        config.videoWidth = 1920
        config.path = getOutputMediaFile()
        config.surfaceTexture = binding.tvShowVideo.surfaceTexture
        config.cameraId = 0
        videoRecorder = VideoRecorder()
        val start: Boolean = videoRecorder.startRecord(config, null)
        Log.d("jia_record", "recordVideo: 开启录制 ： $start")

    }

    /*
     * 获取手机外部存储路径
     * */
    private fun getOutputFile(): String? {
        var mediaFile: File? = null
        val OutputExist = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
        if (OutputExist) {
            mediaFile = Environment.getExternalStorageDirectory()
            return mediaFile.toString()
        }
        return null
    }

    /*
     * 获取录制视频的日期 作为存储文件路径一部分
     * */
    private fun getDate(): String {
        val ca = Calendar.getInstance()
        val year = ca[Calendar.YEAR] // 获取年份
        val month = ca[Calendar.MONTH] // 获取月份
        val day = ca[Calendar.DATE] // 获取日
        return "" + year + "_" + (month + 1) + "_" + day
    }

    /*
     *创建视频存储文件夹 录制好的视频存储在手机外部存储中 以录像时间+mp4格式命名
     * */
    private fun getOutputMediaFile(): String? {
        val mediaPath = getOutputFile()
        if (mediaPath != null) {
            val mediaFile = File("$mediaPath/recordVideo")
            if (!mediaFile.exists()) {
                mediaFile.mkdir()
            }
            return mediaFile.absolutePath + File.separator + getDate() + ".mp4"
        }
        return null
    }
}