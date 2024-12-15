package org.harshit.apigateway.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlls the apigateway. It maps various endpoints;
 */
@Controller
public class GatewayController {

    /**
     * A fallback page. Redirected to it when something goes wrong
     *
     * @return ResponseEntity with the a 404 response
     */
    @GetMapping("/fallback")
	public ResponseEntity<String> getMethodName() {
        System.out.println("Calling fallback page");

        return new ResponseEntity<>("Called the fallback page", HttpStatus.NOT_FOUND);
	}

    /**
     * A root page. The first page that pops up when you goto apigateway
     *
     * @return ResponseEntity with a 200 response
     */
    @GetMapping("/")
    public ResponseEntity<String> getGatewayRootAPI() {
        System.out.println("Calling root page");
        return new ResponseEntity<>("Called the root page", HttpStatus.OK);
    }
    
}

