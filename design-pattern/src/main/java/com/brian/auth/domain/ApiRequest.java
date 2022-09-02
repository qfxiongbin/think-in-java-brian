package com.brian.auth.domain;

import com.brian.auth.service.CredentialStorage;
import lombok.Data;

/**
 * api request vo
 *
 * @author BrianX
 * @date 2022/09/01 11:52
 **/
@Data
public class ApiRequest {
    private String appId;
    private String token;
    private Long timestamp;
    private String originalUrl;

    public static ApiRequest buildFromUrl(String url){
        return new ApiRequest();
    }
}
