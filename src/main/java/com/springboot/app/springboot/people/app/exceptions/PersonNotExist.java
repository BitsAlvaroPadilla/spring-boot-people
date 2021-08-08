package com.springboot.app.springboot.people.app.exceptions;

public class PersonNotExist extends Exception{

    public PersonNotExist(String dni){
        super(String.format("The person <%s> doesn't exist", dni));
    }
}
