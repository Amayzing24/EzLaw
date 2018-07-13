package com.ezlaw.ezlaw;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;

public class Questions extends AppCompatActivity {

    Button button1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);


        button1 = (Button) findViewById(R.id.bt4);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Questions.this, Rights.class);
                startActivity(i);
            }
        });

        button1 = (Button) findViewById(R.id.bt3);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Questions.this, Questions.class);
                startActivity(i);
            }
        });
    }

}