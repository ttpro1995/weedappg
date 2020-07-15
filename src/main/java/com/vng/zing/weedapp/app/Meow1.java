package com.vng.zing.weedapp.app;

import com.github.scribejava.core.builder.api.DefaultApi10a;
import org.pac4j.oauth.client.OAuth10Client;
import org.pac4j.oauth.config.OAuth10Configuration;
import org.pac4j.oauth.profile.bitbucket.BitbucketProfileDefinition;
import org.pac4j.scribe.builder.api.BitBucketApi;

public class Meow1 {

    public static void main(String[] args) {
        OAuth10Configuration config = new OAuth10Configuration();
        config.setKey("bjEt8BMpLwFDqZUvp6");
        config.setSecret("NN6fVXRTcV2qYVejVLZqxBRqHgn3ygD4");
        config.setApi(new BitBucketApi());
        config.setProfileDefinition(new BitbucketProfileDefinition());
        OAuth10Client client = new OAuth10Client();
        client.setCallbackUrl("PAC4J_BASE_URL");
        client.setConfiguration(config);

    }
}
