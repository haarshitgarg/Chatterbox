package org.harshit.apigateway;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApigatewayApplicationTests {
    // Web Test client autowired to the server running at a random port
    @Autowired
    private WebTestClient client_;

    @LocalServerPort
    private int port_;

    @BeforeEach
    void preparation_for_tests() {
        System.out.println("######################################");
        System.out.println("Server running at port: "+this.port_);
        System.out.println("######################################");
    }

    @Test
    void test_fallback_path() throws Exception {
        client_.get()
            .uri("/fallback")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isNotFound();
    }

    @Test
    void test_root_path() {
        client_.get()
            .uri("/")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk();
    }

    @Test
    void test_authenticator_get_api() {
        // Authenticatore expects a post request so we should get an error 405: Method not found
        client_.get()
            .uri("/auth")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isEqualTo(405);
    }

    @Test
    void test_authenticator_post_api() {
        String post_body = "{}";
        client_.post()
            .uri("/auth")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(post_body)
            .exchange()
            .expectStatus()
            .is4xxClientError();
    }

    @Test
    void test_authenticator_post_api_1() {
        String post_body = "{\"username\":\"Harshit\", \"password\":\"hgarg9460@3\"}";
        client_.post()
            .uri("/auth")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(post_body)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody()
            .jsonPath("$.jwt_token").value(token -> {
                System.out.println("Received JWT token: "+token);
            });
    }

    @Test
    void test_userinfo_post_api_1() {
        client_.post()
            .uri("/chat")
            .exchange()
            .expectStatus().is4xxClientError();
    }


    @Test
    void test_userinfo_get_api_1() {
        client_.get()
            .uri("/chat?userid=Harshit")
            .exchange()
            .expectStatus().isUnauthorized();
    }

    @Test
    void test_userinfo_get_api_2() {
        String jwt_token = "";
        String post_body = "{\"username\":\"Harshit\", \"password\":\"hgarg9460@3\"}";

        client_.post()
            .uri("/auth")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(post_body)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody()
            .jsonPath("$.jwt_token").value(token -> {
                System.out.println("Received JWT token: "+token.toString());
                client_.get()
                    .uri("/chat")
                    .header("Authorization", token.toString())
                    .exchange()
                    .expectStatus().isBadRequest();
            });

    }

}
