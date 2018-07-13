package com.ezlaw.ezlaw;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private static final String TAG = "CommentAdapter";
    private ArrayList<Comment> comments = new ArrayList<>();
    Context mContext;

    public CommentAdapter(Context mContext, ArrayList<Comment> comments){
        this.mContext = mContext;
        this.comments = comments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder called" + position);

        Comment c = comments.get(position);

        holder.setComment(c.getComment());
        holder.setName(c.getAuthor());
        holder.setPersonInfo(c.getAuthorInfo());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView comment;
        private TextView personInfo;
        private View divider;

        public ViewHolder(View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.textView12);
            comment = itemView.findViewById(R.id.textView13);
            personInfo = itemView.findViewById(R.id.textView14);
            divider = itemView.findViewById(R.id.divider3);
        }

        public void setName(String s){
            name.setText(s);
        }

        public void setComment(String s){
            comment.setText(s);
        }

        public void setPersonInfo(String s){
            personInfo.setText(s);
        }

        public TextView getName(){
            return name;
        }

        public TextView getComment() {
            return comment;
        }

        public TextView getPersonInfo() {
            return personInfo;
        }
    }
}
