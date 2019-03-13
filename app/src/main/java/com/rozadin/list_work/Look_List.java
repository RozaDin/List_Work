package com.rozadin.list_work;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.rozadin.list_work.database.ListWorkSQLiteOpenHelper;
import java.util.ArrayList;

public class Look_List extends AppCompatActivity implements AdapterLookList.onFragmentInteractionDoubleInterfece {
    private static int priority_list = 1;
    private static ArrayList<String> list = null;
    private static AdapterLookList mAdapterLookList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.look_list_layout);
        priority_list = getIntent().getExtras().getInt("priority");
        if (priority_list > 4 || priority_list < 1) {
            Log.e("List_work", "default Error in 31 line \n prority_list=" + priority_list);
            priority_list = 1;
        }
        Log.v("List_work", "list work init to prioroty list = " + priority_list);
        switch (priority_list) {
            case 1:
                ((TextView) findViewById(R.id.Look_list_Text)).setText(R.string.name_worth_1);
                break;
            case 2:
                ((TextView) findViewById(R.id.Look_list_Text)).setText(R.string.name_worth_2);
                break;
            case 3:
                ((TextView) findViewById(R.id.Look_list_Text)).setText(R.string.name_worth_3);
                break;
            case 4:
                ((TextView) findViewById(R.id.Look_list_Text)).setText(R.string.name_worth_4);
                break;
        }
        if (list != null) list.clear();
        list = new ArrayList<>();
        ListWorkSQLiteOpenHelper db = new ListWorkSQLiteOpenHelper(this);
        list.addAll(db.getList(priority_list));
        mAdapterLookList = new AdapterLookList(this, R.layout.layout_look_list_adapter, list, priority_list);
        Log.v("List_work", "adapter init");
        ListView listV = (ListView) findViewById(R.id.look_list_list_view);
        listV.setAdapter(mAdapterLookList);
        Log.v("List_work", "list set adapter");
        db.close();
    }

    public void AddElementList(View view) {
        Intent intent = new Intent(this, ActivityAdd.class);
        intent.putExtra("name_list_add", priority_list);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.recreate();
    }

    @Override
    public void deleteButtonWorkInterface(int position) {
        Log.v("List_work", "Нажата кнопка удаления с позицей " + position);
        if (list == null) return;
        ListWorkSQLiteOpenHelper db = new ListWorkSQLiteOpenHelper(this);
        db.deleteList(list.get(position));
        list.remove(position);
        db.close();
        mAdapterLookList.notifyDataSetChanged();
    }

    @Override
    public void editButtonWorkInterface(int position) {
        Log.v("List_work", "Нажата кнопка редактирования с позицей " + position);
        Intent intent = new Intent(this, ActivityEdit.class);
        intent.putExtra("name_list_add", priority_list);
        intent.putExtra("count_list_add", position);
        intent.putExtra("string_list_add", this.list.get(position));
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.v("List_work", "полученние результата от активности редактированния");
        ListWorkSQLiteOpenHelper db = null;
        try {
            int count = data.getExtras().getInt("count_list_edit_position");
            String newString_list = data.getExtras().getString("string_list_edit");
            db = new ListWorkSQLiteOpenHelper(this);
            db.updateList(list.get(count), newString_list, priority_list);
            list.remove(count);
            list.add(count, newString_list);
        } catch (Exception e) {
            Log.v("List_work", "все дело в ошибке\n" + e.getMessage());
        } finally {
            Log.v("List_work", "обработка результата от активности редактированния");
            mAdapterLookList.notifyDataSetChanged();
            db.close();
        }
    }
}
