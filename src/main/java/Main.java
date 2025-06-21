import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
  public static void printLogs() {
    for (String log : GlobalStorage.getLogs()) {
      if (log.contains("Error:"))
        System.err.println(log);
      else
        System.out.println(log);
    }
    System.out.println("EOF  null");
  }

  public static void main(String[] args) {
    // You can use print statements as follows for debugging, they'll be visible
    // when running tests.
    System.err.println("Logs from your program will appear here!");

    if (args.length < 2) {
      System.err.println("Usage: ./your_program.sh tokenize <filename>");
      System.exit(1);
    }

    String command = args[0];
    String filename = args[1];

    if (!command.equals("tokenize") && !command.equals("parse")) {
      System.err.println("Unknown command: " + command);
      System.exit(1);
    }

    Tokenizer tokenizer = new Tokenizer();
    tokenizer.Scanning(filename);
    Parse parse = new Parse();
    if (command.equals("tokenize")) {
      printLogs();
    }
    if (command.equals("parse")) {
      parse.parse();
    }

    System.exit(GlobalStorage.exitCode);
  }
}
