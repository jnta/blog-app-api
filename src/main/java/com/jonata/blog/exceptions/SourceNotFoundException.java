package com.jonata.blog.exceptions;

public class SourceNotFoundException extends RuntimeException{
    public SourceNotFoundException(String message) {
        super(message);
    }
}
