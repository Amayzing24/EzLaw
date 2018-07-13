package com.ezlaw.ezlaw;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class QuestionInfo extends AppCompatActivity {
    private static final String TAG = "QuestionInfo";
    ArrayList<Comment> comments;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference questionRef;

    TextView topicTitle;
    TextView question;
    TextView additionalInfoText;
    TextView authorName;
    FloatingActionButton addNewComment;
    String id;

    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_info);

        final Bundle bundle = getIntent().getBundleExtra("bundle");
        key = getIntent().getStringExtra("Key");
        System.out.println("Testing" + bundle.get("Question"));

        comments = new ArrayList<>();
        topicTitle = findViewById(R.id.topicTitle);
        question = findViewById(R.id.question1);
        additionalInfoText = findViewById(R.id.additionalInfo);
        authorName = findViewById(R.id.authorName);
        addNewComment = findViewById(R.id.newComment);

        if (key.equals("Lawyer")){
            addNewComment.setVisibility(View.VISIBLE);
        }
        else{
            addNewComment.setVisibility(View.GONE);
        }

        topicTitle.setText(bundle.getString("Topic"));
        authorName.setText("Posted by: " + bundle.getString("Author"));
        question.setText(bundle.getString("Question"));
        additionalInfoText.setText(bundle.getString("Additional"));
        id = bundle.getString("ID");

        questionRef = database.getReference("Questions").child(id);

        addNewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuestionInfo.this, NewComment.class);
                i.putExtra("ID", id);
                i.putExtra("CurrentUser", bundle.getString("CurrentUser"));
                i.putExtra("LawyerInfo", bundle.getString("LawyerInfo"));
                i.putExtra("bundle", bundle);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        questionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Question q = dataSnapshot.getValue(Question.class);
                comments.clear();
                if (q.getComments() != null){
                    comments.addAll(q.getComments());
                    System.out.println("Apples " + comments.size());
                }
                Collections.reverse(comments);
                initRecyclerView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "Cancelled");
            }
        });
    }

    RecyclerView recyclerView;
    CommentAdapter adapter;
    private void initRecyclerView(){
        recyclerView = findViewById(R.id.comment_recycler);
        adapter = new CommentAdapter(QuestionInfo.this, comments);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
