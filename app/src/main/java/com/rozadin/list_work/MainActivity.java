package com.rozadin.list_work;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.rozadin.list_work.database.ListWorkSQLiteOpenHelper;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    //private static ArrayAdapter<String> adapter_1 = null, adapter_2 = null, adapter_3 = null, adapter_4 = null;

    @Override
    protected void onResume() {
        super.onResume();


//        adapter_1.setNotifyOnChange(true);
//        adapter_2.setNotifyOnChange(true);
//        adapter_3.setNotifyOnChange(true);
//        adapter_4.setNotifyOnChange(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MainFragmentPageAdapter(getSupportFragmentManager(),
                MainActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        adapter_1.notifyDataSetChanged();
//        adapter_2.notifyDataSetChanged();
//        adapter_3.notifyDataSetChanged();
//        adapter_4.notifyDataSetChanged();
        this.recreate();
    }
}
