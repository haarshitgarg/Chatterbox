package org.harshit.authenticator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AuthenticatorApplicationTests {
    @Autowired
    WebTestClient client_;

    @LocalServerPort
    int port_;

    @BeforeEach
    void test_information() {
        System.out.println("#############################################");
        System.out.println("Server running at port: "+port_);
        System.out.println("#############################################");
    }

	@Test
	void test_auth_post_endpoint() {
        // Testing auth post endpoint
        String post_request = "{\"username\":\"Harshit\", \"password\":\"hgarg9460@3\"}";
        client_.post()
            .uri("/auth")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(post_request)
            .exchange()
            .expectStatus().isOk()
            .expectBody();

	}

    @Test
    void test_aut_post_endpoint_2() {
        String post_request = "{\"username\":\"harshit\", \"password\":\"hgarg9460@3\"}";
        client_.post()
            .uri("/auth")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(post_request)
            .exchange()
            .expectStatus().isUnauthorized()
            .expectBody();
    }
    
    @Test
    void test_aut_post_endpoint_3() {
        String post_request = "{\"username\":\"Harshit\", \"password\":\"Hgarg9460@3\"}";
        client_.post()
            .uri("/auth")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(post_request)
            .exchange()
            .expectStatus().isUnauthorized()
            .expectBody();
    }

}

