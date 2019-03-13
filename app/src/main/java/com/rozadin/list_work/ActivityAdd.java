package com.rozadin.list_work;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rozadin.list_work.database.ListWorkSQLiteOpenHelper;

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

    /** Метод добавления записи в БД
     */
    public void AddElement(View view) {
        String str = ((EditText) findViewById(R.id.name_add_list)).getText().toString();
        if (str.isEmpty()) {
            Toast.makeText(this, "Пустая добавляемая запись", Toast.LENGTH_SHORT).show();
        } else {
            //writeFileString(str);
            new ListWorkSQLiteOpenHelper(getApplicationContext()).insertList(str, priority_list);
            Intent intent = new Intent(this, Look_List.class);
            intent.putExtra("priority", priority_list);
            setResult(0, intent);
            finish();
        }
    }
}