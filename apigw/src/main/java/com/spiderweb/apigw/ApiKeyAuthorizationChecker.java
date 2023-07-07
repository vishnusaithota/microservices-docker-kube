package com.spiderweb.apigw;

public interface ApiKeyAuthorizationChecker {
    boolean isAuthorized(String apiKey, String application);
}
