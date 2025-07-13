package com.empresa.employee_manager.Model;


public class BaseResponse<T> {

    private boolean isSuccess;
    
    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    private T data;
    private String message;

    public BaseResponse() {}

    public BaseResponse(boolean isSuccess, T data, String message) {
        this.isSuccess = isSuccess;
        this.data = data;
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
}