package com.rozadin.list_work.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ListWorkSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "ListWork"; // Имя базы данных
    private static final int DB_VERSION = 1; // Версия базы данных

    public ListWorkSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static void insertTable_1(SQLiteDatabase db, String text) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TEXT_NOTE", text);
        db.insert("TABLE1", null, contentValues);
    }

    public static void insertTable_2(SQLiteDatabase db, String text) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TEXT_NOTE", text);
        db.insert("TABLE2", null, contentValues);
    }

    public static void insertTable_3(SQLiteDatabase db, String text) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TEXT_NOTE", text);
        db.insert("TABLE3", null, contentValues);
    }

    public static void insertTable_4(SQLiteDatabase db, String text) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TEXT_NOTE", text);
        db.insert("TABLE4", null, contentValues);
    }

    public static void updateTable_1(SQLiteDatabase db, String oldText, String newText) {
        db.execSQL("update TABLE1 SET TEXT_NOTE = " + newText + " where TEXT_NOTE = " + oldText);
    }

    public static void updateTable_2(SQLiteDatabase db, String oldText, String newText) {
        db.execSQL("update TABLE2 SET TEXT_NOTE = " + newText + " where TEXT_NOTE = " + oldText);
    }

    public static void updateTable_3(SQLiteDatabase db, String oldText, String newText) {
        db.execSQL("update TABLE3 SET TEXT_NOTE = " + newText + " where TEXT_NOTE = " + oldText);
    }

    public static void updateTable_4(SQLiteDatabase db, String oldText, String newText) {
        db.execSQL("update TABLE4 SET TEXT_NOTE = " + newText + " where TEXT_NOTE = " + oldText);
    }

    public static void deleteTable_1(SQLiteDatabase db, String text) {
        db.execSQL("delete TABLE1 where TEXT_NOTE = " + text);
    }

    public static void deleteTable_2(SQLiteDatabase db, String text) {
        db.execSQL("delete TABLE2 where TEXT_NOTE = " + text);
    }

    public static void deleteTable_3(SQLiteDatabase db, String text) {
        db.execSQL("delete TABLE3 where TEXT_NOTE = " + text);
    }

    public static void deleteTable_4(SQLiteDatabase db, String text) {
        db.execSQL("delete TABLE4 where TEXT_NOTE = " + text);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TABLE1 (_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "TEXT_NOTE TEXT)");
        db.execSQL("CREATE TABLE TABLE2 (_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "TEXT_NOTE TEXT)");
        db.execSQL("CREATE TABLE TABLE3 (_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "TEXT_NOTE TEXT)");
        db.execSQL("CREATE TABLE TABLE4 (_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "TEXT_NOTE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
