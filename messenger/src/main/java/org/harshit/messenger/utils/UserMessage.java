package org.harshit.messenger.utils;

public class UserMessage {
    private String msg_from;
    private String msg_to;
    private String msg;
    private long time;
    private Boolean bReceived;

    public UserMessage(String from, String to, String msg, long time, Boolean status) {
        this.msg_from = from;
        this.msg_to = to;
        this.msg = msg;
        this.time = time;
        this.bReceived = status;
    }

    public String getMsg_from() {
        return msg_from;
    }

    public void setMsg_from(String from) {
        this.msg_from = from;
    }

    public String getMsg_to() {
        return this.msg_to;
    }
    
    public void setMsg_to(String to) {
        this.msg_to = to;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time){
        this.time = time;
    }

    public Boolean getBReceived() {
        return this.bReceived;
    }

    public void setBReceived(Boolean status){
        this.bReceived = status;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

};
