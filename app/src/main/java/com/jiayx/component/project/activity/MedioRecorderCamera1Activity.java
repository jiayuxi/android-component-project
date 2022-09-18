package com.jiayx.component.project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;

import com.jiayx.component.project.R;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MedioRecorderCamera1Activity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MedioRecorderCamera1Activity.class.getSimpleName();
    private TextureView mTextureview;
    private Button mBtnStart, mBtnFinish;
    private MediaRecorder mMediaRecorder;
    private Camera mCamera;
    private Camera.Size mSelectSize;//记录当前选择的分辨率
    private boolean isRecorder = false;//用于判断当前是否在录制视频
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x01:
                    mCamera.autoFocus(new Camera.AutoFocusCallback() { //自动对焦
                        @Override
                        public void onAutoFocus(boolean success, Camera camera) {

                        }
                    });
                    mHandler.sendEmptyMessageDelayed(0x01, 2 * 1000);//2秒之后在对焦一次,一直重复自动对焦
                    break;
                default:
                    break;
            }
        }
    };

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_record);
        mTextureview = findViewById(R.id.tv_show_video);
        mBtnStart = findViewById(R.id.bt_record);
        mBtnFinish = findViewById(R.id.bt_stop_record);
        mBtnStart.setOnClickListener(this);
        mBtnFinish.setOnClickListener(this);
        initTextureViewListener();
        initMediaRecorder();

    }

    /**
     * 初始化TextureView监听
     */
    private void initTextureViewListener() {
        mTextureview.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) { //Textureview初始化启用回调
                initCamera();
                initCameraConfig();
                try {
                    mCamera.setPreviewTexture(surface);
                    mCamera.startPreview();
                    mHandler.sendEmptyMessage(0x01);//启动对焦
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_record:
                startRecorder();
                break;
            case R.id.bt_stop_record:
                stopRecorder();
                break;
            default:
                break;
        }

    }

    /**
     * 初始化MediaRecorder
     */
    private void initMediaRecorder() {
        mMediaRecorder = new MediaRecorder();
    }

    /**
     * 选择摄像头
     *
     * @param isFacing true=前摄像头 false=后摄像头
     * @return 摄像id
     */
    private Integer selectCamera(boolean isFacing) {
        int cameraCount = Camera.getNumberOfCameras();
        //        CameraInfo.CAMERA_FACING_BACK 后摄像头
        //        CameraInfo.CAMERA_FACING_FRONT  前摄像头
        int facing = isFacing ? Camera.CameraInfo.CAMERA_FACING_FRONT : Camera.CameraInfo.CAMERA_FACING_BACK;
        Log.e(TAG, "selectCamera: cameraCount=" + cameraCount);
        if (cameraCount == 0) {
            Log.e(TAG, "selectCamera: The device does not have a camera ");
            return null;
        }
        Camera.CameraInfo info = new Camera.CameraInfo();
        for (int i = 0; i < cameraCount; i++) {
            Camera.getCameraInfo(i, info);
            if (info.facing == facing) {
                return i;
            }

        }
        return null;

    }

    /**
     * 初始化相机
     */
    private void initCamera() {
        mCamera = Camera.open(selectCamera(false));
        mSelectSize = selectPreviewSize(mCamera.getParameters());


    }

    /**
     * 初始化相机配置
     */
    private void initCameraConfig() {
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);//关闭闪光灯
        parameters.setFocusMode(Camera.Parameters.FLASH_MODE_AUTO); //对焦设置为自动
        parameters.setPreviewSize(mSelectSize.width, mSelectSize.height);//设置预览尺寸
        parameters.setPictureSize(mSelectSize.width, mSelectSize.height);//设置图片尺寸  就拿预览尺寸作为图片尺寸,其实他们基本上是一样的
        parameters.set("orientation", "portrait");//相片方向
        parameters.set("rotation", 90); //相片镜头角度转90度（默认摄像头是横拍）
        mCamera.setParameters(parameters);//添加参数
        mCamera.setDisplayOrientation(90);//设置显示方向

    }

    /**
     * 计算获取预览尺寸
     *
     * @param parameters
     * @return
     */
    private Camera.Size selectPreviewSize(Camera.Parameters parameters) {
        List<Camera.Size> previewSizeList = parameters.getSupportedPreviewSizes();
        if (previewSizeList.size() == 0) {
            Log.e(TAG, "selectPreviewSize: previewSizeList size is 0");
            return null;

        }

        Camera.Size currentSelectSize = null;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        for (int i = 1; i < 41; i++) {
            for (Camera.Size itemSize : previewSizeList) {
                //                Log.e(TAG, "selectPreviewSize: itemSize 宽="+itemSize.width+"高"+itemSize.height);
                if (itemSize.height > (deviceWidth - i * 5) && itemSize.height < (deviceWidth + i * 5)) {
                    if (currentSelectSize != null) { //如果之前已经找到一个匹配的宽度
                        if (Math.abs(deviceHeight - itemSize.width) < Math.abs(deviceHeight - currentSelectSize.width)) { //求绝对值算出最接近设备高度的尺寸
                            currentSelectSize = itemSize;
                            continue;
                        }
                    } else {
                        currentSelectSize = itemSize;
                    }

                }

            }
        }
        Log.e(TAG, "selectPreviewSize: 当前选择的尺寸 宽=" + currentSelectSize.width + "高" + currentSelectSize.height);
        return currentSelectSize;
    }

    /**
     * 配置MedioRecorder
     */
    private void configMedioRecorder() {
        File saveRecorderFile = new File(getExternalCacheDir(), "CameraRecorder.mp4");
        if (saveRecorderFile.exists()) {
            saveRecorderFile.delete();
        }
        mCamera.unlock();
        mMediaRecorder.setCamera(mCamera);
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);//设置音频源
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);//设置视频源
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);//设置音频输出格式
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);//设置音频编码格式
        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);//设置视频编码格式
        mMediaRecorder.setVideoSize(mSelectSize.width, mSelectSize.height);//设置视频分辨率
        mMediaRecorder.setVideoEncodingBitRate(8 * 1920 * 1080);//设置视频的比特率
        mMediaRecorder.setVideoFrameRate(60);//设置视频的帧率
        mMediaRecorder.setOrientationHint(90);//设置视频的角度
        mMediaRecorder.setMaxDuration(60 * 1000);//设置最大录制时间
        Surface surface = new Surface(mTextureview.getSurfaceTexture());
        mMediaRecorder.setPreviewDisplay(surface);//设置预览
        mMediaRecorder.setOutputFile(saveRecorderFile.getAbsolutePath());//设置文件保存路径
        mMediaRecorder.setOnErrorListener(new MediaRecorder.OnErrorListener() { //录制异常监听
            @Override
            public void onError(MediaRecorder mr, int what, int extra) {
                mMediaRecorder.stop();
                mMediaRecorder.reset();
                try {
                    mCamera.setPreviewTexture(mTextureview.getSurfaceTexture());
                    mCamera.startPreview();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    /**
     * 开启录制视频
     */
    private void startRecorder() {
        if (!isRecorder) {//如果不在录制视频
            mCamera.stopPreview();//暂停相机预览
            configMedioRecorder();//再次配置MedioRecorder
            try {
                mMediaRecorder.prepare();//准备录制
            } catch (IOException e) {
                e.printStackTrace();
            }
            mMediaRecorder.start();//开始录制
            isRecorder = true;
        }

    }

    /**
     * 停止录制视频
     */
    private void stopRecorder() {
        if (isRecorder) { //如果在录制视频
            mMediaRecorder.stop();//暂停录制
            mMediaRecorder.reset();//重置,将MediaRecorder调整为空闲状态
            isRecorder = false;
            try {
                mCamera.setPreviewTexture(mTextureview.getSurfaceTexture());//重新设置预览SurfaceTexture
                mCamera.startPreview(); //重新开启相机预览
                mCamera.autoFocus(new Camera.AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {

                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaRecorder != null) {
            if (isRecorder) {
                mMediaRecorder.stop();
            }
            mMediaRecorder.release();
            mMediaRecorder = null;
        }
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;

        }
    }
}