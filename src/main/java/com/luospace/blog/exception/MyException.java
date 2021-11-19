package com.luospace.blog.exception;

public class MyException extends RuntimeException {

    final private int code;

    final private String message;

    public MyException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
