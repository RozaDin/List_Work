package com.rozadin.list_work;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ActivityAdd extends AppCompatActivity {
    private int priority_list = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        priority_list = getIntent().getExtras().getInt("name_list_add");
        switch (priority_list) {
            case 1:
                ((TextView) findViewById(R.id.name_add_list_textView)).setText(R.string.name_worth_1);
                break;
            case 2:
                ((TextView) findViewById(R.id.name_add_list_textView)).setText(R.string.name_worth_2);
                break;
            case 3:
                ((TextView) findViewById(R.id.name_add_list_textView)).setText(R.string.name_worth_3);
                break;
            case 4:
                ((TextView) findViewById(R.id.name_add_list_textView)).setText(R.string.name_worth_4);
                break;
        }
    }

    public void AddElement(View view) {
        String str = ((EditText) findViewById(R.id.name_add_list)).getText().toString();
        if (str.isEmpty()) {
            Toast.makeText(this, "Пустая добавляемая запись", Toast.LENGTH_SHORT).show();
        } else {
            writeFileString(priority_list, str);
            Intent intent = new Intent(this, Look_List.class);
            intent.putExtra("priority", priority_list);
            setResult(0, intent);
            finish();
        }
    }

    private void writeFileString(int count_prioriti_list, String str) {
        FileOutputStream fos = null;
        String FILE_NAME = initializeFileName();
        try {
            fos = openFileOutput(FILE_NAME, MODE_APPEND);
            fos.write("<>".getBytes());
            fos.write(str.getBytes());
            fos.write("<>".getBytes());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("List_work", ex.getMessage());
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("List_work", ex.getMessage());
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("List_work", ex.getMessage());
            }
        }
    }

    private String initializeFileName() {
        String FILE_NAME = null;
        switch (priority_list) {
            case 1:
                FILE_NAME = getResources().getStringArray(R.array.list_file_work)[0];
                break;
            case 2:
                FILE_NAME = getResources().getStringArray(R.array.list_file_work)[1];
                break;
            case 3:
                FILE_NAME = getResources().getStringArray(R.array.list_file_work)[2];
                break;
            case 4:
                FILE_NAME = getResources().getStringArray(R.array.list_file_work)[3];
                break;
        }
        return FILE_NAME;
    }
}