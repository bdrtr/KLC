package com.example.sorular.CardView;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sorular.Adaptors.ResultAdaptor;
import com.example.sorular.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class ResultCardView extends RecyclerView.Adapter<ResultCardView.HolderDesign> {

    private Context cnt;
    private ArrayList<ResultAdaptor> arrayList;

    public ResultCardView() {
    }

    public ResultCardView(Context cnt, ArrayList<ResultAdaptor> arrayList) {
        this.cnt = cnt;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public HolderDesign onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cnt).inflate(R.layout.card_homepage,parent,false);
        return new HolderDesign(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDesign holder, int position) {
        ResultAdaptor ra = arrayList.get(position);
        holder.name.setText("isim: "+ra.getName());
        holder.trueS.setText("doğru sayısı: "+ra.getTrueOptions());
        holder.falseS.setText("yanlış sayısı: "+ra.getFalseOptions());
        String time = "";
        if (ra.getTimeOb() == 0) {
            time = "zaman aşıldı";
        }else {
            time = String.valueOf(ra.getTimeOb());
        }
        holder.time.setText("kullanılan süre: "+time);

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class HolderDesign extends RecyclerView.ViewHolder {
        private TextView name,trueS,falseS,time;
        private CardView cv;
        public HolderDesign(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_Result);
            trueS = itemView.findViewById(R.id.true_Result);
            falseS = itemView.findViewById(R.id.false_Result);
            time = itemView.findViewById(R.id.time_Result);
            cv = itemView.findViewById(R.id.card_home);

        }
    }
}
