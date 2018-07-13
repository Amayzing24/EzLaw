package com.ezlaw.ezlaw;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LawyerVerification extends AppCompatActivity {

    private static int TIME_OUT = 3000; //Time to launch the another activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lawyer_verification);

        final Bundle bundle1 = getIntent().getBundleExtra("bundle1");
        final Bundle bundle2 = getIntent().getBundleExtra("bundle2");
        String allOut = (String)bundle1.get("Name") + (String)bundle2.get("phoneNumber")
                + (String)bundle2.get("barNumber") + (String)bundle2.get("lawFirm");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(LawyerVerification.this, LawyerMain.class);
                i.putExtra("bundle1", bundle1);
                i.putExtra("bundle2", bundle2);
                i.putExtra("Key", getIntent().getStringExtra("Key"));
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }


}
