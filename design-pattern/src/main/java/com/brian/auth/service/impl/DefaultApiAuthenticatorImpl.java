package com.brian.auth.service.impl;

import com.brian.auth.domain.ApiRequest;
import com.brian.auth.domain.AuthToken;
import com.brian.auth.service.ApiAuthenticator;
import com.brian.auth.service.CredentialStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Api authenticatior impl
 *
 * @author BrianX
 * @date 2022/09/01 11:54
 **/

public class DefaultApiAuthenticatorImpl implements ApiAuthenticator {

    private CredentialStorage certificateStorage;

    public DefaultApiAuthenticatorImpl(){
        this.certificateStorage = new MyCredentialStorage();
    }

    public DefaultApiAuthenticatorImpl(CredentialStorage certificateStorage){
        this.certificateStorage = certificateStorage;
    }

    @Override
    public void auth(String url) {
        ApiRequest apiRequest = ApiRequest.buildFromUrl(url);
        auth(apiRequest);
    }

    @Override
    public void auth(ApiRequest apiResult) {
        String appId = apiResult.getAppId();
        String token = apiResult.getToken();
        long timestamp = apiResult.getTimestamp();
        String originalUrl = apiResult.getOriginalUrl();

        AuthToken clientAuthToken = new AuthToken(token,timestamp);
        if(clientAuthToken.isExpired()){
            throw new RuntimeException("Token is expired.");
        }

        String password = certificateStorage.getPasswordByAppId(appId);
        AuthToken serverAuthToken = AuthToken.generate(originalUrl,appId,password,timestamp);
        if(!serverAuthToken.match(clientAuthToken)){
            throw new RuntimeException("Token verfication failed.");
        }

    }
}
