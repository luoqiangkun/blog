package com.luospace.blog.common;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer status;

    private String message;

    private T data;

    public Result() {
    }

    public Result(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public static <T> Result<T> success(T data){
        return new Result<T>(ResuleCode.SUCCESS.getCode(),ResuleCode.SUCCESS.getMessage(),data);
    }
    public static <T> Result<T> success(T data,String message){
        return new Result<T>(ResuleCode.SUCCESS.getCode(), message,data);
    }
    public static <T> Result<T> failed(){
        return new Result<T>(ResuleCode.FAILED.getCode(),ResuleCode.FAILED.getMessage(),null);
    }
    public static <T> Result<T> failed(Integer status){
        return new Result<T>(status,ResuleCode.FAILED.getMessage(), null);
    }
    public static <T> Result<T> failed(String message){
        return new Result<T>(ResuleCode.FAILED.getCode(), message,null);
    }
    public static <T> Result<T> failed(Integer status,String message){
        return new Result<T>(status, message,null);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
