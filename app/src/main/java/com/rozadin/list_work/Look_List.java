package com.rozadin.list_work;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Look_List extends AppCompatActivity implements AdapterLookList.onFragmentInteractionDoubleInterfece {
    private static int priority_list = 1;
    private ArrayList<String> list = null;
    private AdapterLookList mAdapterLookList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.look_list_layout);
        priority_list = getIntent().getExtras().getInt("priority");
        if (priority_list > 4 || priority_list < 1) {
            Log.e("List_work", "default Error in 20 line \n prority_list=" + priority_list);
            priority_list = 1;
        }
        ArrayList<String> list_work = new ArrayList<>();
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
        list_work.addAll(addList(priority_list));
        this.mAdapterLookList = new AdapterLookList(this, R.layout.layout_look_list_adapter, list_work, priority_list);
        Log.v("List_work", "adapter init");
        ListView list = (ListView) findViewById(R.id.look_list_list_view);
        list.setAdapter(mAdapterLookList);
        Log.v("List_work", "list set adapter");
    }

    public void AddElementList(View view) {
        Intent intent = new Intent(this, ActivityAdd.class);
        intent.putExtra("name_list_add", priority_list);
        startActivity(intent);
    }

    public ArrayList<String> addList(int count) {
        FileInputStream fin = null;
        list = new ArrayList<>();
        try {
            switch (count) {
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
                if (!s.isEmpty()) list.add(s);
            }
        } catch (FileNotFoundException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("List_work", ex.getMessage());
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("List_work", ex.getMessage());
        } finally {
            try {
                if (fin != null) fin.close();
            } catch (IOException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("List_work", e.getMessage());
            }
        }
        return list;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.mAdapterLookList.notifyDataSetChanged();
        this.recreate();
    }

    @Override
    public void deleteButtonWorkInterface(int position) {
        Log.v("List_work", "Нажата кнопка удаления с позицей " + position);
        if (this.list == null) return;
        this.list.remove(position);
        writeFile();
        this.mAdapterLookList.notifyDataSetChanged();
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
        Log.v("List_work","полученние результата от активности редактированния");
        //if (requestCode != 1 || resultCode != 1) return;
        try {
            int count = data.getExtras().getInt("count_list_edit_position");
            String newString_list = data.getExtras().getString("string_list_edit");
            this.list.remove(count);
            this.list.add(count, newString_list);
        }catch (Exception e){Log.v("List_work","все дело в ошибке\n"+ e.getMessage());}
        Log.v("List_work","обработка результата от активности редактированния");
        writeFile();
        this.mAdapterLookList.notifyDataSetChanged();
    }

    private void writeFile() {
        FileOutputStream fos = null;
        try {
            fos = initializeFileOutputStream(priority_list);
            if (fos == null) throw new IOException();
            for (String s : this.list) {
                fos.write(s.getBytes());
                fos.write("<>".getBytes());
            }
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
                this.recreate();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("List_work", ex.getMessage());
            }
        }
    }

    private FileOutputStream initializeFileOutputStream(int count_prioriti_list) throws FileNotFoundException {
        if (count_prioriti_list > 4 || count_prioriti_list < 1) {
            Log.v("List_work", "default Error in count prioriti_list \n prioriti_list =" + count_prioriti_list);
            count_prioriti_list = 1;
            priority_list = 1;
        }
        FileOutputStream fos = null;
        String FILE_NAME = null;
        switch (count_prioriti_list) {
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
        fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
        return fos;
    }
}
