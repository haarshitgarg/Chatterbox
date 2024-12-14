package org.harshit.apigateway.Filters;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.lang.module.ModuleDescriptor.Exports;
import java.nio.file.Files;
import java.nio.file.Paths;
import reactor.core.publisher.Mono;

/**
 * AuthenticationFilter
 */
public class AuthenticationFilter implements GatewayFilter {

    /**
     * Custom filter to authenticate any incoming request to the gateway
     *
     * @param exchange The current web exchange (request and response) for this HTTP request. 
     * @param chain The filter chain that allows forwarding the request to the next filter.
     *
     * @return A Mono representing the asynchronous completion of the request filtering.
     */
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("[FILTER] Authentication filter invoked");
        HttpHeaders header = exchange.getRequest().getHeaders();
        System.out.println("[FILTER] Header: "+header);
        String jwt_token = header.getFirst(HttpHeaders.AUTHORIZATION);
        System.out.println("[FILTER] JWT TOKEN: "+jwt_token);
        if(!validate(jwt_token)) {
            return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Token"));
        }
        exchange.getRequest().mutate().header("auth_status", "verified");
        System.out.println("[FILTER] Successfull validation of JWT token");

        return chain.filter(exchange);
    }

    /**
     * validates the jwt Token
     *
     * @param token It is the jwt token that needs to be verified
     * @return Boolean value based on if the token is correct
     */
    private Boolean validate(String token) {
        // Logic to handle validation
        System.out.println("[FILTER] Validating...");
        String publicKeyPath = "apigateway/public_key.pem";
        try {
            RSAPublicKey public_key= (RSAPublicKey) loadPublicKey(publicKeyPath);
            Algorithm algorithm = Algorithm.RSA256(public_key, null);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("harshit").build();

            DecodedJWT jwt = verifier.verify(token);
            System.out.println("[FILTER] Decoded Token: "+jwt.getSubject());
            return true;
        }
        catch (Exception e){
            System.out.println("[FILTER] Exeption thrown: "+e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Loads the public key from the given path
     *
     * @param publicKeyPath Path to the public key
     *
     * @return Returns the public key
     */
    private static PublicKey loadPublicKey(String publicKeyPath) throws Exception {
        String publicKeyPEM = new String(Files.readAllBytes(Paths.get(publicKeyPath)))
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");
        
        byte[] decoded = Base64.getDecoder().decode(publicKeyPEM);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        
        return keyFactory.generatePublic(keySpec);
    }
   
}
