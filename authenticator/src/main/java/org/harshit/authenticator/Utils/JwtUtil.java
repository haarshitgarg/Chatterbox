package org.harshit.authenticator.Utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {

    private JwtUtil() {
    }

    public static JwtUtil instance = new JwtUtil();

    private final static String secret_key_ = "my_secret_is_my_secret";
    private Integer expiration_time_ = 1000*60;

    public static PrivateKey loadPrivateKey(String privateKeyPath) throws Exception {
        String privateKeyPEM = new String(Files.readAllBytes(Paths.get(privateKeyPath)))
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");
        
        byte[] decoded = Base64.getDecoder().decode(privateKeyPEM);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        
        return keyFactory.generatePrivate(keySpec);
    }

    public static PublicKey loadPublicKey(String publicKeyPath) throws Exception {
        String publicKeyPEM = new String(Files.readAllBytes(Paths.get(publicKeyPath)))
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");
        
        byte[] decoded = Base64.getDecoder().decode(publicKeyPEM);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        
        return keyFactory.generatePublic(keySpec);
    }

    // Way to create a token
    /**
     * @param username
     * @return
     * @throws Exception
     */
    public String GenerateToken(String username) throws Exception {
        // Get RSA private key and public key
        System.out.println("Generating the token");
        RSAPrivateKey private_key = (RSAPrivateKey) loadPrivateKey("authenticator/private_key.pem");
        RSAPublicKey public_key = (RSAPublicKey) loadPublicKey("authenticator/public_key.pem");

        Algorithm algorithm = Algorithm.RSA256(public_key, private_key);

        String token = JWT.create()
            .withSubject(username)
            .withIssuer("harshit")
            .sign(algorithm); 

        System.out.println("Generated the token");
        return token;
    }

    public Boolean VerifyToken(String token) {
        try {
            RSAPrivateKey private_key = (RSAPrivateKey) loadPrivateKey("authenticator/private_key.pem");
            RSAPublicKey pubic_key = (RSAPublicKey) loadPublicKey("authenticator/public_key.pem");

            Algorithm algorithm = Algorithm.RSA256(pubic_key, private_key);

            JWTVerifier verifier = JWT.require(algorithm).withIssuer("harshit").build();

            DecodedJWT jwt = verifier.verify(token);
            jwt.getSubject();
            return true;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }
    }

}
