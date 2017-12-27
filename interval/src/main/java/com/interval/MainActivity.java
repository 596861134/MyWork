package com.interval;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements LifeInterface {
    @BindView(R.id.subject)
    Button subject;
    @BindView(R.id.unsubject)
    Button unsubject;
    @BindView(R.id.control)
    TextView control;

    private static final String TAG = "MainActivity";
    private StringBuffer buffer;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private Observable<Long> mObservable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        buffer = new StringBuffer();
//        mObservable = Observable.interval(0,1, TimeUnit.SECONDS);
    }

    @OnClick({R.id.subject,R.id.unsubject})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.subject:
//                sunject();

                mCompositeDisposable.add(Observable.interval(0, 30, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(getObserver()));

                break;
            case R.id.unsubject:
                unSunject();
                break;
        }
    }


    public void unSunject() {
        if (mCompositeDisposable!=null){
            Log.d(TAG, "取消轮询");
            mCompositeDisposable.clear();
        }
    }


    public void sunject() {
        mObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long aLong) throws Exception {
                Log.d(TAG, "第 " + aLong + " 次轮询");
                buffer.append("第 " + aLong + " 次轮询\n");
                control.setText(buffer.toString());

            }
        });

        // 参数1 = 第1次延迟时间；
        // 参数2 = 间隔时间；
        // 参数3 = 时间单位；
        Observable.interval(0, 30, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        Log.d(TAG, "第 " + aLong + " 次轮询");
                        buffer.append("第 " + aLong + " 次轮询\n");
                        control.setText(buffer.toString());
                    }
                }).subscribe();
    }

    @Override
    public void bindLife() {
        PollingManager.getInstance().bindLifeCycle(getTag(), Event.ActivityEvent.PAUSE);
    }

    @Override
    public String getTag() {
        return TAG;
    }


    public DisposableObserver getObserver() {
        DisposableObserver disposableObserver = new DisposableObserver() {
            @Override
            public void onNext(@NonNull Object o) {
                Log.d(TAG, "第 " + String.valueOf(o) + " 次轮询");
                buffer.append("第 " + String.valueOf(o) + " 次轮询\n");
                control.setText(buffer.toString());
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        return disposableObserver;
    }

}
