package com.quantcast.test.commands;



import com.quantcast.commands.CommandExecutor;
import com.quantcast.commands.CommandsExecutorFactory;
import com.quantcast.commands.MostActiveCookieCommandExecutor;
import com.quantcast.exceptions.InvalidCommandException;
import com.quantcast.model.Command;
import com.quantcast.output.OutputWriter;
import com.quantcast.service.CookieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommandExecutorFactoryTest {


  private CommandsExecutorFactory factory;

  private MostActiveCookieCommandExecutor mostActiveCookieCommandExecutor;



  @Test
  public void testFetchingExecutorForValidCommand() throws InvalidCommandException {
    final OutputWriter outputWriter = Mockito.mock(OutputWriter.class);
    final CookieService cookieService = Mockito.mock(CookieService.class);

    factory = new CommandsExecutorFactory(cookieService,outputWriter);
    final CommandExecutor commandExecutor = factory.getCommandExecutor(new Command("most_active_cookie"));
    Assertions.assertNotNull(commandExecutor);
    Assertions.assertTrue(commandExecutor instanceof MostActiveCookieCommandExecutor);
  }


}
