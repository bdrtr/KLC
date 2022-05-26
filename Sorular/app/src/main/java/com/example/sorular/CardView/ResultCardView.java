package com.example.sorular.CardView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sorular.Adaptors.ResultAdaptor;
import com.example.sorular.R;
import java.util.ArrayList;

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
        holder.time.setText("kullanılan süre: "+ra.getTimeOb());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class HolderDesign extends RecyclerView.ViewHolder {
        private TextView name,trueS,falseS,time;
        public HolderDesign(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_Result);
            trueS = itemView.findViewById(R.id.true_Result);
            falseS = itemView.findViewById(R.id.false_Result);
            time = itemView.findViewById(R.id.time_Result);

        }
    }
}
