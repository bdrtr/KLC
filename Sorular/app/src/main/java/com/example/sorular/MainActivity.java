package com.example.sorular;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView welcome_text;
    private EditText get_name;
    private Button next_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        definetions();
        takeName();

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = get_name.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    name = "empty";
                }
                Toast.makeText(getApplicationContext(),"Merhaba "+ name,Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(MainActivity.this,MainPage.class);
                SharedPreferences sp = getSharedPreferences("NamesTable",MODE_PRIVATE);
                SharedPreferences.Editor editS = sp.edit();
                editS.putString("userName",name);
                editS.commit();
                //intent.putExtra("name",name);
                startActivity(new Intent(MainActivity.this, MainPage.class));
            }
        });



    }

    public void takeName() {
        SharedPreferences sp = getSharedPreferences("NamesTable",MODE_PRIVATE);
        get_name.setText(sp.getString("userName",""));

    }
    public void definetions() {
        welcome_text = findViewById(R.id.WELCOME_text_main);
        get_name = findViewById(R.id.get_name_main);
        next_button = findViewById(R.id.next_button_main);
    }
}