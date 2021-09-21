package com.quantcast.configuration;

import com.quantcast.strategy.CountingStrategy;
import com.quantcast.strategy.DataParser;

import java.nio.file.Path;
import java.time.LocalDate;

/**
 * Cookie File Configuration
 *
 */
public class CookieFileConfiguration {
    private final Path file;
    private final LocalDate date;
    private final CountingStrategy.Type countingEngine;
    private final boolean fileHasHeader;
    private final boolean failFast;

    public DataParser getDataParser() {
        return dataParser;
    }

    private final DataParser dataParser;

    public CookieFileConfiguration(
            Path file, LocalDate date,
            CountingStrategy.Type countingEngine,
            boolean fileHasHeader,
            boolean failFast, DataParser dataParser) {
        this.file = file;
        this.date = date;
        this.countingEngine = countingEngine;
        this.fileHasHeader = fileHasHeader;
        this.failFast = failFast;
        this.dataParser = dataParser;
    }





    public Path getFile() {
        return file;
    }

    public LocalDate getDate() {
        return date;
    }

    public CountingStrategy.Type getCountingEngine() {
        return countingEngine;
    }

    public boolean getFileHasHeader() {
        return fileHasHeader;
    }

    public boolean getFailFast() {
        return failFast;
    }

}
