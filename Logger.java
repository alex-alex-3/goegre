package streme.lang;

import java.io.OutputStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Logging
{
  static public void setup()
  {
    setup(Level.INFO);
  }

  
  static public void setup(Level level)
  {
    // Create Logger
    Logger.getLogger("").stream()
      .forEach(l -> logger.removeHandler(l));
    
    logger.setLevel(level);
    
    ConsoleHandler consoleHandler = new ConsoleHandler((super) => super.setOutputStream(System.out))
    
    logger.addHandler(consoleHandler);
    consoleHandler.setFormatter((record) =>
      {
        String sourceClassName = record.getSourceClassName();
        return sourceClassName.substring(sourceClassName.lastIndexOf('.') + 1) + "." + record.getSourceMethodName() + ": " + record.getMessage() + "\n";
      }
    )
      
    consoleHandler.setLevel(Level.ALL);
  }
}
