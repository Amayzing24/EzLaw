package com.ezlaw.ezlaw;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Rights extends AppCompatActivity {

    Button button;
    Spinner mySpinner;
    Button button1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rights);

        mySpinner = (Spinner) findViewById(R.id.sp1);

        final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Rights.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);


        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String text=parent.getItemAtPosition(position).toString();
                if (text.equals("Border Zone rights")){
                    Intent j  = new Intent(Rights.this, BorderZone.class);
                    startActivity(j);
                }
                else if (text.equals("Protest rights")){
                    Intent k  = new Intent(Rights.this, Protest.class);
                    startActivity(k);
                }
                else if (text.equals("Religious rights")){
                    Intent l  = new Intent(Rights.this, Religious.class);
                    startActivity(l);
                }
                else if (text.equals("Housing rights")){
                    Intent m = new Intent(Rights.this, Housing.class);
                    startActivity(m);
                }
                else if (text.equals("Transgender rights")){
                    Intent n  = new Intent(Rights.this, Transgender.class);
                    startActivity(n);
                }
                else if (text.equals("Sexual Assault rights")){
                    Intent o  = new Intent(Rights.this, SexualAssault.class);
                    startActivity(o);
                }
                else if (text.equals("General Civil rights")){
                    Intent p  = new Intent(Rights.this, General.class);
                    startActivity(p);
                }
                else if (text.equals("Rights when stopped by police")){
                    Intent q  = new Intent(Rights.this, PoliceRights.class);
                    startActivity(q);
                }
                else if (text.equals("Voting rights")){
                    Intent r  = new Intent(Rights.this, Voting.class);
                    startActivity(r);
                }
                else if (text.equals("Court rights")){
                    Intent i  = new Intent(Rights.this, Court.class);
                    startActivity(i);
                }



            }
            public void onNothingSelected(AdapterView<?> parent)
            { }
        });

        button = (Button) findViewById(R.id.bt1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String s=mySpinner.getSelectedItem().toString();
                if (s.equals("Immigrant rights")){
                    Intent i  = new Intent(Rights.this, Immigration.class);
                    startActivity(i);
                }



            }
        });

/*
        button1 = (Button) findViewById(R.id.bt4);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i  = new Intent(Rights.this, Rights.class);
                startActivity(i);
            }
        });

*/


        button1 = (Button) findViewById(R.id.bt3);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });





    }



}





