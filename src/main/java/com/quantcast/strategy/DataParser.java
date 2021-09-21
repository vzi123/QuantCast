package com.quantcast.strategy;

import com.quantcast.exceptions.BadDataFormatException;
import com.quantcast.model.Cookie;
import org.apache.commons.csv.CSVRecord;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class DataParser {

    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ssxxx";

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    public static DataParser withDefaults() {
        return new DataParser(
                DateTimeFormatter.ofPattern(DEFAULT_DATETIME_PATTERN),
                DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN)
        );
    }

    private final DateTimeFormatter dtf;
    private final DateTimeFormatter df;

    @Override
    public String toString() {
        return "DataParser{" +
                "dtf=" + dtf +
                ", df=" + df +
                '}';
    }

    public DataParser(DateTimeFormatter dtf, DateTimeFormatter df) {
        this.dtf = dtf;
        this.df = df;
    }

    public LocalDate parseDate(String str) {
        try {
            return LocalDate.from(df.parse(str));
        } catch (DateTimeParseException cause) {
            throw BadDataFormatException.format(cause, "Invalid Date: %s", str);
        }
    }

    public OffsetDateTime parseDateTime(String str) {
        try {
            return OffsetDateTime.from(dtf.parse(str));
        } catch (DateTimeParseException cause){
            throw BadDataFormatException.format(cause, "Invalid DateTime: %s", str);
        }
    }

    public Map<String, OffsetDateTime> parseCsvRecord(CSVRecord r) {
        try {
            Map<String, OffsetDateTime> map = new HashMap<>();
            map.put(r.get(0).trim(), parseDateTime(r.get(1).trim()));
            return map;
        } catch (BadDataFormatException cause) {
            throw BadDataFormatException.format(cause, "Invalid CSV Record: %s", r);
        }
    }


    public Cookie parseLine(String line) {
        try {
            String[] splits = line.split(",");
            if (splits.length != 2)
                // TODO : Provide more information on exception! probably a custom exception!
                throw new IllegalArgumentException(String.format("Invalid Line: '%s'", line));
            return new Cookie(splits[0].trim(),parseDateTime(splits[1].trim()));
        } catch (RuntimeException cause) {
            throw BadDataFormatException.format(cause, "Invalid CSV Line: %s", line);
        }
    }

}
