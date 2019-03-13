package com.rozadin.list_work;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityEdit extends AppCompatActivity {
    private int count_list = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        int priority_list = getIntent().getExtras().getInt("name_list_add");
        count_list = getIntent().getExtras().getInt("count_list_add");
        String string_list_edit = getIntent().getExtras().getString("string_list_add");
        switch (priority_list) {
            case 1:
                ((TextView) findViewById(R.id.name_edit_list_textView)).setText(R.string.name_worth_1);
                break;
            case 2:
                ((TextView) findViewById(R.id.name_edit_list_textView)).setText(R.string.name_worth_2);
                break;
            case 3:
                ((TextView) findViewById(R.id.name_edit_list_textView)).setText(R.string.name_worth_3);
                break;
            case 4:
                ((TextView) findViewById(R.id.name_edit_list_textView)).setText(R.string.name_worth_4);
                break;
        }
        ((EditText)findViewById(R.id.edit_text_activity_edit)).setText(string_list_edit);
    }

    /**
     * Метод для для редактирования записи.
     * Отправляет редактируемую запись в Look_List.onActivityResult
     * где и происходит обработка редактирования записи
     */
    public void EditElement(View view) {
        String str = ((EditText) findViewById(R.id.edit_text_activity_edit)).getText().toString();
        Intent intent = new Intent(this, Look_List.class);
        intent.putExtra("count_list_edit_position", count_list);
        intent.putExtra("string_list_edit",str);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        setResult(1,intent);
        finish();
    }
}
