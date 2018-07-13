package com.ezlaw.ezlaw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewQuestion extends AppCompatActivity {

    Spinner selectTopic;
    TextView questionText;
    TextView additionalText;
    Button postButton;
    String selectedItem;

    String name;

    Bundle bundle1;

    DatabaseReference questionReference;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_question);

        questionReference = database.getReference("Questions");

        bundle1 = getIntent().getBundleExtra("bundle1");
        name = (String)bundle1.get("Name");

        selectTopic = findViewById(R.id.spinner);
        questionText = (TextView)findViewById(R.id.questionText);
        additionalText = (TextView)findViewById(R.id.additionalText);
        postButton = findViewById(R.id.postButton);
        selectedItem = null;

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qText = questionText.getText().toString();
                String aText = additionalText.getText().toString();
                if (selectTopic != null && qText != null){
                    Question newQuestion = new Question(name, selectedItem, qText, aText);
                    String id = questionReference.push().getKey();
                    newQuestion.setId(id);
                    questionReference.child(id).setValue(newQuestion);

                    finish();
                }
            }
        });

        selectTopic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
