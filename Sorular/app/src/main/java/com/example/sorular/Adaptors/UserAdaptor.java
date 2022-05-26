package com.example.sorular.Adaptors;

import java.io.Serializable;

public class UserAdaptor implements Serializable {
    private String name;
    private int id;
    private int Ord;

    public UserAdaptor(String name, int id, int ord) {
        this.name = name;
        this.id = id;
        Ord = ord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrd() {
        return Ord;
    }

    public void setOrd(int ord) {
        Ord = ord;
    }
}
