package com.rozadin.list_work;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdapterLookList extends ArrayAdapter<String>{

    private ArrayList<String> productList;
    private int pririti_list;
    private onFragmentInteractionDoubleInterfece mInterface;

    public AdapterLookList(@NonNull Context context, int resource, @NonNull List<String> objects, int prioriti_list) {
        super(context, resource, objects);
        this.productList = (ArrayList) objects;
        this.pririti_list = prioriti_list;
        this.mInterface =(onFragmentInteractionDoubleInterfece) context;
        Log.v("List_work","AdapterLookList инициализирован");
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull final ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.layout_look_list_adapter, parent, false);
        }
        ImageButton edit_button = (ImageButton) listItemView.findViewById(R.id.button_edit_look_list);
        ImageButton delete_button = (ImageButton) listItemView.findViewById(R.id.button_delete_look_list);

        String spisok = productList.get(position);
        TextView textView = (TextView) listItemView.findViewById(R.id.textView_adapter_look_list);
        textView.setText(spisok);
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Переделать на редактирование данных
                Log.v("List_work", "edit_button.setOnClickListener init to position " + position);
                mInterface.editButtonWorkInterface(position);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //удаляет запись из списка
                Log.v("List_work", "delete_button.setOnClickListener init to position " + position);
              mInterface.deleteButtonWorkInterface(position);
            }
        });
        return listItemView;
    }

    interface onFragmentInteractionDoubleInterfece
    {
        void deleteButtonWorkInterface(int position);
        void editButtonWorkInterface(int position);
    }
}
