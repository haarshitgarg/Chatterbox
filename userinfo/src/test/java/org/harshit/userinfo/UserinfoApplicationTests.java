package org.harshit.userinfo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserinfoApplicationTests {
    @Autowired
    WebTestClient client_;

	@Test
	void chat_endpoint_get_test_1() {
        client_.get()
            .uri("/chat?userid=Harshits")
            .exchange()
            .expectStatus().isOk();
	}

	@Test
	void chat_endpoint_get_test_2() {
        client_.get()
            .uri("/chat?userid=Harshit")
            .exchange()
            .expectStatus().isOk();
	}

    @Test
	void chat_endpoint_get_test_3() {
        client_.get()
            .uri("/chat?userid=Tanvi")
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class)
            .value(response -> {
                System.out.println("Response: "+response);
            });
	}

}
