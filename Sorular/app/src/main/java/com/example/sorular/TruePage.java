package com.example.sorular;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sorular.Adaptors.InvateAdaptor;

public class TruePage extends AppCompatActivity {

    private TextView questions,sender,time;
    private Button btn;
    private String sendertext;
    private int time_i, shuffled_i,questions_i;
    private InvateAdaptor invate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_page);
        take();
        description();
        fill();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),StartActivity.class);
                i.putExtra("nesne",invate);
                startActivity(i);
            }
        });
    }

    public void take() {
        invate = (InvateAdaptor) getIntent().getSerializableExtra("nesne");
        sendertext = invate.getUser();
        time_i = invate.getTime();
        shuffled_i = invate.getShuffled();
        questions_i = invate.getQuestions();
    }

    public void fill() {
        questions.setText("questions : "+questions_i);
        time.setText("time: "+time_i);
        sender.setText("sender: "+sendertext);

    }
    public void description() {
        questions = findViewById(R.id.question_true_page);
        sender = findViewById(R.id.sender_true_page);
        time = findViewById(R.id.time_true_page);
        btn = findViewById(R.id.start_true_page);
    }
}