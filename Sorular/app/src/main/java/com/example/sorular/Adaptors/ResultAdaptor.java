package com.example.sorular.Adaptors;

import java.io.Serializable;
import java.util.ArrayList;

public class ResultAdaptor implements Serializable {

    private String name;
    private int trueOptions;
    private int falseOptions;
    private long timeOb;
    private ArrayList<Integer> wrongs;

    public ResultAdaptor() {
    }

    public ResultAdaptor(String name, int trueOptions, int falseOptions, long timeOb) {
        this.name = name;
        this.trueOptions = trueOptions;
        this.falseOptions = falseOptions;
        this.timeOb = timeOb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTrueOptions() {
        return trueOptions;
    }

    public void setTrueOptions(int trueOptions) {
        this.trueOptions = trueOptions;
    }

    public int getFalseOptions() {
        return falseOptions;
    }

    public void setFalseOptions(int falseOptions) {
        this.falseOptions = falseOptions;
    }

    public long getTimeOb() {
        return timeOb;
    }

    public void setTimeOb(long timeOb) {
        this.timeOb = timeOb;
    }

    public ArrayList<Integer> getWrongs() {
        return wrongs;
    }

    public void setWrongs(ArrayList<Integer> wrongs) {
        this.wrongs = wrongs;
    }
}
