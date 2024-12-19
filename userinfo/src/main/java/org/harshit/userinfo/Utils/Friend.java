package org.harshit.userinfo.Utils;

/**
 * Friend
 */
public class Friend {

    private String name;
    private String lastChat;

    public Friend() {}

    public Friend(String name, String lastChat) {
        this.name = name;
        this.lastChat = lastChat;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getLastChat() {
        return this.lastChat;
    }

    public void setLastChat(String lastChat) {
        this.lastChat = lastChat;
    }

    public String toString() {
        return "Name: "+name+" Last chat at: "+lastChat;
    }
}

