package com.quantcast.strategy;

import com.quantcast.configuration.CookieFileConfiguration;
import com.quantcast.framework.IterableRich;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Loader {

    // Rewrite it's usage to be configurable
    public static final int DEFAULT_BUFFER = 1000000;

    public static IterableRich<CSVRecord> lazy(File file, CSVFormat format) throws IOException {
        CSVParser parser =
                CSVParser.parse(file, StandardCharsets.UTF_8, format);

        return new IterableRich<>(parser);
    }

    public static SortedLinesDesc allSortedLinesDesc(
            CookieFileConfiguration cookieFileConfiguration) throws IOException {
        ArrayList<String> buffer = new ArrayList<>();
        FileReader fileReader = new FileReader(cookieFileConfiguration.getFile().toFile());
        BufferedReader bufferedReader = new BufferedReader(fileReader, DEFAULT_BUFFER);
        String line = null;
        if(cookieFileConfiguration.getFileHasHeader()) bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null) {
            buffer.add(line);
        }
        return new SortedLinesDesc(buffer, cookieFileConfiguration.getDataParser());
    }

}
