package com.checkbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;



public class MainActivity extends AppCompatActivity {
    //适配器
    private ListViewAdapter listItemAdapter;
    private GridView gridView;

    private ArrayList<HashMap<String, Object>> listData;
    private String[] name = {"William", "Charles", "Linng", "Json", "Bob", "Carli", "William", "Charles", "Linng", "Json", "Bob", "Carli"};
    private String[] id = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        listData = new ArrayList<>();
        //按钮及事件响应
        Button getValue = (Button) findViewById(R.id.get_value);
        getValue.setOnClickListener(listener);
        gridView = (GridView) findViewById(R.id.list_view);
        //存储数据的数组列表
        for (int i = 0; i < 12; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("friend_image", R.mipmap.ic_launcher);
            map.put("friend_username", name[i]);
            map.put("friend_id", id[i]);
            map.put("selected", false);
            //添加数据
            listData.add(map);
        }
        //适配器
        listItemAdapter = new ListViewAdapter(this, listData);
        gridView.setAdapter(listItemAdapter);
    }

    //事件响应
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            HashMap<Integer, Boolean> state = listItemAdapter.state;
            String options = "选择的项是:";
            for (int j = 0; j < listItemAdapter.getCount(); j++) {
                System.out.println("state.get(" + j + ")==" + state.get(j));
                if (state.get(j) != null) {
                    HashMap<String, Object> map = (HashMap<String, Object>) listItemAdapter.getItem(j);
                    String username = map.get("friend_username").toString();
                    String id = map.get("friend_id").toString();
                    options += "\n" + id + "." + username;
                }
            }
            //显示选择内容
            Toast.makeText(getApplicationContext(), options, Toast.LENGTH_LONG).show();
        }
    };
}