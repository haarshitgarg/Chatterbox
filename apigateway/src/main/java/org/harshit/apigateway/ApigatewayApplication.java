package org.harshit.apigateway;

import org.harshit.apigateway.Filters.AuthenticationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * Api gateway application acts as a single point of entry to various services
 */
@SpringBootApplication
public class ApigatewayApplication {

    /**
     * Main function to run the application
     */
	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}

    /**
     * Handles the all the routes
     *
     * "/home -> /home"
     * "/about -> /about"
     * "/auth -> authenticator enpoint localhost:8083/auth"
     * "/chat -> userinfo endpoint localhost:8090/chat
     *
     * @param builder To define and build routes 
     *
     * @return A collection of routes
     */
    @Bean
    RouteLocator getRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
			.route(p -> p
				.path("/home")
				.filters(f -> f
					.circuitBreaker(config -> config
					.setName("Circuit breaker")
					.setFallbackUri("forward:/fallback"))
				)
				.uri("http://localhost:8082/home")
			)
			.route(p->p
				.path("/about")
				.filters(f -> f
					.circuitBreaker(config -> config
					.setName("break")
					.setFallbackUri("forward:/fallback")))
				.uri("http://localhost:8082/about")
			)
			.route(p->p
				.path("/auth")
				.filters(f->f
					.circuitBreaker(config -> config
					.setName("break")
					.setFallbackUri("forward:/fallback")))
				.uri("http://localhost:8083/auth")
			)
			.route(p->p
				.path("/chat**")
				.filters(f -> f
					.filter(new AuthenticationFilter())
					.circuitBreaker(config -> config
					.setName("break")
					.setFallbackUri("forward:/fallback")))
				.uri("http://localhost:8090/chat")
			)
			.build();
	}

}
