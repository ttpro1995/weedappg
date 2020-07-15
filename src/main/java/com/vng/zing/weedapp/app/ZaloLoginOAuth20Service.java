package com.vng.zing.weedapp.app;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.oauth.AccessTokenRequestParams;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.core.pkce.PKCE;

import java.io.OutputStream;

public class ZaloLoginOAuth20Service extends OAuth20Service {
    private final ZaloLoginApi api;

    public ZaloLoginOAuth20Service(ZaloLoginApi api, String apiKey, String apiSecret, String callback, String defaultScope, String responseType, OutputStream debugStream, String userAgent, HttpClientConfig httpClientConfig, HttpClient httpClient) {
        super(api, apiKey, apiSecret, callback, defaultScope, responseType, debugStream, userAgent, httpClientConfig, httpClient);
        this.api = api;
    }



    //createAccessTokenRequest

    /**
     * https://oauth.zaloapp.com/v3/access_token?app_id={1}&app_secret={2}&code={3}
     * require: app_id, app_secret, code
     * @param params
     * @return
     */
    @Override
    protected OAuthRequest createAccessTokenRequest(AccessTokenRequestParams params) {
        final OAuthRequest request = new OAuthRequest(api.getAccessTokenVerb(), api.getAccessTokenEndpoint());

        api.getClientAuthentication().addClientAuthentication(request, getApiKey(), getApiSecret());
        request.addParameter("app_id", this.getApiKey());
        request.addParameter("app_secret", this.getApiSecret());
        request.addParameter(OAuthConstants.CODE, params.getCode());

        if (isDebug()) {
            log("created access token request with body params [%s], query string params [%s]",
                    request.getBodyParams().asFormUrlEncodedString(),
                    request.getQueryStringParams().asFormUrlEncodedString());
        }
        return request;
    }
}
