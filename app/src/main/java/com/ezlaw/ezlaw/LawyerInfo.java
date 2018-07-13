package com.ezlaw.ezlaw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LawyerInfo extends AppCompatActivity {
    Button goButton;
    TextView phoneNumber;
    TextView barNumber;
    TextView lawFirm;
    TextView website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lawyer_info);

        goButton = (Button) findViewById(R.id.infoGo);

        phoneNumber = (TextView) findViewById(R.id.phoneNumber);
        barNumber = (TextView) findViewById(R.id.barNumber);
        lawFirm = (TextView) findViewById(R.id.lawFirm);
        website = (TextView) findViewById(R.id.website);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phoneNumber.getText() != null && barNumber.getText() != null && lawFirm.getText() != null){
                    Intent newIntent = new Intent(LawyerInfo.this, LawyerVerification.class);
                    newIntent.putExtra("bundle1", getIntent().getExtras());
                    newIntent.putExtra("Key", getIntent().getStringExtra("Key"));
                    Bundle bundle = new Bundle();
                    bundle.putString("phoneNumber", phoneNumber.getText().toString());
                    bundle.putString("barNumber", barNumber.getText().toString());
                    bundle.putString("lawFirm", lawFirm.getText().toString());
                    bundle.putString("website", website.getText().toString());
                    newIntent.putExtra("bundle2", bundle);
                    startActivity(newIntent);
                }
            }
        });
    }
}
