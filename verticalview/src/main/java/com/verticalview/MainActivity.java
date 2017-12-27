package com.verticalview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,View.OnTouchListener {

    private ViewFlipper myViewFlipper;
    private LayoutInflater factory;
    private View first,second,third;
    private GestureDetector myGestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        factory = LayoutInflater.from(MainActivity.this);
        myViewFlipper = (ViewFlipper) findViewById(R.id.myViewFlipper);
        initView();
    }

    private void initView() {
        myGestureDetector = new GestureDetector(this);
        myViewFlipper.setOnTouchListener(this);

        first = factory.inflate(R.layout.firstview, null);
        second = factory.inflate(R.layout.secondview, null);
        third = factory.inflate(R.layout.thirdscrollview, null);

        myViewFlipper.addView(first);
        myViewFlipper.addView(second);
        myViewFlipper.addView(third);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        // 参数e1是按下事件，e2是放开事件，剩下两个是滑动的速度分量，这里用不到
        // 按下时的横坐标大于放开时的横坐标，从右向左滑动
        if (motionEvent.getX() > motionEvent1.getX()) {
            myViewFlipper.showNext();
        }
        // 按下时的横坐标小于放开时的横坐标，从左向右滑动
        else if (motionEvent.getX() < motionEvent1.getX()) {
            myViewFlipper.showPrevious();
        }
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return myGestureDetector.onTouchEvent(motionEvent);
    }
}
