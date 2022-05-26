package com.example.sorular.DBO;

import com.example.sorular.Adaptors.InvateAdaptor;
import com.example.sorular.Adaptors.ResultAdaptor;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class ResultsDBO {

    private ArrayList<ResultAdaptor> results = new ArrayList<>();
    private DatabaseReference dbref;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String user_name;

    public ResultsDBO() {
        dbref = database.getReference(ResultAdaptor.class.getSimpleName());
    }

    public Task<Void> add(ResultAdaptor result) {
        return dbref.push().setValue(result);
    }

    public Task<Void> remove(String id) {
        return dbref.child(id).removeValue();
    }

    public Query get() {
        return dbref.orderByKey();
    }

}
