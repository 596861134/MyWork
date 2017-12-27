package com.gooda.unitapp;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView imageview;

    private Timer timer;
    private TimerTask timerTask;
    private ClipDrawable clipDrawable;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            clipDrawable.setLevel(msg.what);
            Log.e("Level",msg.what + "");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageview = (ImageView) findViewById(R.id.image);
        clipDrawable = (ClipDrawable) imageview.getBackground();
        clipDrawable.setLevel(0);

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (clipDrawable.getLevel() <= 10000){
                    handler.sendEmptyMessage(clipDrawable.getLevel() + 1000);
                }else {
                    if (timer != null) {
                        timer.cancel();
                        timer.purge();
                        timer = null;
                    }
                }
            }
        };
        timer.schedule(timerTask, 1000, 100);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }
}
