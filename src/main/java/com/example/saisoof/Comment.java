package com.example.saisoof;

import java.sql.Timestamp;

class Comment {
    private String content;
    private Timestamp timestamp;
    private UserProfile author;

    public Comment(String content, UserProfile author, Timestamp date) {
        this.author = author;
        this.content = content;
        this.timestamp = date;
    }

    public UserProfile getAuthor(){
        return this.author;
    }

    public String getContent() {
        return this.content;
    }

    public void editContent(String content){
        this.content = content;
    }

    public Timestamp getTimeStamp(){
        return this.timestamp;
    }
}