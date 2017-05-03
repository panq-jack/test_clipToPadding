package com.example.panqian.clippaddingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by panqian on 2017/5/3.
 */

public class FirstActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean clipToPadding=getIntent().getBooleanExtra("clip",true);
        boolean transparent=getIntent().getBooleanExtra("trans",false);
        boolean tint=getIntent().getBooleanExtra("tint",false);
        statusBarTintEnable=tint;
        statusBarTransparentEnable=transparent;
        setContentView(R.layout.activity_first);
        ListView listView=(ListView)findViewById(R.id.list_view);

        listView.setClipToPadding(clipToPadding);
//        listView.setAdapter(new SimpleAdapter(this,getList(),R.layout.view_item,new String[]{"text","image"},new int[]{R.id.text,R.id.image}));
        final List<Map<String,Object>> lists=getList();
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return lists.size();
            }

            @Override
            public Object getItem(int position) {
                return lists.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Map<String ,Object> map=lists.get(position);
                ViewHolder viewHolder=null;
                if (null==convertView){
                    convertView=getLayoutInflater().inflate(R.layout.view_item,null);
                    viewHolder=new ViewHolder();
                    viewHolder.imageView=(ImageView)convertView.findViewById(R.id.image);
                    viewHolder.textView=(TextView)convertView.findViewById(R.id.text);
                    convertView.setTag(viewHolder);
                }else {
                    viewHolder=(ViewHolder) convertView.getTag();
                }
                viewHolder.imageView.setImageResource((int)map.get("image"));
                viewHolder.textView.setText((String)map.get("text"));
                return convertView;
            }
        });
    }

    private List<Map<String,Object>> getList(){
        int[] images={R.drawable.b,R.drawable.a,R.drawable.a};
        List<Map<String,Object>> data=new ArrayList<>();
        for (int i=0;i<3;++i){
            Map<String ,Object > map=new HashMap<>();
            map.put("text","Hello  Test "+i);
            map.put("image",images[i]);
            data.add(map);
        }
        return data;
    }
    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
