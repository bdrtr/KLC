package com.example.sorular.DBO;


import com.example.sorular.Adaptors.InvateAdaptor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InvateAdaptorDBO {

    private ArrayList<InvateAdaptor> datas = new ArrayList<>();
    private DatabaseReference dbref;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String user_name;

    public InvateAdaptorDBO() {
        dbref = database.getReference(InvateAdaptorDBO.class.getSimpleName());
    }

    public Task<Void> add(InvateAdaptor invate) {
        return dbref.push().setValue(invate);
    }

    public Task<Void> remove(String id) {
        return dbref.child(id).removeValue();
    }

    public Query get() {
        return dbref.orderByKey();
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
