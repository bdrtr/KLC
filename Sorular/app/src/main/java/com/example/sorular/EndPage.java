package com.example.sorular;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sorular.Adaptors.InvateAdaptor;
import com.example.sorular.Adaptors.ResultAdaptor;
import com.example.sorular.DBO.ResultsDBO;
import com.example.sorular.Fragments.PageDashboard;

public class EndPage extends AppCompatActivity {

    private TextView who,trueS,falseS,timeS;
    private Button send;
    private ResultAdaptor resultAdaptor;
    private InvateAdaptor invateAdaptor;
    private long usedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_page);
        definetions();
        take();
        fill();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getApplicationContext(), MainPage.class);
                ResultsDBO rdb = new ResultsDBO();
                SharedPreferences sp = getSharedPreferences("NamesTable",MODE_PRIVATE);
                resultAdaptor.setName(sp.getString("userName",""));
                resultAdaptor.setTimeOb(usedTime);
                rdb.add(resultAdaptor);
                startActivity(new Intent(getApplicationContext(), MainPage.class));
            }
        });
    }

    public void definetions() {
        who = findViewById(R.id.who_end);
        trueS = findViewById(R.id.true_end);
        falseS = findViewById(R.id.false_end);
        timeS = findViewById(R.id.time_end);
        send = findViewById(R.id.send_end);
    }

    public void take() {
        resultAdaptor = (ResultAdaptor) getIntent().getSerializableExtra("resultObject");
        invateAdaptor = (InvateAdaptor) getIntent().getSerializableExtra("invate");
    }

    public void fill() {

        who.setText("user: "+resultAdaptor.getName());
        trueS.setText("doğru sayısı: "+resultAdaptor.getTrueOptions());
        falseS.setText("yanlıs sayısı: "+resultAdaptor.getFalseOptions());
        usedTime = (long)invateAdaptor.getTime()*60-resultAdaptor.getTimeOb();
        timeS.setText("harcanan zaman: "+usedTime);
    }
}