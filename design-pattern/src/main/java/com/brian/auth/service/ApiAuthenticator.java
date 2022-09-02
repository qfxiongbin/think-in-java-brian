package com.brian.auth.service;

import com.brian.auth.domain.ApiRequest;

public interface ApiAuthenticator {
    void auth(String url);
    void auth(ApiRequest apiResult);
}
