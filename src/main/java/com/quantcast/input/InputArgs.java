package com.quantcast.input;


import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = " ")

public class InputArgs {

   @Parameter(names = {"-f","filePath"}, required = true)
    public String filePath;

   @Parameter(names = {"-d","cookiesByDate"}, required = true)
    public String cookiesByDate;

    @Parameter(names = {"-h","noHeader"}, required = false)
    public boolean noHeader;

    @Parameter(names = {"-failfast","failFast"}, required = false)
    public boolean failFast;

}
