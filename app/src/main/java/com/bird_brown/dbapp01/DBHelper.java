package com.bird_brown.dbapp01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "test"; //データベース名
    private static int DB_VERSION = 1;  //バージョン

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        //テーブル作成SQL文
        //CREATE TABLE Fruits(id INTEGER AUTOINCREMENT,name TEST NOT NULL);
        String sql = "CREATE TABLE Fruits(";
        sql += "id INTEGER PRIMARY KEY AUTOINCREMENT,";
        sql += "name TEXT NOT NULL";
        sql += ");";

        //テーブル作成
        database.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

    }
}
