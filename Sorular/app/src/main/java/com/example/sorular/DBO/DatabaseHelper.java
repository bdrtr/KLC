package com.example.sorular.DBO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "bayrakquiz.sqlite", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS 'Sorular' ("+
                "'soru_id'	INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "'Soru'	TEXT,"+
                "'Soru_cevap'	TEXT," +
                "'false1' TEXT," +
                "'false2' TEXT, " +
                "'false3' TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS 'Sorular'");
        onCreate(db);
    }
}
