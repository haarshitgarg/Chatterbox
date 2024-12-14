package org.harshit.userinfo.Controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.harshit.userinfo.Utils.Friend;

@Controller
public class ApplicationController {

    @GetMapping("/chat")
    public ResponseEntity<GetResponse> getContactList(@RequestParam String userid) {
        System.out.println("[CONTROLLER] Called Get Contact List api with userid: "+userid);
        DatabaseController db_controller = new DatabaseController();
        try {
            List<Friend> records = db_controller.getFriendsList(userid);
            GetResponse response = new GetResponse();
            response.setContact_list_(records);
            System.out.println(records.toString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println("[CONTROLLER] Failed to get the contact list");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

class GetResponse {
    private List<Friend> contact_list_;

    public GetResponse() {
        contact_list_ = new ArrayList<>();
    }

    public void addContact(Friend item) {
        contact_list_.add(item);
    }

    public List<Friend> getContact_list_() {
        return contact_list_;
    }

    public void setContact_list_(List<Friend> contact_list_) {
        this.contact_list_ = contact_list_;
    }

}


