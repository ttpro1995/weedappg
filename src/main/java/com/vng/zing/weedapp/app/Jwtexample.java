package com.vng.zing.weedapp.app;


import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import org.pac4j.core.credentials.TokenCredentials;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.jwt.config.encryption.ECEncryptionConfiguration;
import org.pac4j.jwt.config.encryption.SecretEncryptionConfiguration;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.pac4j.jwt.profile.JwtGenerator;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Jwtexample {

    public static String KEY2 = "meowkey2meowkey2meowkey2meowkey2meowkey2meowkey2meowkey2";
    public static String SECRET = "asdasdasdasdasda";

    public static void main(String[] args) throws NoSuchAlgorithmException {
        CommonProfile inProfile = new CommonProfile();
        inProfile.setId("111111");
        inProfile.setPermissions(new HashSet<>(Arrays.asList("p1", "p2", "p3")));
        JwtGenerator<CommonProfile> generator = new JwtGenerator<>(new SecretSignatureConfiguration(KEY2),
                new SecretEncryptionConfiguration(SECRET, JWEAlgorithm.A128GCMKW, EncryptionMethod.A128GCM));
        JwtGenerator<CommonProfile> generator2 = new JwtGenerator<>(new SecretSignatureConfiguration(KEY2));

        System.out.println(generator);
        String token = generator2.generate(inProfile);
        System.out.println("token " + token);
        JwtAuthenticator jwtAuthenticator = new JwtAuthenticator();

        jwtAuthenticator.addSignatureConfiguration(new SecretSignatureConfiguration(KEY2));


        jwtAuthenticator.addEncryptionConfiguration(new SecretEncryptionConfiguration(SECRET, JWEAlgorithm.A128GCMKW, EncryptionMethod.A128GCM));

        CommonProfile out = jwtAuthenticator.validateToken(token);
        System.out.println("out profile "  + out);
    }
}
