package com.ezlaw.ezlaw;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LawyerMain extends AppCompatActivity {

    private static final String TAG = "LawyerMain";

    DatabaseReference questionReference;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    Bundle bundle1;
    Bundle bundle2;

    ArrayList<Question> questionList;

    FloatingActionButton addQuestion;
    Button searchButton;
    TextView searchBar;
    Button button1;

    String currentLawyerInfo;
    String currentUser;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lawyer_main);

        questionList = new ArrayList<>();

        key = getIntent().getStringExtra("Key");

        bundle1 = getIntent().getBundleExtra("bundle1");
        currentUser = bundle1.getString("Name");

        questionReference = database.getReference("Questions");

        addQuestion = findViewById(R.id.newQButton);
        searchButton = findViewById(R.id.searchButton);
        searchBar = findViewById(R.id.searchBar);
        button1 = (Button) findViewById(R.id.button22);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(LawyerMain.this, Rights.class);
                startActivity(i);
            }
        });


        if (key.equals("Lawyer")){
            bundle2 = getIntent().getBundleExtra("bundle2");
            currentLawyerInfo = bundle2.getString("lawFirm");
            addQuestion.setVisibility(View.GONE);
        }
        else{
            addQuestion.setVisibility(View.VISIBLE);
        }

        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LawyerMain.this, NewQuestion.class);
                i.putExtra("bundle1", bundle1);
                i.putExtra("bundle2", bundle2);
                startActivity(i);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = searchBar.getText().toString();
                if (searchButton.getText().equals("Search")){
                    searchButton.setText("Clear");
                    doSearch(searchText);
                }
                else if (searchButton.getText().equals("Clear")){
                    searchButton.setText("Search");
                    searchBar.setText(null);
                    initRecyclerView(questionList);
                }
            }
        });

        /*

        Question q1 = new Question("Bobby Smith", "Voting Rights", "How do I vote if I'm out of my state?",
                "I am from Cupertino California, but currently am going to college in Texas.");
        q1.addComment(new Comment("Jill Brown", "There are a few options for you. There is something " +
                "called absentee voting. Check this link out: https://www.usa.gov/absentee-voting", "Scranton Law Firm"));
        q1.addComment(new Comment("Jason Lee", "If this is for the electoral college, then there are some cases where the system doesn't allow you to vote.", "Lee and Red"));

        Question q2 = new Question("Mike Jones", "Rights in Court", "I don't have the money for a lawyer. What are my options?",
                "I recently accused of theft, although I didn't do anything.");
        q2.addComment(new Comment("Sally Samson", "The state is required to provide you with a lawyer.", "Chicago Law"));
        q2.addComment(new Comment("Paul Michael", "Look for firms that are interested in Pro Bono work.", "New York Law Firm"));

        Question q3 = new Question("George Paul", "Housing Rights", "My landlord is evicting me of my property, how long do I legally have to leave?", "I live in Los Angeles.");
        q3.addComment(new Comment("Rohan Taluri", "7-30 days", "Baltimore Firm"));
        q3.addComment(new Comment("Kobe Paul", "Upto 30 days.", "Paul Pro Bono Firm"));
        q3.addComment(new Comment("James Kora", "Minimum of 7 days. Ask your landlord for extra time if needed.", "Austin Law"));
        q3.addComment(new Comment("James Kora", "Correction: If you need more than 30 days ask your landlord.", "Austin Law"));

        addTestQuestion(q1);
        addTestQuestion(q2);
        addTestQuestion(q3);
        */


    }

    public void addTestQuestion(Question q){
        String id = questionReference.push().getKey();
        q.setId(id);
        questionReference.child(id).setValue(q);
    }

    public void doSearch(String s){
        s = s.toLowerCase();
        ArrayList<Question> copiedQuestionList = questionList;
        ArrayList<Question> foundMatches = new ArrayList<>();
        for (int i = 0; i < copiedQuestionList.size(); i++){
            Question questionObj = copiedQuestionList.get(i);
            String currQ = questionObj.getQuestion();
            String currA = questionObj.getAuthor();
            String currT = questionObj.getTopic();
            if (currQ.toLowerCase().contains(s) || currA.toLowerCase().contains(s) ||
                    currT.toLowerCase().contains(s)){
                foundMatches.add(questionObj);
            }
            else{
                ArrayList<Comment> currCs = questionObj.getComments();
                if (currCs != null && questionObj.getCommentCount() > 0){
                    for (int j = 0; j < currCs.size(); j++){
                        Comment currC = currCs.get(j);
                        if (currC.getAuthor().toLowerCase().contains(s) ||
                                currC.getAuthorInfo().toLowerCase().contains(s) ||
                                currC.getComment().toLowerCase().contains(s)){
                            foundMatches.add(questionObj);
                            break;
                        }
                    }
                }
            }
        }

        Collections.reverse(foundMatches);
        initRecyclerView(foundMatches);
    }



    @Override
    protected void onStart() {
        super.onStart();

        /*
        questionReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Question newQuestion = dataSnapshot.getValue(Question.class);
                System.out.println("Apple" + newQuestion.getAuthor());
                questionList.add(newQuestion);
                initRecyclerView();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        */

        questionReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                questionList.clear();
                for (DataSnapshot qSnapshot : dataSnapshot.getChildren()){
                    Question newQ = qSnapshot.getValue(Question.class);
                    questionList.add(newQ);
                }
                Collections.reverse(questionList);
                if (searchButton.getText().equals("Search")){
                    initRecyclerView(questionList);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "Cancelled");
            }
        });
    }

    public void addQuestionToDatabase(Question q){
        String id = questionReference.push().getKey();
        q.setId(id);
        questionReference.child(id).setValue(q);
    }

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    private void initRecyclerView(ArrayList<Question> qList){
        recyclerView = findViewById(R.id.my_recycler_view);
        adapter = new RecyclerViewAdapter(qList, LawyerMain.this);
        adapter.setCurrentLawyerInfo(currentLawyerInfo);
        adapter.setCurrentUser(currentUser);
        adapter.setCurrentUserKey(key);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }




}


