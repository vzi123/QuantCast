package com.quantcast.mode;

import com.quantcast.commands.CommandsExecutorFactory;
import com.quantcast.exceptions.InvalidCommandException;
import com.quantcast.input.InputArgs;
import com.quantcast.input.InputHandler;
import com.quantcast.model.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mode running in which input commands are given from an interactive shell.
 */
public class InteractiveMode extends Mode {

   Command command ;

  public InteractiveMode(
          final CommandsExecutorFactory commandExecutorFactory) {
    super(commandExecutorFactory);
    command = new Command();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void process() throws IOException, InvalidCommandException {

    final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

       String input = reader.readLine();



      InputHandler inputHandler = new InputHandler();
      InputArgs inputArgs = inputHandler.getInputArgs(processUserInput(input));
      command.setInputArgs(inputArgs);
      processCommand(command);


  }

  @Override
  public void process(InputArgs inputArgs) throws IOException, InvalidCommandException {
    command.setInputArgs(inputArgs);
    processCommand(command);

  }

  public  String[] processUserInput(String inputLine) throws InvalidCommandException {

    final List<String> tokensList = Arrays.stream(inputLine.trim().split(" "))
            .map(String::trim)
            .filter(token -> (token.length() > 0)).collect(Collectors.toList());
    String a[]=new String[tokensList.size()-1];
    if (tokensList.size() == 0) {
      throw new InvalidCommandException();
    }

    command.setCommandName( tokensList.get(0).toLowerCase());
    tokensList.remove(0);
    return tokensList.toArray(a);
  }
}
