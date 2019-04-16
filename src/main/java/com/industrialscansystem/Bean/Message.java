package com.industrialscansystem.Bean;

/**
 * Message消息类，用于传递各个函数运算结果的消息
 */
public class Message {

    private boolean isSuccess;
    private String info;

    public Message(){
        this.isSuccess = false;
        this.info = "Nothing";
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
