package com.quantcast.test.model;


import com.quantcast.exceptions.InvalidCommandException;
import com.quantcast.model.Command;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommandTest {

  @Test
  public void testCommandParsingFromInput() throws InvalidCommandException {
    validateCommandParsing("my_command 1 2 3", "my_command", Arrays.asList("1", "2", "3"));
    validateCommandParsing("my_command   1  2 ", "my_command", Arrays.asList("1", "2"));
    validateCommandParsing("my_command", "my_command", Collections.emptyList());
    validateCommandParsing("  my_command     ", "my_command", Collections.emptyList());
  }



  /**
   * Helper method to validate command parsing.
   *
   * @param input Input line.
   * @param expectedCommandName Expected command name from input.
   * @param expectedParams Expected command params from inout.
   */
  private void validateCommandParsing(
      final String input, final String expectedCommandName, final List expectedParams) throws InvalidCommandException {
    Command command = new Command(input);
    Assertions.assertNotNull(command);
    Assertions.assertEquals(expectedCommandName, command.getCommandName());
    Assertions.assertEquals(expectedParams, command.getParams());
  }
}
