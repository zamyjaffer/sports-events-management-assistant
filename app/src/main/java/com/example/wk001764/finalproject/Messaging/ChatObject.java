package com.example.wk001764.finalproject.Messaging;

/**
 * Created by wk001764 on 07/02/2018.
 */

public class ChatObject {
    private String message;
    private Boolean currentUser;

    public ChatObject(String message, Boolean currentUser){
        this.message = message;
        this.currentUser = currentUser;
    }

    public String getMessage(){
        return message;
    }
    public void setMessage(String userID){
        this.message = message;
    }

    public Boolean getCurrentUser(){
        return currentUser;
    }
    public void setCurrentUser(Boolean currentUser){
        this.currentUser = currentUser;
    }
}