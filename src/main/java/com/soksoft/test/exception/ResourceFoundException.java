package com.soksoft.test.exception;

public class ResourceFoundException extends RuntimeException{
    public ResourceFoundException(String message){
        super(message);
    }
    public ResourceFoundException(){
        super("Resourse already exist");
    }
}
