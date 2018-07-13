package com.ezlaw.ezlaw;

import java.util.ArrayList;

public class Question {
    private String author;
    private String question;
    private String topic;
    private ArrayList<Comment> comments;
    private String additionalInfo;
    private String id;

    public Question(String author, String topic, String question, String additionalInfo){
        this.author = author;
        this.topic = topic;
        this.question = question;
        this.additionalInfo = additionalInfo;
        this.comments = new ArrayList<>();
        this.id = null;
    }



    public Question(){}

    public String getAuthor(){
        return author;
    }

    public String getQuestion(){
        return question;
    }

    public String getTopic(){
        return topic;
    }

    public String getAdditionalInfo(){
        return additionalInfo;
    }

    public String getId (){
        return id;
    };

    public void setId(String s){
        id = s;
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

    public ArrayList<Comment> getComments(){
        return comments;
    }

    public int getCommentCount(){
        if (comments != null){
            return comments.size();
        }
        else{
            return 0;
        }
    }

    public void setComments(ArrayList<Comment> newComments){
        comments = newComments;
    }
}
