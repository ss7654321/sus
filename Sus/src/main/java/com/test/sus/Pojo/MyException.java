package com.test.sus.Pojo;

public class MyException extends RuntimeException {
    String message;
    public MyException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
