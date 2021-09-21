package com.quantcast.constants;

public class Constants {

    public static final String MISSING_CSV_HEADER_OR_INVALID_FORMAT_ERROR_MESSAGE =
            "The csv file has no headers or incorrect format, please ensure it has the correct upload format";

    public static final String CSV_FILE_EXTENSION_ERROR_ERROR_MESSAGE =
            "Extension should be only Csv file allowed";
    public static final String FILE_EXTENSION_PATTERN =
            "([^\\.]+(\\.(?i)(csv))$)";
    public static final String REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]+$";
    public static final String COOKIE = "cookie";
    public static final String TIMESTAMP = "timestamp";
    public static final String DATE_REGEX = "((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";


    public static final String OPT_FILE = "f";
    public static final String OPT_DATE = "d";
    public static final String OPT_NO_HEADER = "noheader";
    public static final String OPT_COUNTING_ENGINE = "e";
    public static final String OPT_FAIL_FAST = "failfast";

    private Constants() {
    }

}
