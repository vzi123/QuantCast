package com.quantcast.service;

import com.quantcast.configuration.CookieFileConfiguration;
import com.quantcast.strategy.CountingStrategy;

import java.util.List;

public interface ICookieService {

     List<String> findMostActiveCookies(CountingStrategy countingStrategy, CookieFileConfiguration cookieFileConfiguration);
}
