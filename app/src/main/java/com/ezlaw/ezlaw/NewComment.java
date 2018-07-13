package com.ezlaw.ezlaw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewComment extends AppCompatActivity {
    final static String TAG = "NewComment";

    TextView commentText;
    Button postNewComment;
    Question qGlobal;

    String id;
    String currentUser;
    String currentUserInfo;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref;

    Intent lastIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_comment);

        lastIntent = getIntent();
        id = lastIntent.getStringExtra("ID");
        currentUser = lastIntent.getStringExtra("CurrentUser");
        currentUserInfo = lastIntent.getStringExtra("LawyerInfo");

        System.out.println(id);

        ref = database.getReference("Questions").child(id);

        commentText = (TextView)findViewById(R.id.commentText);
        postNewComment = findViewById(R.id.postNewComment);

        postNewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "OnClick called");
                String cText = commentText.getText().toString();
                Question q1 = getqGlobal();
                System.out.println(cText);
                if (cText != null && !(cText.equals("")) && q1 != null){
                    Log.d(TAG, "internal called");
                    Comment newComment = new Comment(currentUser, cText, currentUserInfo);
                    if (q1.getComments() != null){
                        q1.addComment(newComment);
                    }
                    else{
                        ArrayList<Comment> tempC = new ArrayList<>();
                        tempC.add(newComment);
                        q1.setComments(tempC);
                    }

                    ref.setValue(q1);

                    //Intent i = new Intent(NewComment.this, QuestionInfo.class);
                    //i.putExtra("bundle", lastIntent.getBundleExtra("bundle"));
                    //startActivity(i);
                    finish();
                }
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "Firebase called");
                Question q = dataSnapshot.getValue(Question.class);
                setqGlobal(q);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "Cancelled");
            }
        });
    }

    public void setqGlobal(Question q){
        qGlobal = q;
    }

    public Question getqGlobal(){
        return qGlobal;
    }
}
