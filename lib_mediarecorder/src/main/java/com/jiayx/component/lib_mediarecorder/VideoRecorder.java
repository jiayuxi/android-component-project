package com.jiayx.component.lib_mediarecorder;

import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Build;
import android.util.Log;
import android.view.Surface;

import java.io.IOException;

/**
 * author : Jia yu xi
 * date : 2022/9/18 17:00:00
 * description :
 */
public class VideoRecorder {
    private static final String TAG = "VideoRecord";
    private MediaRecorder mRecorder;
    private Camera mCamera;

    public VideoRecorder() {

    }

    /**
     * 开始录制
     *
     * @param config
     * @return
     */
    public boolean startRecord(VideoRecorderConfig config, MediaRecorder.OnErrorListener listener) {
        if (config == null || !config.checkParam()) {
            Log.e(TAG, "参数错误");
            return false;
        }
        if (mRecorder == null) {
            mRecorder = new MediaRecorder();
        }
        mRecorder.reset();
        if (listener != null) {
            mRecorder.setOnErrorListener(listener);
        }
        mCamera = config.getCamera();
        mCamera.unlock();
        mRecorder.setCamera(mCamera);
        //设置音频通道
        //        mRecorder.setAudioChannels(1);
        //声音源
        //        AudioSource.DEFAULT:默认音频来源
        //        AudioSource.MIC:麦克风（常用）
        //        AudioSource.VOICE_UPLINK:电话上行
        //        AudioSource.VOICE_DOWNLINK:电话下行
        //        AudioSource.VOICE_CALL:电话、含上下行
        //        AudioSource.CAMCORDER:摄像头旁的麦克风
        //        AudioSource.VOICE_RECOGNITION:语音识别
        //        AudioSource.VOICE_COMMUNICATION:语音通信
        mRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        //视频源
        mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);//设置音频输出格式
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);//设置音频编码格式
        mRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);//设置视频编码格式
        try {
            //推荐使用以下代码进行参数配置
            CamcorderProfile bestCamcorderProfile = getBestCamcorderProfile(config.getCameraId());
            mRecorder.setProfile(bestCamcorderProfile);
        } catch (Exception e) {
            Log.e(TAG, "startRecord: 参数配置异常" + e.getMessage());
        }

        //设置视频的长宽
        mRecorder.setVideoSize(config.getVideoWidth(), config.getVideoHeight());
        //        设置取样帧率
        mRecorder.setVideoFrameRate(25);
        //        mRecorder.setAudioEncodingBitRate(44100);
        //        设置比特率（比特率越高质量越高同样也越大）
        mRecorder.setVideoEncodingBitRate(800 * 1024);
        //        这里是调整旋转角度（前置和后置的角度不一样）
        mRecorder.setOrientationHint(config.getCameraRotation());
        //        设置记录会话的最大持续时间（毫秒）
        mRecorder.setMaxDuration(Integer.MAX_VALUE);
        //设置输出的文件路径
        mRecorder.setOutputFile(config.getPath());
        //设置预览对象（可以使用SurfaceHoler代替）
        mRecorder.setPreviewDisplay(new Surface(config.getSurfaceTexture()));
        //预处理
        try {
            mRecorder.prepare();
            mRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        //开始录制
        return true;
    }

    /**
     * 停止录制
     */
    public void stopRecord() {
        if (mRecorder != null) {
            try {
                mRecorder.stop();
                mRecorder.reset();
                mRecorder.release();
                mRecorder = null;
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage());
            }

        }
        if (mCamera != null) {
            try {
                mCamera.stopPreview();
                mCamera.release();
                mCamera = null;
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage());
            }
        }
    }

    /**
     * 暂停录制
     *
     * @return
     */
    public boolean pause() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && mRecorder != null) {
            mRecorder.pause();
            return true;
        }
        return false;
    }

    /**
     * 继续录制
     *
     * @return
     */
    public boolean resume() {
        if (mRecorder != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mRecorder.resume();
            return true;
        }
        return false;
    }

    public CamcorderProfile getBestCamcorderProfile(int cameraID) {
        CamcorderProfile profile = CamcorderProfile.get(cameraID, CamcorderProfile.QUALITY_LOW);
        if (CamcorderProfile.hasProfile(cameraID, CamcorderProfile.QUALITY_480P)) {
            //对比下面720 这个选择 每帧不是很清晰
            profile = CamcorderProfile.get(cameraID, CamcorderProfile.QUALITY_480P);
            profile.videoBitRate = profile.videoBitRate / 5;
            return profile;
        }
        if (CamcorderProfile.hasProfile(cameraID, CamcorderProfile.QUALITY_720P)) {
            //对比上面480 这个选择 动作大时马赛克!!
            profile = CamcorderProfile.get(cameraID, CamcorderProfile.QUALITY_720P);
            profile.videoBitRate = profile.videoBitRate / 35;
            return profile;
        }
        if (CamcorderProfile.hasProfile(cameraID, CamcorderProfile.QUALITY_CIF)) {
            profile = CamcorderProfile.get(cameraID, CamcorderProfile.QUALITY_CIF);
            return profile;
        }
        if (CamcorderProfile.hasProfile(cameraID, CamcorderProfile.QUALITY_QVGA)) {
            profile = CamcorderProfile.get(cameraID, CamcorderProfile.QUALITY_QVGA);
            return profile;
        }
        return profile;
    }
}
