package org.harshit.apigateway.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GatewayController {

    @GetMapping("/fallback")
	public ResponseEntity<String> getMethodName() {
        System.out.println("Calling fallback page");

        return new ResponseEntity<>("Called the fallback page", HttpStatus.NOT_FOUND);
	}

    @GetMapping("/")
    public ResponseEntity<String> getGatewayRootAPI() {
        System.out.println("Calling root page");
        return new ResponseEntity<>("Called the root page", HttpStatus.OK);
    }
    
}

class GatewayResponse {
    GatewayResponse() {
    }
};

class GatewayRequest {

};
