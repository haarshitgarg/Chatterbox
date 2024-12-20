package org.harshit.userinfo.Controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.cj.jdbc.DatabaseMetaData;

import org.bouncycastle.util.test.FixedSecureRandom.Data;
import org.harshit.userinfo.Utils.Friend;
import org.harshit.userinfo.Utils.Messages;

@Controller
public class ApplicationController {

    @GetMapping("/chat")
    public ResponseEntity<GetResponse> getContactList(@RequestParam String userid) {
        System.out.println("[CONTROLLER] Called Get Contact List api with userid: "+userid);
        DatabaseController db_controller = DatabaseController.instance;
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

    @PostMapping("/postMessage")
    public ResponseEntity<Boolean> postMessages(@RequestBody Messages message) {
        DatabaseController controller = DatabaseController.instance;
        if(controller.addMessageToDB(message)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(false, HttpStatus.SERVICE_UNAVAILABLE);
        }

    }

    /**
     * Sync Endpoint: To synchronize the application with the latest Messages
     *
     * @param username Name of the user requesting to sync
     * @param lastSync Last time the application was synced. Newest message that the user have
     *
     * @return Returns the Response entity with a list of all the messages.
     */
    @GetMapping("/syncChat")
    public ResponseEntity<List<Messages>> syncChat(@RequestParam String username, @RequestParam long lastSync) {
        System.out.println("[CONTROLLER] Synching chat...");
        DatabaseController controller = DatabaseController.instance;
        try {
            System.out.println("[CONTROLLER] Finding the list of messages");
            List<Messages> new_messages = controller.getMessages(username, lastSync);

            return new ResponseEntity<>(new_messages, HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
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


