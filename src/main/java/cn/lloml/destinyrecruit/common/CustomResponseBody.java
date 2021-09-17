package cn.lloml.destinyrecruit.common;


import java.util.HashMap;

public class CustomResponseBody<T> extends HashMap<String,Object> {
    public  CustomResponseBody(int status, String message, String error, T data) {
        this.put("status",status);
        this.put("message",message);
        this.put("error",error);
        this.put("data",data);
    }

    public  CustomResponseBody(int status, String message, String error) {
        this.put("status",status);
        this.put("message",message);
        this.put("error",error);
        this.put("data",null);
    }
}
