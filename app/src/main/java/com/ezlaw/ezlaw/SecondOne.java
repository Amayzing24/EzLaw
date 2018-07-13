package com.ezlaw.ezlaw;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SecondOne extends AppCompatActivity {

    Button button;
    Button goButton;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    RadioButton client;
    RadioButton lawyer;
    Intent lastIntent;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_one);

        button = (Button) findViewById(R.id.logout);
        mAuth = FirebaseAuth.getInstance();

        goButton = (Button) findViewById(R.id.button3);
        client = (RadioButton)findViewById(R.id.client);
        lawyer = (RadioButton)findViewById(R.id.lawyer);
        lastIntent = getIntent();



        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(SecondOne.this, MainActivity.class));
                }
            }
        };

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                mAuth.signOut();
            }
        });

        goButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent newIntent;
                if (client.isChecked()){
                    newIntent = new Intent(SecondOne.this, LawyerMain.class);
                    newIntent.putExtra("bundle1", lastIntent.getExtras());
                    newIntent.putExtra("Key", "Client");
                    startActivity(newIntent);
                }
                else if (lawyer.isChecked()) {
                    newIntent = new Intent(SecondOne.this, LawyerInfo.class);
                    newIntent.putExtras(lastIntent.getExtras());
                    newIntent.putExtra("Key", "Lawyer");
                    startActivity(newIntent);
                }
            }
        });
    }
}
