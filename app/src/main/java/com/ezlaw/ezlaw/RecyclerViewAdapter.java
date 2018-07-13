package com.ezlaw.ezlaw;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Question> questions = new ArrayList<>();
    private String currentUser;
    private String currentLawyerInfo;
    private String currentUserKey;
    Context mContext;

    public RecyclerViewAdapter(ArrayList<Question> questions, Context mContext) {
        this.questions = questions;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    Question q;
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder called");

        q = questions.get(position);
        ArrayList<Comment> qComments = q.getComments();

        holder.setTopic(q.getTopic());
        holder.setAuthor(q.getAuthor());
        holder.setQuestion(q.getQuestion());
        holder.setId(q.getId());
        if (qComments == null){
            holder.setViewAnswersToZero();
        }
        else{
            holder.setViewAnswers(q.getCommentCount() + " Answers");
        }
        holder.setAdditionalInfo(q.getAdditionalInfo());

        holder.viewAnswers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, QuestionInfo.class);
                Bundle bundle = new Bundle();
                bundle.putString("Author", holder.getAuthor().getText().toString());
                bundle.putString("Topic", holder.getTopic().getText().toString());
                bundle.putString("Question", holder.getQuestion().getText().toString());
                bundle.putString("Additional", holder.getAdditionalInfo());
                bundle.putString("ID", holder.getId());
                bundle.putString("LawyerInfo", currentLawyerInfo);
                bundle.putString("CurrentUser", currentUser);
                i.putExtra("Key", currentUserKey);
                i.putExtra("bundle", bundle);
                mContext.startActivity(i);
            }
        });
    }

    public void updateQuestions(ArrayList<Question> newList) {
        questions.clear();
        questions.addAll(newList);
        this.notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView topic;
        private TextView author;
        private TextView question;
        private Button viewAnswers;
        private String additionalInfo;
        public String id;
        private View divider;

        public ViewHolder(View itemView) {
            super(itemView);

            topic = itemView.findViewById(R.id.textView5);
            author = itemView.findViewById(R.id.textView7);
            question = itemView.findViewById(R.id.textView6);
            viewAnswers = itemView.findViewById(R.id.button2);
            divider = itemView.findViewById(R.id.divider2);
        }

        public TextView getTopic(){
            return topic;
        }

        public TextView getQuestion(){
            return question;
        }

        public TextView getAuthor(){
            return author;
        }

        public Button getViewAnswers(){
            return viewAnswers;
        }

        public void setTopic(String s){
            topic.setText(s);
        }

        public void setAuthor(String s){
            author.setText(s);
        }

        public void setQuestion(String s){
            question.setText(s);
        }

        public void setViewAnswersToZero(){
            viewAnswers.setText("0 Answers");
        }

        public void setViewAnswers(String s){
            viewAnswers.setText(s);
        }

        public void setAdditionalInfo(String s){
            additionalInfo = s;
        }

        public String getAdditionalInfo(){
            return additionalInfo;
        }


        public void setId(String s){
            id = s;
        }

        public String getId(){
            return id;
        }
    }

    public void setCurrentLawyerInfo(String s){
        this.currentLawyerInfo = s;
    }

    public String getCurrentLawyerInfo(){
        return this.currentLawyerInfo;
    }

    public void setCurrentUser(String s) {
        this.currentUser = s;
    }

    public void setCurrentUserKey(String s){
        this.currentUserKey = s;
    }

    public String getCurrentUserKey() {
        return currentUserKey;
    }
}
