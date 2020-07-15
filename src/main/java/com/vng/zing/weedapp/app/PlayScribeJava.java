package com.vng.zing.weedapp.app;

import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class PlayScribeJava {
    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        String documentUrl = "https://github.com/scribejava/scribejava/wiki/getting-started";
        String codeExampleUrl = "https://github.com/scribejava/scribejava/blob/master/scribejava-apis/src/test/java/com/github/scribejava/apis/examples/Google20Example.java";
        String callbackUrl = "https://bba7114dfb3751543da88ac673ee11d4.m.pipedream.net";
        // Step 1
        String appId = "1904393786336116373";
        String appSecret = "68SKG677uG18WNXxMJe2";
        final ZaloLoginOAuth20Service service = (ZaloLoginOAuth20Service) new ServiceBuilder(appId)
                .apiSecret(appSecret)
                .apiKey(appId)
                .callback(callbackUrl)
                .debug()
                .build(ZaloLoginApi.instance());

        // build url
        final String authorizationUrl = service.createAuthorizationUrlBuilder()
                .build();

        System.out.println("Got the Authorization URL! " + authorizationUrl);

        final Scanner in = new Scanner(System.in, "UTF-8");
        String code = in.nextLine(); // must get from callback
        OAuth2AccessToken accessToken = service.getAccessToken(code);
        System.out.println("(The raw response looks like this: " + accessToken.getRawResponse() + "')");

    }
}
