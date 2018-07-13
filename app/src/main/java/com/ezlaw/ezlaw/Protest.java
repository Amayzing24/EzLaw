package com.ezlaw.ezlaw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Protest extends AppCompatActivity {

    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protest);

        button1 = (Button) findViewById(R.id.bt2);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        TextView textView = (TextView) findViewById(R.id.txtView3);

        textView.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.protest, 0, 0, 0);


    }
}
