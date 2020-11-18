package com.iotdreamclub.demo.entity;

public class JsonResult<T> {
    private String state;
    private Integer resultCode;
    private T data;

    public JsonResult(String state, int resultCode, T data) {
        this.state = state;
        this.resultCode = resultCode;
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
