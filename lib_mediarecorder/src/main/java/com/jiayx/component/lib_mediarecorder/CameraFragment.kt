package com.jiayx.component.lib_mediarecorder

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jiayx.component.lib_mediarecorder.databinding.FragmentCameraBinding
import com.otaliastudios.cameraview.CameraException
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.CameraOptions
import com.otaliastudios.cameraview.PictureResult
import com.otaliastudios.cameraview.size.Size
import java.io.File
import java.util.*

/**
 *  author : Jia yu xi
 *  date : 2022/9/20 23:03:03
 *  description :
 */
class CameraFragment : Fragment() {
    private val viewBinding: FragmentCameraBinding by lazy {
        FragmentCameraBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = viewBinding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.startRecord.setOnClickListener {
            if (viewBinding.cameraViewTake.isTakingVideo.not()) {
                val file = File(getOutputMediaFile())
                file.parentFile?.mkdirs()
                viewBinding.cameraViewTake.takeVideo(file, Int.MAX_VALUE)
            }
        }
        viewBinding.stopRecord.setOnClickListener {
            if (viewBinding.cameraViewTake.isTakingVideo) {
                viewBinding.cameraViewTake.stopVideo()
            }
        }
        viewBinding.cameraViewTake.setPictureSize { listOf(Size(1080, 1920)) }
        viewBinding.cameraViewTake.setVideoSize { listOf(Size(1080, 1920)) }
        viewBinding.cameraViewTake.videoBitRate = 2 * 1920 * 1080
        viewBinding.cameraViewTake.run {
            setLifecycleOwner(viewLifecycleOwner)
            addCameraListener(object : CameraListener() {
                override fun onCameraOpened(options: CameraOptions) {
                    super.onCameraOpened(options)
                }

                override fun onCameraError(exception: CameraException) {
                    super.onCameraError(exception)
                    Log.e("jia_camera", "摄像头开启失败：${exception.reason}")
                }

                override fun onPictureTaken(result: PictureResult) {
                    super.onPictureTaken(result)
                }

                override fun onVideoRecordingStart() {
                    super.onVideoRecordingStart()
                    Log.e("jia_camera", "onVideoRecordingStart: 开始录像")
                }

                override fun onVideoRecordingEnd() {
                    super.onVideoRecordingEnd()
                    Log.e("jia_camera", "onVideoRecordingEnd: 停止录像")
                }
            })
        }
        viewBinding.cameraFrontViewTake.setPictureSize { listOf(Size(1080, 1920)) }
        viewBinding.cameraFrontViewTake.setVideoSize { listOf(Size(1080, 1920)) }
        viewBinding.cameraFrontViewTake.videoBitRate = 2 * 1920 * 1080
        viewBinding.cameraFrontViewTake.run {
            setLifecycleOwner(viewLifecycleOwner)
            addCameraListener(object : CameraListener() {
                override fun onCameraOpened(options: CameraOptions) {
                    super.onCameraOpened(options)
                }

                override fun onCameraError(exception: CameraException) {
                    super.onCameraError(exception)
                    Log.e("jia_camera", "摄像头开启失败：${exception.reason}")
                }

                override fun onPictureTaken(result: PictureResult) {
                    super.onPictureTaken(result)
                }

                override fun onVideoRecordingStart() {
                    super.onVideoRecordingStart()
                    Log.e("jia_camera", "onVideoRecordingStart: 开始录像")
                }

                override fun onVideoRecordingEnd() {
                    super.onVideoRecordingEnd()
                    Log.e("jia_camera", "onVideoRecordingEnd: 停止录像")
                }
            })
        }
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