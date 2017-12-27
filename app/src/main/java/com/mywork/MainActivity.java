package com.mywork;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String mUrl;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private ImageView mIvBook;
//    private BookBean mBookBean;
    private TextView mTvTitle;
    private TextView mTvMsg;
    private TextView mTvRating;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    protected void initView() {
        //设置Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);//决定左上角的图标是否可以点击
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//决定左上角图标的右侧是否有向左的小箭头

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        mIvBook = (ImageView) findViewById(R.id.iv_book_image);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvMsg = (TextView) findViewById(R.id.tv_msg);
        mTvRating = (TextView) findViewById(R.id.tv_rating);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        mTabLayout.addTab(mTabLayout.newTab().setText("作者信息"));
        mTabLayout.addTab(mTabLayout.newTab().setText("章节"));
        mTabLayout.addTab(mTabLayout.newTab().setText("书籍简介"));
    }

    private void initData() {
        mCollapsingToolbarLayout.setTitle("深入理解Android");
        mTvTitle.setText("深入理解Android1");
        mTvMsg.setText("接盘侠" + "/" + "接盘侠出版社" + "/" + "2017/4");
        mTvRating.setText("8.0分");
        mIvBook.setBackgroundResource(R.mipmap.test_bg);
    }

}
