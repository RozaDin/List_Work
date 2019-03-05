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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ArrayAdapter<String> adapter_1 = null, adapter_2 = null, adapter_3 = null, adapter_4 = null;
    private ListView list1 = null, list2 = null, list3 = null, list4 = null;

    @Override
    protected void onResume() {
        super.onResume();
        list1.setAdapter(adapter_1);
        list2.setAdapter(adapter_2);
        list3.setAdapter(adapter_3);
        list4.setAdapter(adapter_4);

        Log.v("List_work", "list set adapter");
        this.adapter_1.setNotifyOnChange(true);
        this.adapter_2.setNotifyOnChange(true);
        this.adapter_3.setNotifyOnChange(true);
        this.adapter_4.setNotifyOnChange(true);

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

        ArrayList<String> list_priority_1 = splitArrayString(1);
        ArrayList<String> list_priority_2 = splitArrayString(2);
        ArrayList<String> list_priority_3 = splitArrayString(3);
        ArrayList<String> list_priority_4 = splitArrayString(4);
        Log.v("List_work", "list_priority init");

        this.adapter_1 = new ArrayAdapter<String>(this, R.layout.simple_list_item_min_white, list_priority_1);
        this.adapter_2 = new ArrayAdapter<String>(this, R.layout.simple_list_item_min_black, list_priority_2);
        this.adapter_3 = new ArrayAdapter<String>(this, R.layout.simple_list_item_min_black, list_priority_3);
        this.adapter_4 = new ArrayAdapter<String>(this, R.layout.simple_list_item_min_white, list_priority_4);
        Log.v("List_work", "adapter init");

        this.list1 = (ListView) findViewById(R.id.list_item_1);
        this.list2 = (ListView) findViewById(R.id.list_item_2);
        this.list3 = (ListView) findViewById(R.id.list_item_3);
        this.list4 = (ListView) findViewById(R.id.list_item_4);
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

    public ArrayList<String> splitArrayString(int i) {
        ArrayList<String> list_priority = new ArrayList<>();
        FileInputStream fin = null;
        if (i > 4 || i < 1) i = 1;
        try {
            if (!new File(getResources().getStringArray(R.array.list_file_work)[0]).exists()) {
                FileOutputStream fos = null;
                fos = openFileOutput(getResources().getStringArray(R.array.list_file_work)[0], MODE_APPEND);
                fos.close();
                fos = openFileOutput(getResources().getStringArray(R.array.list_file_work)[1], MODE_APPEND);
                fos.close();
                fos = openFileOutput(getResources().getStringArray(R.array.list_file_work)[2], MODE_APPEND);
                fos.close();
                fos = openFileOutput(getResources().getStringArray(R.array.list_file_work)[3], MODE_APPEND);
                fos.close();
            }
            switch (i) {
                case 1:
                    fin = openFileInput(getResources().getStringArray(R.array.list_file_work)[0]);
                    break;
                case 2:
                    fin = openFileInput(getResources().getStringArray(R.array.list_file_work)[1]);
                    break;
                case 3:
                    fin = openFileInput(getResources().getStringArray(R.array.list_file_work)[2]);
                    break;
                case 4:
                    fin = openFileInput(getResources().getStringArray(R.array.list_file_work)[3]);
                    break;
            }
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String[] arrays_split = new String(new String(bytes)).split("<>");
            for (String s : arrays_split) {
                if (!s.isEmpty()) list_priority.add(s);
            }
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("List_work", ex.getMessage());
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("List_work", ex.getMessage());
            }
        }
        return list_priority;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.adapter_1.notifyDataSetChanged();
        this.adapter_2.notifyDataSetChanged();
        this.adapter_3.notifyDataSetChanged();
        this.adapter_4.notifyDataSetChanged();
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
