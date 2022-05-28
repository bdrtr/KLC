package com.example.sorular.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sorular.Adaptors.InvateAdaptor;
import com.example.sorular.AddInvate;
import com.example.sorular.CardView.CardViewV;
import com.example.sorular.DBO.InvateAdaptorDBO;
import com.example.sorular.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PageDashboard extends Fragment {

    private View root;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private InvateAdaptorDBO invateDBO;
    private CardViewV c;
    private ArrayList<InvateAdaptor> s = new ArrayList<>();
    private PageDashboardArgs pg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_page_dashboard, container, false);
        definetions();

        c = new CardViewV(getContext(),s);
        recyclerView.setAdapter(c);
        LoadData();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(getContext(), AddInvate.class);
                i.putExtra("name","");
                startActivity(i);
                */
                startActivity(new Intent(getContext(), AddInvate.class));
            }
        });

        return root;
    }

    public void definetions() {
        recyclerView = root.findViewById(R.id.recycle_view_dash);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fab = root.findViewById(R.id.floatingActionButton);
        invateDBO = new InvateAdaptorDBO();

    }

    private void LoadData() {

        invateDBO.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d: snapshot.getChildren()) {
                    InvateAdaptor i = d.getValue(InvateAdaptor.class);
                    s.add(i);
                }

                c.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

}