package com.opengles;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private GLSurfaceView mGLView;

    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float mPreviousX;
    private float mPreviousY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        mGLView = new GLSurfaceView(this);
        //创建一个OpenGL ES 2.0 context
        mGLView.setEGLContextClientVersion(2);
        //设置Renderer到GLSurfaceView
        mGLView.setRenderer(new MyGLRenderer());
        // 只有在绘制数据改变时才绘制view
        mGLView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        setContentView(mGLView);

        mGLView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent e) {
                float x = e.getX();
                float y = e.getY();

                switch (e.getAction()) {
                    case MotionEvent.ACTION_MOVE:

                        float dx = x - mPreviousX;
                        float dy = y - mPreviousY;

                        // reverse direction of rotation above the mid-line
                        if (y > mGLView.getHeight() / 2) {
                            dx = dx * -1 ;
                        }

                        // reverse direction of rotation to left of the mid-line
                        if (x < mGLView.getWidth() / 2) {
                            dy = dy * -1 ;
                        }

                        MyGLRenderer.setAngle(
                                MyGLRenderer.getAngle() +
                                        ((dx + dy) * TOUCH_SCALE_FACTOR));  // = 180.0f / 320
                        mGLView.requestRender();
                }

                mPreviousX = x;
                mPreviousY = y;
                return true;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLView.onResume();
    }
}
