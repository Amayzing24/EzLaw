package com.ezlaw.ezlaw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Immigration02 extends AppCompatActivity{

    Button button1;
    Button button2;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immigration2);


        button1 = (Button) findViewById(R.id.bt2);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        button2 = (Button) findViewById(R.id.bt4);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i  = new Intent(Immigration02.this, Immigration.class);
                startActivity(i);
                finish();
            }
        });




        textView = (TextView) findViewById(R.id.txtView3);

        textView.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.immigration_ice2, 0, 0, 0);

        textView.setMovementMethod(new ScrollingMovementMethod());














    }

}

