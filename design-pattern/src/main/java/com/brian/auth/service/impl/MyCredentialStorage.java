package com.brian.auth.service.impl;

import com.brian.auth.service.CredentialStorage;
import org.springframework.stereotype.Service;

/**
 * CredentialStorage impl
 *
 * @author BrianX
 * @date 2022/09/01 13:37
 **/
@Service
public class MyCredentialStorage implements CredentialStorage {
    @Override
    public String getPasswordByAppId(String appId) {
        return null;
    }
}
