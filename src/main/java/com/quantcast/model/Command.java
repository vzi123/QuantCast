package com.quantcast.model;

import com.quantcast.exceptions.InvalidCommandException;
import com.quantcast.input.InputArgs;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command {

    private static final String SPACE = " ";



    private String commandName;
    private List<String> params;

    private InputArgs inputArgs;

    public InputArgs getInputArgs() {
        return inputArgs;
    }

    public void setInputArgs(InputArgs inputArgs) {
        this.inputArgs = inputArgs;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

    public List<String> getParams() {
        return params;
    }

    /**
     * Constructor. It takes the input line and parses the command name and param out of it. If the
     * command or its given params are not valid, then {@link InvalidCommandException} is thrown.
     *
     * @param inputLine Given input command line.
     */
    public Command(final String inputLine) throws InvalidCommandException {
        final List<String> tokensList = Arrays.stream(inputLine.trim().split(SPACE))
                .map(String::trim)
                .filter(token -> (token.length() > 0)).collect(Collectors.toList());

        if (tokensList.size() == 0) {
            throw new InvalidCommandException();
        }

        commandName = tokensList.get(0).toLowerCase();
        tokensList.remove(0);
        params = tokensList;
    }

    public Command(){

    }
}
