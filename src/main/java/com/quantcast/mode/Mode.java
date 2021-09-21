package com.quantcast.mode;


import com.quantcast.commands.CommandExecutor;
import com.quantcast.commands.CommandsExecutorFactory;
import com.quantcast.exceptions.InvalidCommandException;
import com.quantcast.input.InputArgs;
import com.quantcast.model.Command;

import java.io.IOException;

/**
 * Interface for mode of the program in which it can be run.
 */
public abstract class Mode {

  private CommandsExecutorFactory commandExecutorFactory;

  public Mode(
      final CommandsExecutorFactory commandExecutorFactory) {
    this.commandExecutorFactory = commandExecutorFactory;
  }

  /**
   * Helper method to process a command. It basically uses {@link CommandExecutor} to run the given
   * command.
   *
   * @param command Command to be processed.
   */
  protected void processCommand(final Command command) throws InvalidCommandException {
    final CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);
    if (commandExecutor.validate(command)) {
      commandExecutor.execute(command);
    } else {
      throw new InvalidCommandException();
    }
  }

  /**
   * Abstract method to process the mode. Each mode will process in its own way.
   *
   * @throws IOException
   */
  public abstract void process() throws IOException, InvalidCommandException;

  public abstract void process(InputArgs inputArgs) throws IOException, InvalidCommandException;
}
