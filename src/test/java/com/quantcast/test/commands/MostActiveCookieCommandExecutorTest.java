package com.quantcast.test.commands;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import com.quantcast.commands.MostActiveCookieCommandExecutor;
import com.quantcast.exceptions.InvalidCommandException;
import com.quantcast.input.InputArgs;
import com.quantcast.model.Command;
import com.quantcast.output.OutputWriter;
import com.quantcast.service.CookieService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MostActiveCookieCommandExecutorTest {
  private CookieService cookieService;
  private OutputWriter outputWriter;
  private MostActiveCookieCommandExecutor mostActiveCookieCommandExecutor;
  private Command command;


  @Test
  public void testValidCommand() throws InvalidCommandException {
    cookieService = mock(CookieService.class);
    outputWriter = mock(OutputWriter.class);
    command = mock(Command.class);
    mostActiveCookieCommandExecutor =
            new MostActiveCookieCommandExecutor(cookieService, outputWriter);
    InputArgs inputArgs = new InputArgs();
    inputArgs.failFast =true;
    inputArgs.cookiesByDate="2018-12-09";
    inputArgs.filePath="src/main/resources/test-inputs/input1.csv";
   when(command.getInputArgs()).thenReturn(inputArgs);
    Assertions.assertTrue(
            mostActiveCookieCommandExecutor.validate(
            command));
  }


}
