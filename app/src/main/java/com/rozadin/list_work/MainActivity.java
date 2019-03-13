package com.rozadin.list_work;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rozadin.list_work.database.ListWorkSQLiteOpenHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static ArrayAdapter<String> adapter_1 = null, adapter_2 = null, adapter_3 = null, adapter_4 = null;
    private static ListView list1 = null, list2 = null, list3 = null, list4 = null;

    @Override
    protected void onResume() {
        super.onResume();
        list1.setAdapter(adapter_1);
        list2.setAdapter(adapter_2);
        list3.setAdapter(adapter_3);
        list4.setAdapter(adapter_4);

        Log.v("List_work", "list set adapter");
        adapter_1.setNotifyOnChange(true);
        adapter_2.setNotifyOnChange(true);
        adapter_3.setNotifyOnChange(true);
        adapter_4.setNotifyOnChange(true);

        list1.setOnItemClickListener(this);
        list2.setOnItemClickListener(this);
        list3.setOnItemClickListener(this);
        list4.setOnItemClickListener(this);
        Log.v("List_work", " list set Listener");

        LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.liner_priority_1);
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("List_work", "Прослушиватель linearLayout1");
                startActivitiForCountList(1);
            }
        });
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.liner_priority_2);
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("List_work", "Прослушиватель linearLayout2");
                startActivitiForCountList(2);
            }
        });
        LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.liner_priority_3);
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("List_work", "Прослушиватель linearLayout3");
                startActivitiForCountList(3);
            }
        });
        LinearLayout linearLayout4 = (LinearLayout) findViewById(R.id.liner_priority_4);
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("List_work", "Прослушиватель linearLayout4");
                startActivitiForCountList(4);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("List_work", "initialize App");

        ArrayList<String> list_priority_1 = new ListWorkSQLiteOpenHelper(this).getList(1);
        ArrayList<String> list_priority_2 = new ListWorkSQLiteOpenHelper(this).getList(2);
        ArrayList<String> list_priority_3 = new ListWorkSQLiteOpenHelper(this).getList(3);
        ArrayList<String> list_priority_4 = new ListWorkSQLiteOpenHelper(this).getList(4);
        Log.v("List_work", "list_priority init");

        adapter_1 = new ArrayAdapter<String>(this, R.layout.simple_list_item_min_white, list_priority_1);
        adapter_2 = new ArrayAdapter<String>(this, R.layout.simple_list_item_min_black, list_priority_2);
        adapter_3 = new ArrayAdapter<String>(this, R.layout.simple_list_item_min_black, list_priority_3);
        adapter_4 = new ArrayAdapter<String>(this, R.layout.simple_list_item_min_white, list_priority_4);
        Log.v("List_work", "adapter init");

        list1 = (ListView) findViewById(R.id.list_item_1);
        list2 = (ListView) findViewById(R.id.list_item_2);
        list3 = (ListView) findViewById(R.id.list_item_3);
        list4 = (ListView) findViewById(R.id.list_item_4);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.list_item_1:
                startActivitiForCountList(1);
                break;
            case R.id.list_item_2:
                startActivitiForCountList(2);
                break;
            case R.id.list_item_3:
                startActivitiForCountList(3);
                break;
            case R.id.list_item_4:
                startActivitiForCountList(4);
                break;
            default:
                startActivitiForCountList(1);
                break;
        }
        Log.v("List_work", "parent.getId() = " + parent.getId());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter_1.notifyDataSetChanged();
        adapter_2.notifyDataSetChanged();
        adapter_3.notifyDataSetChanged();
        adapter_4.notifyDataSetChanged();
        this.recreate();
    }

    private void startActivitiForCountList(int prioriti_list) {
        Intent intent = new Intent(this, Look_List.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        switch (prioriti_list) {
            case 1:
                intent.putExtra("priority", 1);
                break;
            case 2:
                intent.putExtra("priority", 2);
                break;
            case 3:
                intent.putExtra("priority", 3);
                break;
            case 4:
                intent.putExtra("priority", 4);
                break;
            default:
                intent.putExtra("priority", 1);
                break;
        }
        Log.v("List_work", "prioriti_list = " + prioriti_list);
        startActivity(intent);
    }
}
