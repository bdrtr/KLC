package com.example.sorular;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sorular.Adaptors.InvateAdaptor;
import com.example.sorular.DBO.InvateAdaptorDBO;
import com.example.sorular.Fragments.PageDashboard;
import com.example.sorular.Fragments.PageDashboardArgs;

import java.util.Locale;

public class AddInvate extends AppCompatActivity {

    private EditText questions;
    private EditText shuffled;
    private EditText time;
    private Button conf;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_invate);

        definetions();
        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int question_i = Integer.parseInt(questions.getText().toString());
                int shuffled_i = Integer.parseInt(shuffled.getText().toString());
                int time_i = Integer.parseInt(time.getText().toString());

                String name = getIntent().getStringExtra("name");
                Log.i("----------+++------",name);
                InvateAdaptor invate = new InvateAdaptor(name,question_i,shuffled_i,time_i);

                InvateAdaptorDBO invateAdaptorDBO = new InvateAdaptorDBO();
                invateAdaptorDBO.add(invate);

                Intent i = new Intent(getApplicationContext(), PageDashboard.class);
                i.putExtra("nesne",invate);
                startActivity(i);
            }
        });
    }

    public void definetions() {
        questions = findViewById(R.id.questions_add_page);
        shuffled = findViewById(R.id.shuffle_add_page);
        time = findViewById(R.id.time_add_page);
        conf = findViewById(R.id.confirm_add_page);

    }
}