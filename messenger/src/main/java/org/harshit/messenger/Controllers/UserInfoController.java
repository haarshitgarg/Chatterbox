package org.harshit.messenger.Controllers;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.harshit.messenger.utils.UserMessage;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserInfoController {
    public static UserInfoController instance = new UserInfoController();
    private UserInfoController() {}

    public Boolean storeMessage(UserMessage message) {
        System.out.println("[MESSENGER CONTROLLER] Storing message");
        String urlString = "http://localhost:8090/postMessage";
        try {
            URI uri = new URI(urlString);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set Request Method to post and configure the connection
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            // Convert the message to json string
            ObjectMapper mapper = new ObjectMapper();
            String json_msg = mapper.writeValueAsString(message);

            // Set the output stream and send the message
            OutputStream os = connection.getOutputStream();
            byte[] bytes = json_msg.getBytes();
            os.write(bytes);

            connection.getResponseMessage();

            return true;
        }
        catch (Exception e) {
            System.out.println("[MESSENGER CONTROLLER] Found an error");
            e.printStackTrace();
            return false;
        }
    }
};
