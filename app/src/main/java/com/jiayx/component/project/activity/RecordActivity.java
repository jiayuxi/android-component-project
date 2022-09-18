package com.jiayx.component.project.activity;

import android.hardware.Camera;
import android.media.AudioManager;
import android.media.CamcorderProfile;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.jiayx.component.project.R;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class RecordActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private static final String TAG = "MainActivity";
    private SurfaceView mSurfaceView;
    private Button mRecordBtn, mPlayBtn;
    private boolean mIsRecord = false; //是否正在录像
    private Camera mCamera;
    private MediaRecorder mMediaRecorder;
    private String mMediaPath;
    private MediaPlayer mMediaPlayer;
    private SurfaceHolder mSurfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        //6.0及以上系统请求运行时权限 利用权限申请工具类（见下文）
        mSurfaceView = (SurfaceView) findViewById(R.id.surface_view);
        mSurfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); // 必须-设置Surface不需要维护自己的缓冲区
        mRecordBtn = (Button) findViewById(R.id.record_btn);
        mPlayBtn = (Button) findViewById(R.id.play_btn);
        initBtnClick();
        SurfaceHolder holder = mSurfaceView.getHolder();
        holder.addCallback(this);
        selectCamera(true);
    }

    private void StartRecording() {
        mRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mIsRecord) {
                    try {
                        Log.d(TAG, "首次点击开始录像 ");
                        if (prepareVideoRecorder()) {
                            mMediaRecorder.start();
                            mIsRecord = true;
                            mRecordBtn.setText("stop");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d(TAG, "再次点击停止录像");
                    mMediaRecorder.stop();
                    releaseMediaRecorder();
                    mCamera.lock();
                    mRecordBtn.setText("record");
                    mIsRecord = false;
                    if (mCamera != null) {
                        mCamera.release();
                        mCamera = null;
                    }
                }
            }
        });
    }

    private void initBtnClick() {
        StartRecording();
        mPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMediaPlayer == null) {
                    mMediaPlayer = new MediaPlayer();
                    mMediaPlayer.reset();
                    Uri uri = Uri.parse(mMediaPath);
                    mMediaPlayer = MediaPlayer.create(RecordActivity.this, uri);
                    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mMediaPlayer.setDisplay(mSurfaceHolder);
                    try {
                        mMediaPlayer.prepare();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mMediaPlayer.start();
                }
            }
        });
    }

    /*
     * 相机预览前的准备工作代码 单独抽出来
     * */
    private boolean prepareVideoRecorder() throws IOException {
        if (mMediaRecorder == null) {
            mMediaRecorder = new MediaRecorder();
            mMediaRecorder.reset();
        }
        /*camera相关设置部分*/
        mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);//Camera.CameraInfo.CAMERA_FACING_BACK
        if (mCamera != null) {
            //设置旋转角度，顺时针方向，因为默认是逆向90度的，这样图像就是正常显示了
            mCamera.setDisplayOrientation(90);
            mCamera.unlock();
            mMediaRecorder.setCamera(mCamera);
        }
        /*recorder设置部分*/
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mMediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));
        mMediaRecorder.setOutputFile(getOutputMediaFile());
        mMediaRecorder.setPreviewDisplay(mSurfaceView.getHolder().getSurface());
        mMediaRecorder.prepare();
        return true;
    }
    /**
     * 选择摄像头
     * @param isFacing true=前摄像头 false=后摄像头
     * @return 摄像id
     */
    private Integer selectCamera(boolean isFacing){
        int cameraCount = Camera.getNumberOfCameras();
        //        CameraInfo.CAMERA_FACING_BACK 后摄像头
        //        CameraInfo.CAMERA_FACING_FRONT  前摄像头
        int facing = isFacing ? Camera.CameraInfo.CAMERA_FACING_FRONT : Camera.CameraInfo.CAMERA_FACING_BACK;
        Log.e(TAG, "selectCamera: cameraCount="+cameraCount);
        if (cameraCount == 0){
            Log.e(TAG, "selectCamera: The device does not have a camera ");
            return null;
        }
        Camera.CameraInfo info = new Camera.CameraInfo();
        for (int i=0; i < cameraCount; i++){
            Camera.getCameraInfo(i,info);
            if (info.facing == facing){
                return i;
            }

        }
        return null;

    }
    /*
     * 获取手机外部存储路径
     * */
    private String getOutputFile() {
        File mediaFile = null;
        boolean OutputExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if (OutputExist) {
            mediaFile = Environment.getExternalStorageDirectory();
            return mediaFile.toString();
        }
        return null;
    }

    /*
     * 获取录制视频的日期 作为存储文件路径一部分
     * */
    private String getDate() {
        Log.d(TAG, "获取录制视频的日期 ");
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);   // 获取年份
        int month = ca.get(Calendar.MONTH);   // 获取月份
        int day = ca.get(Calendar.DATE);   // 获取日
        String date = "" + year + "_" + (month + 1) + "_" + day;
        return date;
    }

    /*
     *创建视频存储文件夹 录制好的视频存储在手机外部存储中 以录像时间+mp4格式命名
     * */
    private String getOutputMediaFile() {
        Log.d(TAG, "获取视频存储的位置 ");
        String mediaPath = getOutputFile();
        if (mediaPath != null) {
            File mediaFile = new File(mediaPath + "/recordVideo");
            if (!mediaFile.exists()) {
                mediaFile.mkdir();
            }
            return mMediaPath = mediaFile.getAbsolutePath() + File.separator + getDate() + ".mp4";
        }
        return null;
    }

    /*
     * 录制视频结束时释放相机资源
     * */
    private void releaseMediaRecorder() {
        Log.d(TAG, "录制结束后释放资源 ");
        if (mMediaRecorder != null) {
            mMediaRecorder.reset(); // clear recorder configuration
            mMediaRecorder.release(); // release the recorder object
            mMediaRecorder = null;
            mCamera.lock();   // lock camera for later use
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mSurfaceHolder = surfaceHolder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        mSurfaceHolder = surfaceHolder;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mSurfaceView = null;
        mSurfaceHolder = null;
        releaseMediaRecorder();
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}