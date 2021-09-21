package com.quantcast.service;

import com.quantcast.configuration.CookieFileConfiguration;
import com.quantcast.strategy.CountingStrategy;

import java.util.*;


public class CookieService implements ICookieService {

    @Override
    public List<String>  findMostActiveCookies(CountingStrategy countingStrategy, CookieFileConfiguration cookieFileConfiguration) {
        return countingStrategy.apply(cookieFileConfiguration);
    }
}
