package com.example.sorular;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class StartActivity extends AppCompatActivity {
    private TextView question,A,B,C,D;
    private TextView time_start_page;
    private Button backButton;

    private InvateAdaptor invate;
    public ArrayList<QuestionAdaptor> questions_list = new ArrayList<>();
    private DatabaseHelper vt;
    public HashSet<String> shuffle_hashset = new HashSet<>();
    private ArrayList<String> options;
    private QuestionAdaptor current_question;
    private int turn,truecounter,falsecounter;
    long getTime =0;
    private CountDownTimer counter;
    private ArrayList<Integer> saveWrongQuestion = new ArrayList<>();
    private ArrayList<Integer> QuestionsQueue = new ArrayList<>();
    //private HashMap<Integer,String> answers = new HashMap<Integer,String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        COPY();
        definetion();
        take();

        int ms = 1000;
        counter = new CountDownTimer((long) invate.getTime()*60*ms,1*ms) {
            @Override
            public void onTick(long millisUntilFinished) {
                getTime = millisUntilFinished/ms;
                time_start_page.setText(String.valueOf(getTime));
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(),"time over",Toast.LENGTH_SHORT).show();
            }
        }.start();
        start();

    }

    public void COPY() {
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

        backButton = findViewById(R.id.back_button);
        time_start_page = findViewById(R.id.time_start_page);
        vt = new DatabaseHelper(getApplicationContext());
        turn = 0;

    }

    public void take() {
        invate = (InvateAdaptor) getIntent().getSerializableExtra("nesne");
        questions_list = new QuestionDBO().getQuestions(vt,invate.getQuestions(),invate);

    }

    public void updateValues() {
        question.setText(String.valueOf(turn+1)+") "+current_question.getBody());//question order

        A.setText("ا)"+options.get(0));
        B.setText("ب)"+options.get(1));
        C.setText("ج)"+options.get(2));
        D.setText("د)"+options.get(3));
    }


    public void shuffleList() {
        options = new ArrayList<>();
        shuffle_hashset.clear();
        shuffle_hashset.add(current_question.getTrueOp());
        shuffle_hashset.add(current_question.getFalse1());
        shuffle_hashset.add(current_question.getFalse2());
        shuffle_hashset.add(current_question.getFalse3());

        for (String s : shuffle_hashset) {
            options.add(s);
        }

    }
    public void setColor(boolean t, int btn) {
        if (t) {
            switch (btn) {
                case 0:
                    A.setBackgroundColor(Color.GREEN);
                    A.setTextColor(Color.WHITE);
                    break;
                case 1:
                    B.setBackgroundColor(Color.GREEN);
                    B.setTextColor(Color.WHITE);
                    break;
                case 2:
                    C.setBackgroundColor(Color.GREEN);
                    C.setTextColor(Color.WHITE);
                    break;
                case 3:
                    D.setBackgroundColor(Color.GREEN);
                    D.setTextColor(Color.WHITE);
                    break;
            }
        } else {
            switch (btn) {
                case 0:
                    A.setBackgroundColor(Color.RED);
                    A.setTextColor(Color.WHITE);
                    break;
                case 1:
                    B.setBackgroundColor(Color.RED);
                    B.setTextColor(Color.WHITE);
                    break;
                case 2:
                    C.setBackgroundColor(Color.RED);
                    C.setTextColor(Color.WHITE);
                    break;
                case 3:
                    D.setBackgroundColor(Color.RED);
                    D.setTextColor(Color.WHITE);
                    break;
            }
        }
    }

    public void turnDefault() {
        A.setBackgroundColor(Color.WHITE);
        A.setTextColor(Color.BLACK);
        B.setBackgroundColor(Color.WHITE);
        B.setTextColor(Color.BLACK);
        C.setBackgroundColor(Color.WHITE);
        C.setTextColor(Color.BLACK);
        D.setBackgroundColor(Color.WHITE);
        D.setTextColor(Color.BLACK);
    }

    public void control(String req, int btn)  {
        if (current_question.getTrueOp().equals(req)) {
            truecounter++;
            QuestionsQueue.add(1);
            setColor(true,btn);
        }
        else {
            falsecounter++;
            //setColor(false,btn);
            QuestionsQueue.add(0);
            Toast.makeText(getApplicationContext(),"Yanlıs yaptın",Toast.LENGTH_SHORT).show();
            saveWrongQuestion.add(current_question.getId());
        }
        updateValues();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        turn++;
        //options.clear();
        //shuffle_hashset.clear();
        start();
    }

    public void start() {
        if (turn == invate.getQuestions()) {
            Intent i = new Intent(getApplicationContext(),EndPage.class);
            ResultAdaptor result = new ResultAdaptor(invate.getUser(),truecounter,falsecounter,getTime); // getTime kalan zaman
            result.setWrongs(saveWrongQuestion);
            i.putExtra("resultObject",result);
            i.putExtra("invate",invate);

            counter.cancel();
            startActivity(i);
            turn = 0;
        }

        current_question = questions_list.get(turn);
        shuffleList();
        turnDefault();
        updateValues();

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                control(options.get(0),0);
            }
        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                control(options.get(1),1);
            }
        });

        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                control(options.get(2),2);
            }
        });

        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                control(options.get(3),3);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (turn==0) {
                    Toast.makeText(getApplicationContext(),"Daha geri gidilemez",Toast.LENGTH_SHORT).show();
                    start();
                } else {
                    if (QuestionsQueue.get(turn-1).equals(1)) { // when you back, last answer going to turn first sate
                        truecounter--;
                    }
                    else {
                        falsecounter--;
                    }
                    turn--;
                    start();
                }

            }
        });
    }
}