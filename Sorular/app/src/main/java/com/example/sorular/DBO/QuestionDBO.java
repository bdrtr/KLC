package com.example.sorular.DBO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sorular.Adaptors.InvateAdaptor;
import com.example.sorular.Adaptors.QuestionAdaptor;
import java.util.ArrayList;

public class QuestionDBO {

    public ArrayList<QuestionAdaptor> getQuestions(DatabaseHelper vt, int num, InvateAdaptor invate) {
        ArrayList<QuestionAdaptor> questionAdaptors = new ArrayList<>();
        SQLiteDatabase dbx = vt.getWritableDatabase();
        Cursor c = dbx.rawQuery("SELECT * FROM Sorular LIMIT "+num,null);

        while(c.moveToNext()) {
            QuestionAdaptor myQuestion = new QuestionAdaptor(
                    c.getString(c.getColumnIndexOrThrow("Soru")),
                    c.getString(c.getColumnIndexOrThrow("Soru_cevap")),
                    c.getString(c.getColumnIndexOrThrow("false1")),
                    c.getString(c.getColumnIndexOrThrow("false2")),
                    c.getString(c.getColumnIndexOrThrow("false3")));
            myQuestion.setId(c.getInt(c.getColumnIndexOrThrow("soru_id")));
            questionAdaptors.add(myQuestion);
        }

        dbx.close();
        return questionAdaptors;
    }

    public void Add(QuestionAdaptor qa,DatabaseHelper vt) {
        SQLiteDatabase dbx = vt.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Soru",qa.getBody());
        values.put("Soru_cevap",qa.getTrueOp());
        values.put("false1",qa.getFalse1());
        values.put("false2",qa.getFalse2());
        values.put("false3",qa.getFalse3());

        dbx.insertOrThrow("Sorular",null,values);
        dbx.close();
    }

}
