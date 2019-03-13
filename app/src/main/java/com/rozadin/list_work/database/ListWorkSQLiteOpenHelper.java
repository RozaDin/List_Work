package com.rozadin.list_work.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class ListWorkSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "ListWork"; // Имя базы данных
    private static final int DB_VERSION = 1; // Версия базы данных
    private final static String nameTable = "TABLEWORKLIST";

    public ListWorkSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + nameTable + " (_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "TEXT_NOTE TEXT,\n" +
                "WORKLIST INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<String> getList(int COUNTLIST) {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = this.getReadableDatabase();
            Log.v("List_work", "БД создана");
            cursor = db.query(nameTable, new String[]{"TEXT_NOTE"},
                    "WORKLIST = ?", new String[]{Integer.toString(COUNTLIST)},
                    null, null, null);
            int N = cursor.getCount();
            for (int i = 0; i < N; i++) {
                if (i == 0 && cursor.moveToFirst()) ;
                list.add(cursor.getString(0));
                if (i != (N - 1) && cursor.moveToNext()) ;
            }
            cursor.close();
            Log.v("List_work", "БД создана и прочитана, в БД " + N + " записей, таблица " + COUNTLIST);
        } catch (SQLException e) {
            Log.e("List_work", e.getMessage());
        } finally {
            cursor.close();
            db.close();
        }
        if (list.size() == 0) list.add("пусто");
        return list;
    }

    public void deleteList(String Text_Note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(nameTable,
                "TEXT_Note = ?",
                new String[]{Text_Note});
        Log.v("List_work", "Запись " + Text_Note + " удалена из БД");
        db.close();
    }

    public void insertList(String Text, int COUNTLIST) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TEXT_NOTE", Text);
        contentValues.put("WORKLIST", COUNTLIST);
        db.insert(nameTable,
                null,
                contentValues);
        Log.v("List_work", "Запись " + Text + " добавлена в БД  список " + COUNTLIST);
        db.close();
    }

    public void updateList(String oldText, String newText, int COUNTLIST) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TEXT_NOTE", newText);
        contentValues.put("WORKLIST", COUNTLIST);
        db.update(nameTable,
                contentValues,
                "TEXT_NOTE = ?",
                new String[]{oldText});
        Log.v("List_work", "Запись " + oldText + " обновлена до записи " + newText);
        db.close();
    }
}
