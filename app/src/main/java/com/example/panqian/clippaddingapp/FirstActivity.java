package com.example.panqian.clippaddingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by panqian on 2017/5/3.
 */

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ListView listView=(ListView)findViewById(R.id.list_view);
        boolean clipToPadding=getIntent().getBooleanExtra("clip",true);
        listView.setClipToPadding(clipToPadding);
        listView.setAdapter(new SimpleAdapter(this,getList(),R.layout.view_item,new String[]{"text"},new int[]{R.id.text}));

    }

    private List<Map<String,String>> getList(){
        List<Map<String,String>> data=new ArrayList<>();
        for (int i=0;i<3;++i){
            Map<String ,String > map=new HashMap<>();
            map.put("text","Hello  Test "+i);
            data.add(map);
        }
        return data;
    }
}
