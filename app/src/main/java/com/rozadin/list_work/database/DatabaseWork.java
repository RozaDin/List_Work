package com.rozadin.list_work.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseWork {
    private static SQLiteDatabase dbWrite = null;
    private static SQLiteDatabase dbRead = null;

    private static ArrayList<String> list1 = null, list2 = null, list3 = null, list4 = null;

    public DatabaseWork(Context context) {
        if (dbRead == null) dbRead = new ListWorkSQLiteOpenHelper(context).getReadableDatabase();
        if (dbWrite == null) dbWrite = new ListWorkSQLiteOpenHelper(context).getWritableDatabase();
    }

    public static ArrayList<String> getList1() {
        if (list1 == null) {
            try {
                Cursor cursor = dbRead.query("TABLE1", new String[]{"TEXT_NOTE"},
                        null, null, null, null, null);
                int N = cursor.getColumnCount();
                for (int i = 0; i < N; i++) {
                    if (i == 0 && cursor.moveToFirst()) ;
                    list1.add(cursor.getString(0));
                    if (i != (N - 1) && cursor.moveToNext()) ;
                }
                cursor.close();
            } catch (SQLException e) {
                Log.e("List_work", e.getMessage());
            }
        }
        return list1;
    }

    public static ArrayList<String> getList2() {
        if (list2 == null) {
            try {
                Cursor cursor = dbRead.query("TABLE2", new String[]{"TEXT_NOTE"},
                        null, null, null, null, null);
                int N = cursor.getColumnCount();
                for (int i = 0; i < N; i++) {
                    if (i == 0 && cursor.moveToFirst()) ;
                    list2.add(cursor.getString(0));
                    if (i != (N - 1) && cursor.moveToNext()) ;
                }
                cursor.close();
            } catch (SQLException e) {
                Log.e("List_work", e.getMessage());
            }
        }
        return list2;
    }

    public static ArrayList<String> getList3() {
        if (list3 == null) {
            try {
                Cursor cursor = dbRead.query("TABLE3", new String[]{"TEXT_NOTE"},
                        null, null, null, null, null);
                int N = cursor.getColumnCount();
                for (int i = 0; i < N; i++) {
                    if (i == 0 && cursor.moveToFirst()) ;
                    list3.add(cursor.getString(0));
                    if (i != (N - 1) && cursor.moveToNext()) ;
                }
                cursor.close();
            } catch (SQLException e) {
                Log.e("List_work", e.getMessage());
            }
        }
        return list3;
    }

    public static ArrayList<String> getList4() {
        if (list4 == null) {
            try {
                Cursor cursor = dbRead.query("TABLE4", new String[]{"TEXT_NOTE"},
                        null, null, null, null, null);
                int N = cursor.getColumnCount();
                for (int i = 0; i < N; i++) {
                    if (i == 0 && cursor.moveToFirst()) ;
                    list4.add(cursor.getString(0));
                    if (i != (N - 1) && cursor.moveToNext()) ;
                }
                cursor.close();
            } catch (SQLException e) {
                Log.e("List_work", e.getMessage());
            }
        }
        return list4;
    }

}
