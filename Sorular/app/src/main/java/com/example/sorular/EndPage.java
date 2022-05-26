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
    private ResultAdaptor ra;
    private InvateAdaptor inv;
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
                Intent intent = new Intent(getApplicationContext(), MainPage.class);
                ResultsDBO rdb = new ResultsDBO();
                SharedPreferences sp = getSharedPreferences("All_names",MODE_PRIVATE);
                String user_name = sp.getString("name","");
                ra.setName(user_name);
                rdb.add(ra);
                startActivity(intent);
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
        ra = (ResultAdaptor) getIntent().getSerializableExtra("nesne");
        inv = (InvateAdaptor) getIntent().getSerializableExtra("invate");
    }

    public void fill() {

        who.setText("user: "+ra.getName());
        trueS.setText("doğru sayısı: "+ra.getTrueOptions());
        falseS.setText("yanlıs sayısı: "+ra.getFalseOptions());
        long usedTime = (long)inv.getTime()-ra.getTimeOb();
        timeS.setText("harcanan zaman: "+usedTime);
    }
}