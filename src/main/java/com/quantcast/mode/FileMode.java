package com.quantcast.mode;

import com.quantcast.commands.CommandsExecutorFactory;
import com.quantcast.exceptions.InvalidCommandException;
import com.quantcast.input.InputArgs;
import com.quantcast.model.Command;
import com.quantcast.output.OutputWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Mode running in which input commands are given from a file.
 */
public class FileMode extends Mode {
  private String fileName;

  public FileMode(
      final CommandsExecutorFactory commandExecutorFactory,
      final OutputWriter outputWriter,
      final String fileName) {
    super(commandExecutorFactory);
    this.fileName = fileName;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void process() throws IOException, InvalidCommandException {
    final File file = new File(fileName);
    final BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(file));
    } catch (FileNotFoundException e) {
      System.out.println("File not Found");;
      return;
    }

    String input = reader.readLine();
    while (input != null) {
      final Command command = new Command(input);
      processCommand(command);
      input = reader.readLine();
    }
  }

  @Override
  public void process(InputArgs inputArgs) throws IOException, InvalidCommandException {

  }
}
