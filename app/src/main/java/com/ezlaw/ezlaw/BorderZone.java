package com.ezlaw.ezlaw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class BorderZone extends AppCompatActivity {

    TextView textView;
    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_border_zone);

        button1 = (Button) findViewById(R.id.bt2);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        button2 = (Button) findViewById(R.id.bt3);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(BorderZone.this, BorderZone02.class);
                startActivity(i);
                finish();
            }
        });

        textView = (TextView) findViewById(R.id.txtView3);

        textView.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.borderzone_1, 0, 0, 0);


    }
}
