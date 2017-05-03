package com.example.panqian.clippaddingapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by panqian on 2017/5/3.
 */

public class MainActivity extends ListActivity {
    private static final String[] items={"clipToPadding:  true","clipToPadding:  false"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent=new Intent(this,FirstActivity.class);
        switch (position){
            case 0:
//                intent.putExtra("clip",true);
                startActivity(intent);
                break;
            case 1:
                intent.putExtra("clip",false);
                startActivity(intent);
                break;
            default:
                break;
        }
        super.onListItemClick(l, v, position, id);
    }
}
