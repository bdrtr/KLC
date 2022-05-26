package com.example.sorular;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sorular.Adaptors.InvateAdaptor;
import com.example.sorular.Adaptors.QuestionAdaptor;
import com.example.sorular.Adaptors.ResultAdaptor;
import com.example.sorular.DBO.DatabaseCopyHelper;
import com.example.sorular.DBO.DatabaseHelper;
import com.example.sorular.DBO.QuestionDBO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class StartActivity extends AppCompatActivity {
    private TextView question;
    private TextView A,B,C,D;
    private TextView time_start_page;

    private InvateAdaptor invate;
    public ArrayList<QuestionAdaptor> questions_list = new ArrayList<>();
    private DatabaseHelper vt;
    public HashSet<String> shuffle_hashset = new HashSet<>();
    private ArrayList<String> options;
    private QuestionAdaptor current_question;
    private int turn,truecounter,falsecounter;
    long getTime =0;
    private CountDownTimer counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        kopyala();
        definetion();
        take();
        int ms = 1000;
        counter = new CountDownTimer((long) invate.getTime() *ms,1*ms) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_start_page.setText(String.valueOf(millisUntilFinished/ms));
                getTime = millisUntilFinished/ms;
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(),"time over",Toast.LENGTH_SHORT).show();
            }
        }.start();
        start();

    }

    public void kopyala() {
        DatabaseCopyHelper helper = new DatabaseCopyHelper(this);
        try {
            helper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        helper.openDataBase();
    }

    public void definetion(){
        question = findViewById(R.id.question_start);
        A = findViewById(R.id.Astart);
        B = findViewById(R.id.Bstart);
        C = findViewById(R.id.Cstart);
        D = findViewById(R.id.Dstart);
        time_start_page = findViewById(R.id.time_start_page);
        vt = new DatabaseHelper(getApplicationContext());
        turn = 0;
    }

    public void take() {
        invate = (InvateAdaptor) getIntent().getSerializableExtra("nesne");
        questions_list = new QuestionDBO().getQuestions(vt,invate.getQuestions(),invate,getApplicationContext());

    }

    public void updateValues() {
        question.setText(String.valueOf(turn+1)+") "+current_question.getBody());

        String A_x = options.get(0);
        String B_x = options.get(1);
        String C_x = options.get(2);
        String D_x = options.get(3);

        Log.i("------------",A_x);
        Log.i("------------",B_x);
        Log.i("------------",C_x);
        Log.i("------------",D_x);

        A.setText("ا)"+A_x);
        B.setText("ب)"+B_x);
        C.setText("ج)"+C_x);
        D.setText("د)"+D_x);

    }


    public void shuffleList() {
        options = new ArrayList<>();
        shuffle_hashset.add(current_question.getTrueOp());
        shuffle_hashset.add(current_question.getFalse1());
        shuffle_hashset.add(current_question.getFalse2());
        shuffle_hashset.add(current_question.getFalse3());

        for (String i : shuffle_hashset) {
            options.add(i);
        }

    }

    public void control(String req) {
        if (current_question.getTrueOp().equals(req)) {
            truecounter++;
        }
        else {
            falsecounter++;
        }
        turn++;
        options.clear();
        shuffle_hashset.clear();
        start();
    }

    public void start() {
        if (turn == invate.getQuestions()) {
            Intent i = new Intent(getApplicationContext(),EndPage.class);
            ResultAdaptor re = new ResultAdaptor(invate.getSender(),truecounter,falsecounter,getTime);
            i.putExtra("nesne",re);
            i.putExtra("invate",invate);
            counter.cancel();
            startActivity(i);
            turn = 0;
        }

        current_question = questions_list.get(turn);
        shuffleList();
        updateValues();

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                control(options.get(0));
            }
        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                control(options.get(1));
            }
        });

        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                control(options.get(2));
            }
        });

        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                control(options.get(3));
            }
        });
    }
}