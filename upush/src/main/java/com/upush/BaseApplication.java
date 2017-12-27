package com.upush;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

/**
 * Created by czf on 2017/7/8.
 */

public class BaseApplication extends Application {
    private PushAgent mPushAgent;

    @Override
    public void onCreate() {
        super.onCreate();
        mPushAgent = PushAgent.getInstance(this);
        initUmeng();
    }

    public void initUmeng(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //注册推送服务，每次调用register方法都会回调该接口
                mPushAgent.register(new IUmengRegisterCallback() {
                    @Override
                    public void onSuccess(String deviceToken) {
                        //注册成功会返回device token
                        Log.e("deviceToken:",deviceToken);
                    }
                    @Override
                    public void onFailure(String s, String s1) {

                    }
                });
            }
        }).start();
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(context,TextActivity.class);
                startActivity(intent);
            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);
    }
}
