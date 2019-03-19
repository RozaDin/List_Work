package com.rozadin.list_work;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.rozadin.list_work.database.ListWorkSQLiteOpenHelper;

import java.util.ArrayList;

public class PageFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static ArrayAdapter<String> adapter_1 = null, adapter_2 = null, adapter_3 = null, adapter_4 = null;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_layout, container, false);


        adapter_1 = new ArrayAdapter<>(view.getContext(),
                R.layout.simple_list_item_min_white,
                new PageFragment.MainActivityAsyncTask().doInBackground(1));
        adapter_2 = new ArrayAdapter<>(view.getContext(),
                R.layout.simple_list_item_min_black,
                new PageFragment.MainActivityAsyncTask().doInBackground(2));
        adapter_3 = new ArrayAdapter<>(view.getContext(),
                R.layout.simple_list_item_min_black,
                new PageFragment.MainActivityAsyncTask().doInBackground(3));
        adapter_4 = new ArrayAdapter<>(view.getContext(),
                R.layout.simple_list_item_min_white,
                new PageFragment.MainActivityAsyncTask().doInBackground(4));
        Log.v("List_work", "adapter init");

        try {
            ListView list1 = getActivity().findViewById(R.id.list_item_1);
            ListView list2 = getActivity().findViewById(R.id.list_item_2);
            ListView list3 = getActivity().findViewById(R.id.list_item_3);
            ListView list4 = getActivity().findViewById(R.id.list_item_4);
            list1.setAdapter(adapter_1);
            list2.setAdapter(adapter_2);
            list3.setAdapter(adapter_3);
            list4.setAdapter(adapter_4);
            Log.v("List_work", "list set adapter");

        } catch (NullPointerException e) {
            Log.e("List_work", e.getMessage());
        }
        return view;
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
    public void onResume() {
        super.onResume();

        LinearLayout linearLayout1 = this.getView().findViewById(R.id.liner_priority_1);
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("List_work", "Прослушиватель linearLayout1");
                startActivitiForCountList(1);
            }
        });
        LinearLayout linearLayout2 = this.getView().findViewById(R.id.liner_priority_2);
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("List_work", "Прослушиватель linearLayout2");
                startActivitiForCountList(2);
            }
        });
        LinearLayout linearLayout3 = this.getView().findViewById(R.id.liner_priority_3);
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("List_work", "Прослушиватель linearLayout3");
                startActivitiForCountList(3);
            }
        });
        LinearLayout linearLayout4 = this.getView().findViewById(R.id.liner_priority_4);
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("List_work", "Прослушиватель linearLayout4");
                startActivitiForCountList(4);
            }
        });
    }

    private void startActivitiForCountList(int prioriti_list) {
        Intent intent = new Intent(this.getContext(), Look_List.class);
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

    class MainActivityAsyncTask extends AsyncTask<Integer, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(Integer... integers) {
            ArrayList<String> list = null;
            Log.v("List_work", "Класс MainActivityAsyncTask метод doInBackground запущен");
            switch (integers[0]) {
                case 1:
                    list = new ListWorkSQLiteOpenHelper(getContext()).getList(1);
                    break;
                case 2:
                    list = new ListWorkSQLiteOpenHelper(getContext()).getList(2);
                    break;
                case 3:
                    list = new ListWorkSQLiteOpenHelper(getContext()).getList(3);
                    break;
                case 4:
                    list = new ListWorkSQLiteOpenHelper(getContext()).getList(4);
                    break;
            }
            return list;
        }
    }
}
