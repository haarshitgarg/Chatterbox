package org.harshit.messenger.utils;

public class MessengerResponse {
    int sessionId;

    public MessengerResponse(int id){
        this.sessionId = id;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void SetSessionId(int id) {
        this.sessionId = id;
    }
};
