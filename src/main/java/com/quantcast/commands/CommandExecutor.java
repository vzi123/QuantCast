package com.quantcast.commands;

import com.quantcast.model.Command;
import com.quantcast.output.OutputWriter;
import com.quantcast.service.CookieService;
import com.quantcast.service.ICookieService;

import java.util.List;

/**
 * Command executor interface.
 */


public abstract class CommandExecutor {

    ICookieService cookieService;

    OutputWriter<List<String>> outputWriter;


    public CommandExecutor(CookieService cookieService, OutputWriter<List<String>> outputWriter) {
        this.cookieService = cookieService;
        this.outputWriter = outputWriter;
    }

    /**
     * Validates that whether a command is valid to be executed or not.
     *
     * @param command Command to be validated.
     * @return Boolean indicating whether command is valid or not.
     */
    public abstract boolean validate(Command command);

    /**
     * Executes the command.
     *
     * @param command Command to be executed.
     */
    public abstract void execute(Command command);

}
