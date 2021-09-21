package com.quantcast.strategy;

import com.quantcast.configuration.CookieFileConfiguration;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CountingStrategy {

    enum Type {
        SIMPLE,
    }

    List<String> apply(CookieFileConfiguration cookieFileConfiguration);
}
