package com.quantcast.strategy;

import com.quantcast.configuration.CookieFileConfiguration;

import java.util.List;

public class SimpleStrategy implements CountingStrategy{
    @Override
    public List<String> apply(CookieFileConfiguration cookieFileConfiguration) {
        return new CountingSimply(cookieFileConfiguration).apply();
    }
}
