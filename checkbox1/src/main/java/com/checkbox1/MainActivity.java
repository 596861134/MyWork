package com.checkbox1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView listView;
    private Button button;
    private ListViewAdapter adapter;
    private List<BoxBean> boxList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 12; i++) {
            BoxBean box = new BoxBean();
            box.setId(i);
            box.setImage(R.mipmap.ic_launcher);
            box.setName("名称"+i);
            boxList.add(box);
        }

        initView();
    }

    private void initView() {
        button = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.list_view);
        adapter = new ListViewAdapter(MainActivity.this, boxList);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        HashMap<Integer, Boolean> state =adapter.isSelected;
        String options="选择的项是:";
        for(int j=0;j<adapter.getCount();j++){
            System.out.println("state.get("+j+")=="+state.get(j));
            if(state.get(j)!=null){
                HashMap<String, Object> map=(HashMap<String, Object>) adapter.getItem(j);
                String username = map.get(j).toString();
                String id = map.get(j).toString();
                options+="\n"+id+"."+username;
            }
        }
        //显示选择内容
        Toast.makeText(getApplicationContext(), options, Toast.LENGTH_LONG).show();
    }
}
