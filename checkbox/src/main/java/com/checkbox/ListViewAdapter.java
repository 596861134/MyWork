package com.checkbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by czf on 2017/5/3.
 */

public class ListViewAdapter extends BaseAdapter {
    Context context;
    ArrayList<HashMap<String, Object>> listData;
    //记录checkbox的状态
    HashMap<Integer, Boolean> state = new HashMap<>();

    //构造函数
    public ListViewAdapter(Context context,ArrayList<HashMap<String,Object>> listData) {
        this.context = context;
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item,null);
            viewHolder = new ViewHolder();
            viewHolder.id = (TextView) convertView.findViewById(R.id.friend_id);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.friend_image);
            viewHolder.username = (TextView) convertView.findViewById(R.id.friend_username);
            viewHolder.check = (CheckBox) convertView.findViewById(R.id.selected);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.id.setText((String) listData.get(position).get("friend_id"));
        viewHolder.username.setText((String) listData.get(position).get("friend_username"));
        viewHolder.image.setBackgroundResource((Integer) listData.get(position).get("friend_image"));
        viewHolder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    state.put(position, isChecked);
                } else {
                    state.remove(position);
                }
            }
        });
        viewHolder.check.setChecked((state.get(position) == null ? false : true));

        return convertView;
    }

    static class ViewHolder{
        private TextView id;
        private ImageView image;
        private TextView username;
        private CheckBox check;
    }

    // 重写View
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        LayoutInflater mInflater = LayoutInflater.from(context);
//        convertView = mInflater.inflate(R.layout.list_item, null);
//
//        ImageView image = (ImageView) convertView.findViewById(R.id.friend_image);
//        image.setBackgroundResource((Integer) listData.get(position).get("friend_image"));
//
//        TextView username = (TextView) convertView.findViewById(R.id.friend_username);
//        username.setText((String) listData.get(position).get("friend_username"));
//
//        TextView id = (TextView) convertView.findViewById(R.id.friend_id);
//        id.setText((String) listData.get(position).get("friend_id"));
//
//        CheckBox check = (CheckBox) convertView.findViewById(R.id.selected);
//        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    state.put(position, isChecked);
//                } else {
//                    state.remove(position);
//                }
//            }
//        });
//        check.setChecked((state.get(position) == null ? false : true));
//        return convertView;
//    }


}
