package com.luospace.blog.common;

public enum ResuleCode {

    SUCCESS(200,"操作成功"),
    FORBIDDER(403,"无访问权限"),
    FAILED(500, "操作失败");

    private int code;

    private String message;

    ResuleCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
