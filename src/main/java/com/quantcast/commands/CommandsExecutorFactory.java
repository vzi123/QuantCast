package com.quantcast.commands;

import com.quantcast.exceptions.InvalidCommandException;
import com.quantcast.model.Command;
import com.quantcast.output.OutputWriter;
import com.quantcast.service.CookieService;

import java.util.HashMap;
import java.util.Map;

public class CommandsExecutorFactory {

    private Map<String, CommandExecutor> commands = new HashMap<>();

    private static String DEFAULT_COMMAND = "default";

    public CommandsExecutorFactory(final CookieService cookieService,OutputWriter writer) {

        commands.put(
                MostActiveCookieCommandExecutor.COMMAND_NAME,
                new MostActiveCookieCommandExecutor(cookieService,writer ));
        commands.put(
                DEFAULT_COMMAND,
                new MostActiveCookieCommandExecutor(cookieService,writer ));


    }

    /**
     * Gets {@link CommandExecutor} for a particular command. It basically uses name of command to
     * fetch its corresponding executor.
     *
     * @param command Command for which executor has to be fetched.
     * @return Command executor.
     */
    public CommandExecutor getCommandExecutor(final Command command) throws InvalidCommandException {
        String commandName = command.getCommandName();
        if (commandName==null || commandName==""){
            commandName=DEFAULT_COMMAND;
        }
        final CommandExecutor commandExecutor = commands.get(commandName);

        if (commandExecutor == null) {
            throw new InvalidCommandException();
        }
        return commandExecutor;
    }

}
