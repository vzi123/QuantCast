package com.quantcast.strategy;


import com.quantcast.configuration.CookieFileConfiguration;

import java.util.List;

public class CountingStrategyFactory {

    public static CountingStrategy getStrategy(CountingStrategy.Type type) {
        switch (type) {
            case SIMPLE:
            default:
                return new SimpleStrategy();
        }
    }

   /* protected static CountingStrategy simpleStategy(DataParser parser) {
        return new CountingStrategy() {

            @Override
            public List<String> apply(CookieFileConfiguration cookieFileConfiguration) {
                   return new CountingSimply(cookieFileConfiguration).apply();
            }
        };
    }*/

}
