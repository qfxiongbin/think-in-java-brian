package com.brian.auth.domain;

/**
 * AuthToken
 *
 * @author BrianX
 * @date 2022/09/01 14:23
 **/
public class AuthToken {
    private String token;
    private long timestamp;


    public AuthToken(){};

    public AuthToken(String token,long timestamp){
        this.token = token;
        this.timestamp = timestamp;
    }

    public boolean isExpired(){
        return false;
    }

    public static AuthToken generate(String originalUrl,String appId,String password,long timestamp){
        return new AuthToken();
    }

    public boolean match(AuthToken authToken){
        return false;
    }
}
