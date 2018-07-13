package com.ezlaw.ezlaw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class General extends AppCompatActivity {

    Button button1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        button1 = (Button) findViewById(R.id.bt22);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        TextView textView = (TextView) findViewById(R.id.txtView3);
        String text = "";
        textView.setText(text);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
}