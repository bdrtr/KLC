package com.example.sorular.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sorular.Adaptors.InvateAdaptor;
import com.example.sorular.Adaptors.ResultAdaptor;
import com.example.sorular.CardView.ResultCardView;
import com.example.sorular.DBO.ResultsDBO;
import com.example.sorular.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PageHome extends Fragment {
    private RecyclerView recyclerView;
    private View root;
    private ArrayList<ResultAdaptor> RA = new ArrayList<>();
    private ResultCardView resCard;
    private ResultsDBO resDBO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_page_home, container, false);

        definetions();
        resCard = new ResultCardView(getContext(),RA);
        recyclerView.setAdapter(resCard);
        LoadData();
        return root;
    }

    private void LoadData() {

        resDBO.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d: snapshot.getChildren()) {
                    ResultAdaptor i = d.getValue(ResultAdaptor.class);
                    RA.add(i);
                }

                resCard.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("----------","errrırır");
            }
        });
    }

    public void definetions() {
        recyclerView = root.findViewById(R.id.Recyc_home);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        resDBO = new ResultsDBO();
    }
}