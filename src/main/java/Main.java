import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
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

    if (!command.equals("tokenize")) {
      System.err.println("Unknown command: " + command);
      System.exit(1);
    }

    String fileContents = "";
    try {
      fileContents = Files.readString(Path.of(filename));
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
      System.exit(1);
    }

    // Uncomment this block to pass the first stage
    //
    boolean hadError = false;
    if (fileContents.length() > 0) {
      for (char c : fileContents.toCharArray()) {
        if (c == '(')
          System.out.println("LEFT_PAREN ( null");
        else if (c == ')')
          System.out.println("RIGHT_PAREN ) null");
        else if (c == '{')
          System.out.println("LEFT_BRACE { null");
        else if (c == '}')
          System.out.println("RIGHT_BRACE } null");
        else if (c == '*')
          System.out.println("STAR * null");
        else if (c == '.')
          System.out.println("DOT . null");
        else if (c == '-')
          System.out.println("MINUS - null");
        else if (c == ';')
          System.out.println("SEMICOLON ; null");
        else if (c == ',')
          System.out.println("COMMA , null");
        else if (c == '+')
          System.out.println("PLUS + null");
        else {
          System.err.println("[line 1] Error: Unexpected character: " + c);
          hadError = true;
        }
      }
      System.out.println("EOF  null");
    } else {
      System.out.println("EOF  null");
    }
    if (hadError) {
      System.exit(65);
    } else {
      System.exit(0);
    }
  }
}
