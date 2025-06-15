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
    // Implement the scanner
    if (fileContents.length() > 0) {
      Scanner scanner = new Scanner(fileContents);
      scanner.scanTokens();
    } else {
      System.out.println("EOF  null"); // Placeholder, replace this line when
                                       // implementing the scanner
    }
  }
}

class Scanner {
  private int current = 0;
  private String file;

  Scanner(String fileContents) { this.file = fileContents; }

  private char advance() { return file.charAt(current++); }

  private void scanToken() {
    char c = advance();
    switch (c) {
    case '(':
      System.out.println("LEFT_PAREN ( null");
      break;
    case ')':
      System.out.println("RIGHT_PAREN ) null");
      break;
    case '}':
      System.out.println("RIGHT_BRACE } null");
      break;
    case '{':
      System.out.println("LEFT_BRACE { null");
      break;
    case '*':
      System.out.println("STAR * null");
      break;
    case '+':
      System.out.println("PLUS + null");
      break;
    case '.':
      System.out.println("DOT . null");
      break;
    case ',':
      System.out.println("COMMA , null");
      break;
    case '-':
      System.out.println("MINUS - null");
      break;
    case '/':
      System.out.println("SLASH / null");
      break;
    case ';':
      System.out.println("SEMICOLON ; null");
      break;
    default:
      System.out.println("Not added " + c);
    }
  }

  private boolean isAtEnd() { return current >= file.length(); }

  public void scanTokens() {
    while (!isAtEnd()) {
      scanToken();
    }

    System.out.println("EOF  null");
  }
}
