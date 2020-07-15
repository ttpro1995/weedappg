package com.vng.zing.weedapp.app;

import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.ParameterList;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

import java.io.OutputStream;
import java.util.Map;

public class ZaloLoginApi  extends DefaultApi20 {
    private String authorizationUrl = "https://oauth.zaloapp.com/v3/permission";
    private String accessTokenUrl = "https://oauth.zaloapp.com/v3/access_token";
    @Override
    public String getAccessTokenEndpoint() {
        return accessTokenUrl;
    }

    @Override
    public Verb getAccessTokenVerb() {
        return Verb.GET;
    }



    @Override
    protected String getAuthorizationBaseUrl() {
        return authorizationUrl;
    }

    @Override
    public String getAuthorizationUrl(String responseType, String apiKey, String callback, String scope, String state, Map<String, String> additionalParams) {
        final ParameterList parameters = new ParameterList(additionalParams);

        // URL sample: https://oauth.zaloapp.com/v3/permission?app_id={1}&redirect_uri={2}&state={3}

        //parameters.add(OAuthConstants.RESPONSE_TYPE, responseType);
        parameters.add("app_id", apiKey);


        if (callback != null) {
            parameters.add(OAuthConstants.REDIRECT_URI, callback);
        }

        if (scope != null) {
            parameters.add(OAuthConstants.SCOPE, scope);
        }

        if (state != null) {
            parameters.add(OAuthConstants.STATE, state);
        }

        return parameters.appendTo(getAuthorizationBaseUrl());
    }

    private static class InstanceHolder {
        private static final ZaloLoginApi INSTANCE = new ZaloLoginApi();
    }

    public static ZaloLoginApi instance() {
        return ZaloLoginApi.InstanceHolder.INSTANCE;
    }

    @Override
    public ZaloLoginOAuth20Service createService(String apiKey, String apiSecret, String callback, String defaultScope,
                                        String responseType, OutputStream debugStream, String userAgent, HttpClientConfig httpClientConfig,
                                        HttpClient httpClient) {
        return new ZaloLoginOAuth20Service(this, apiKey, apiSecret, callback, defaultScope, responseType, debugStream, userAgent,
                httpClientConfig, httpClient);
    }
}
