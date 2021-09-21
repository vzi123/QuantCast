package com.quantcast.commands;

import com.quantcast.configuration.CookieFileConfiguration;
import com.quantcast.constants.Constants;
import com.quantcast.exceptions.BadDataFormatException;
import com.quantcast.exceptions.WrongArgumentsException;
import com.quantcast.model.Command;
import com.quantcast.output.OutputWriter;
import com.quantcast.service.CookieService;
import com.quantcast.strategy.CountingStrategy;
import com.quantcast.strategy.CountingStrategyFactory;
import com.quantcast.strategy.DataParser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MostActiveCookieCommandExecutor extends  CommandExecutor{
    public static String COMMAND_NAME = "most_active_cookie";

    public MostActiveCookieCommandExecutor(
            final CookieService cookieService, final OutputWriter outputWriter) {
        super(cookieService, outputWriter);
    }
    @Override
    public boolean validate(Command command) {



        Path filePath = Paths.get(command.getInputArgs().filePath);

        if (!Files.exists(filePath))
            throw new WrongArgumentsException("Given file path ('%s') doesn't exists: %s", Constants.OPT_FILE, filePath);

        if (Files.isDirectory(filePath))
            throw new WrongArgumentsException("Given file path ('%s') refers to a directory: %s", Constants.OPT_FILE, filePath);

        if (!Files.isReadable(filePath))
            throw new WrongArgumentsException("Given file path ('%s') is not readable: %s", Constants.OPT_FILE, filePath);

        DataParser dataParser = DataParser.withDefaults();
        try {
            dataParser.parseDate(command.getInputArgs().cookiesByDate);
        } catch (BadDataFormatException cause) {
            throw new WrongArgumentsException("Invalid Date ('%s') value : '%s'; " +
                            "date values should be compatible to" +
                            " this pattern: '%s'",
                    Constants.OPT_DATE, command.getInputArgs().cookiesByDate,
                    DataParser.DEFAULT_DATE_PATTERN);
        }

        return true;
    }

    @Override
    public void execute(Command command) {

        CountingStrategy countingStrategy =
                CountingStrategyFactory.getStrategy(CountingStrategy.Type.SIMPLE);
        CookieFileConfiguration cookieFileConfiguration = getCookieFileConfiguration(command);
        outputWriter.write(cookieService.findMostActiveCookies(countingStrategy,cookieFileConfiguration));


    }

    private CookieFileConfiguration getCookieFileConfiguration(Command command) {
        Path filePath = Paths.get(command.getInputArgs().filePath);
        return new CookieFileConfiguration(filePath,
                DataParser.withDefaults().parseDate(command.getInputArgs().cookiesByDate),
                CountingStrategy.Type.SIMPLE,
                !command.getInputArgs().noHeader,
                command.getInputArgs().failFast, DataParser.withDefaults());
    }
}
