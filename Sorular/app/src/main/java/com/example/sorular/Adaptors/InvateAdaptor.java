package com.example.sorular.Adaptors;

import java.io.Serializable;

public class InvateAdaptor implements Serializable {

    private int questions;
    private int shuffled;
    private int time;
    private String user;

    public InvateAdaptor() {
    }

    public InvateAdaptor(String user, int questions, int shuffled, int time) {
        this.questions = questions;
        this.shuffled = shuffled;
        this.time = time;
        this.user = user;
    }

    public int getQuestions() {
        return questions;
    }

    public void setQuestions(int questions) {
        this.questions = questions;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getShuffled() {
        return shuffled;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setShuffled(int shuffled) {
        this.shuffled = shuffled;
    }
}
