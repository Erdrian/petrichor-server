package com.petrichor.sincerity.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Result<T> {
    private boolean ok;
    private int code;
    private long timeStamp =new Date().getTime() ;
    private String msg;
    private T result;
}
