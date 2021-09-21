package com.quantcast;

import com.quantcast.commands.CommandsExecutorFactory;
import com.quantcast.exceptions.InvalidCommandException;
import com.quantcast.input.InputArgs;
import com.quantcast.input.InputHandler;
import com.quantcast.mode.InteractiveMode;
import com.quantcast.output.ConsoleWriter;
import com.quantcast.output.OutputWriter;
import com.quantcast.service.CookieService;


import java.io.IOException;
import java.util.Calendar;

public class MainApplication {

    public static void main(String[] in) {
        try {

            OutputWriter outputWriter = new ConsoleWriter();
        final CommandsExecutorFactory commandExecutorFactory =
                new CommandsExecutorFactory(new CookieService(),outputWriter);


        if (isInteractiveMode(in)) {

            InputHandler inputHandler = new InputHandler();

            InputArgs inputArgs =  inputHandler.getInputArgs(in);

            new InteractiveMode(commandExecutorFactory).process(inputArgs);
        }else {
            new InteractiveMode(commandExecutorFactory).process();
        }



    } catch (IOException e) {
        e.printStackTrace();
    } catch (InvalidCommandException e) {
        e.printStackTrace();
    }

    }

    /**
     * Checks whether the program is running using file input mode.
     *
     * @param args Command line arguments.
     * @return Boolean indicating whether in file input mode.
     */
    private static boolean isFileInputMode(final String[] args) {
        return args.length == 0;
    }

    /**
     * Checks whether the program is running using interactive shell mode.
     *
     * @param args Command line arguments.
     * @return Boolean indicating whether in interactive shell mode.
     */
    private static boolean isInteractiveMode(final String[] args) {
        return args.length >0;
    }
}
