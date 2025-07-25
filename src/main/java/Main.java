import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
  public static void printTokens() {
    for (Token token : GlobalStorage.getTokens()) {
        System.out.println(token.toString());
    }
  }

  public static void main(String[] args) {
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
    
    if (command.equals("tokenize")) {
      printTokens();
    }
    if (command.equals("parse")) {
      Parse parse = new Parse();
      parse.parse();
    }

    System.exit(GlobalStorage.exitCode);
  }
}
