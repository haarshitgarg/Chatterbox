package org.harshit.authenticator.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.harshit.authenticator.Utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    Connection conn_;
    String url_ = "jdbc:mysql://localhost:3306/chat_app_user_data";
    String username_ = "root";
    String password_ = "Hgarg9460@3";

    AuthController() {
        try {
            conn_ = DriverManager.getConnection(url_, username_, password_);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private Boolean ConnectToDB() {
        Boolean conn_status = false;
        try {
            conn_status = this.conn_.isValid(0);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }

        if(!conn_status) {
            try {
                conn_ = DriverManager.getConnection(url_, username_, password_);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
                return false;
            }
        }

        return true;
    }

    @GetMapping("/auth-html")
    public String authenticate(Model model) {
        return "auth";
    }

    /**
     * @param request
     * @return
     */
    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> authRequest(@RequestBody AuthRequest request) {
        Boolean conn_status = ConnectToDB();
        if (!conn_status) {
            System.out.println("Could not connect to the database");
            return new ResponseEntity<>(new AuthResponse("Unable to process request right now"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            Statement stmt = conn_.createStatement();
            String query = "SELECT * FROM users WHERE username = '" + request.username + "';";
            ResultSet res_set = stmt.executeQuery(query);

            System.out.println("Executed the query");

            if(!res_set.next()) {
                System.out.println("User doesn't exist");
                return new ResponseEntity<>(new AuthResponse("User does not exist"), HttpStatus.BAD_REQUEST);
            }

            int id = res_set.getInt("id");
            String password = res_set.getString("password");

            System.out.println("id: "+id+" username: "+request.username+" password: "+password);

            if(!password.equals(request.password)) {
                System.out.println("Password is incorrect");
                return new ResponseEntity<>(new AuthResponse("Invalid Credentials"), HttpStatus.UNAUTHORIZED);
            }

            String jwt = JwtUtil.instance.GenerateToken(request.username);
            return new ResponseEntity<>(new AuthResponse("Authenticated", jwt), HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return new ResponseEntity<>(new AuthResponse("Unable to process request right now"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
}

class AuthRequest {
    String username;
    String password;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    
}

class AuthResponse {
    String status;
    String jwt_token;

    public String getJwt_token() {
        return jwt_token;
    }

    public void setJwt_token(String jwt_token) {
        this.jwt_token = jwt_token;
    }

    AuthResponse(String status) {
        this.status = status;
    }

    AuthResponse(String status, String jwt) {
        this.status = status;
        this.jwt_token = jwt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
