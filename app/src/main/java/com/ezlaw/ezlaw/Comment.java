package com.ezlaw.ezlaw;

public class Comment {
    private String author;
    private String comment;
    private String authorInfo;

    public Comment(String author, String comment, String authorInfo){
        this.author = author;
        this.comment = comment;
        this.authorInfo = authorInfo;
    }

    public Comment(){}

    public String getAuthor(){
        return author;
    }

    public String getComment(){
        return comment;
    }

    public String getAuthorInfo() {
        return authorInfo;
    }
}
