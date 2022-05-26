package com.example.sorular.Adaptors;

import android.widget.TextView;

import java.io.Serializable;

public class QuestionAdaptor implements Serializable {
    private int id;
    private String body;
    private String trueOp,false1,false2,false3;

    public QuestionAdaptor(String body, String trueOp, String false1, String false2, String false3) {
        this.body = body;
        this.trueOp = trueOp;
        this.false1 = false1;
        this.false2 = false2;
        this.false3 = false3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTrueOp() {
        return trueOp;
    }

    public void setTrueOp(String trueOp) {
        this.trueOp = trueOp;
    }

    public String getFalse1() {
        return false1;
    }

    public void setFalse1(String false1) {
        this.false1 = false1;
    }

    public String getFalse2() {
        return false2;
    }

    public void setFalse2(String false2) {
        this.false2 = false2;
    }

    public String getFalse3() {
        return false3;
    }

    public void setFalse3(String false3) {
        this.false3 = false3;
    }
}
