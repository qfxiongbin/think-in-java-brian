package com.brian.auth.service;

public interface CredentialStorage {
    /**
     * getPassword by appId
    * @param appId:
    *
    * @return String
    * @author BrianX
    * @description TODO
    * @date 2022/9/1 13:37
    */
    public String getPasswordByAppId(String appId);
}
