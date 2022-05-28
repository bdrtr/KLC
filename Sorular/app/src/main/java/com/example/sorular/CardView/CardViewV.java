package com.example.sorular.CardView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sorular.Adaptors.InvateAdaptor;
import com.example.sorular.R;
import com.example.sorular.TruePage;

import java.util.ArrayList;

public class CardViewV extends RecyclerView.Adapter<CardViewV.HolderDesign> {
    private Context cnt;
    private ArrayList<InvateAdaptor> invates;

    public CardViewV(Context cnt, ArrayList<InvateAdaptor> invates) {
        this.cnt = cnt;
        this.invates = invates;
    }

    @NonNull
    @Override
    public HolderDesign onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cnt).inflate(R.layout.cardinvate,parent,false);
        return new HolderDesign(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HolderDesign holder, int position) {
        InvateAdaptor invateAdaptor = invates.get(position);
        holder.sender.setText(invateAdaptor.getUser());
        holder.questions.setText(String.valueOf(invateAdaptor.getQuestions()));
        holder.time.setText(String.valueOf(invateAdaptor.getTime()));

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cnt, TruePage.class);
                intent.putExtra("nesne",invateAdaptor);
                cnt.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return invates.size();
    }

    public class HolderDesign extends RecyclerView.ViewHolder {

        private TextView sender;
        private TextView questions;
        private TextView time;
        private androidx.cardview.widget.CardView cv;

        public HolderDesign(@NonNull View itemView) {
            super(itemView);

            cv = itemView.findViewById(R.id.card_view);
            sender = itemView.findViewById(R.id.sent_card_view);
            questions = itemView.findViewById(R.id.question_card_view);
            time = itemView.findViewById(R.id.time_card_view);
        }
    }

}
