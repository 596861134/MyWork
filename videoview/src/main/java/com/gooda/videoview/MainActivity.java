package com.gooda.videoview;

import android.Manifest;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private VideoView video;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        requestPermission();

    }

    private void requestPermission() {
        String[] needPermission = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        };
        ActivityCompat.requestPermissions(this, needPermission, 1);
    }

    private void initView() {
        video = (VideoView) findViewById(R.id.video);
        playVideoRaw();
    }

    /**
     * 播放raw的小视频
     */
    private void playVideoRaw() {
        mediaController = new MediaController(this);
        //获取raw.mp4的uri地址
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.semi_automatic;
        video.setVideoURI(Uri.parse(uri));
        //让video和mediaController建立关联
        video.setMediaController(mediaController);
        mediaController.setMediaPlayer(video);
        //让video获取焦点
        video.requestFocus();
        //开始播放
        video.start();
        //监听播放完成，循环播放
        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //重新开始播放
//                video.start();
                mp.start();
                mp.setLooping(true);
            }
        });
    }

    /**
     * 播放sd卡的小视频
     */
    private void playVideoMp4() {
        mediaController = new MediaController(this);
        //获取sd卡根目录下的test.mp4文件
        File file = new File(Environment.getExternalStorageDirectory().toString() + "/test.mp4");
        if (file.exists()) {
            video.setVideoPath(file.getAbsolutePath());
            //让video和mediaController建立关联
            video.setMediaController(mediaController);
            mediaController.setMediaPlayer(video);
            //让video获取焦点
            video.requestFocus();
        }
    }
}
