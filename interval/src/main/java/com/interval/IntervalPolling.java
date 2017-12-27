package com.interval;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * //
 * //                            _ooOoo_
 * //                           o8888888o
 * //                           88" . "88
 * //                           (| -_- |)
 * //                           O\  =  /O
 * //                        ____/`---'\____
 * //                      .'  \\|     |//  `.
 * //                     /  \\|||  :  |||//  \
 * //                    /  _||||| -:- |||||-  \
 * //                    |   | \\\  -  /// |   |
 * //                    | \_|  ''\---/''  |   |
 * //                    \  .-\__  `-`  ___/-. /
 * //                  ___`. .'  /--.--\  `. . ___
 * //               ."" '<  `.___\_<|>_/___.'  >' "".
 * //              | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * //              \  \ `-.   \_ __\ /__ _/   .-` /  /
 * //         ======`-.____`-.___\_____/___.-`____.-'======
 * //                            `=---='
 * //        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * //                      佛祖保佑       永无BUG
 * Created by czf on 2017/12/11.
 */

public class IntervalPolling extends PollingRequest {


    private int inteval;

    public IntervalPolling(int interval, String tag, EventInterface eventInterface, PollingAction action) {
        super(tag, eventInterface, action);
        this.inteval = interval;
    }

    @Override
    public Disposable execute(PollingManager manager) {
        return Observable.interval(inteval, TimeUnit.SECONDS).
                compose(manager.composeEvent(tag, eventInterface)).
                observeOn(AndroidSchedulers.mainThread()).
                doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d("TAG","emit interval polling, Tag = " + tag + ", num = " + aLong);
                    }
                }).
                subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long num) throws Exception {
                        if (null != pollingAction) {
                            pollingAction.doAction(num);
                        }
                        Log.d("TAG","running interval polling, Tag = " + tag + ", num = " + num);
                    }
                });
    }
}