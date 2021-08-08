package com.springboot.app.springboot.people.app.exceptions;

public class ExistingUserException extends Exception{

    public ExistingUserException(String message){
        super(message);
    }
}
