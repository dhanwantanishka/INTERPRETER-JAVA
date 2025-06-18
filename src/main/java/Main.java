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
  private int line = 1;
  static private boolean hadError = false;

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
      if (match('/')) {
        while (peek() != '\n' && !isAtEnd())
          advance();
      } else {
        System.out.println("SLASH / null");
      }
      break;
    case ';':
      System.out.println("SEMICOLON ; null");
      break;
    case '\n':
      line++;
      break;
    case '=':
      if (match('=')) {
        System.out.println("EQUAL_EQUAL == null");
        break;
      }

      System.out.println("EQUAL = null");
      break;
    case '!':
      if (match('=')) {
        System.out.println("BANG_EQUAL != null");
        break;
      }

      System.out.println("BANG ! null");
      break;
    case '>':
      if (match('=')) {
        System.out.println("GREATER_EQUAL >= null");
        break;
      }

      System.out.println("GREATER > null");
      break;
    case '<':
      if (match('=')) {
        System.out.println("LESS_EQUAL <= null");
        break;
      }

      System.out.println("LESS < null");
      break;
    case ' ':
    case '\r':
    case '\t':
      // Ignore whitespace.
      break;
    default:
      System.err.printf("[line %d] Error: Unexpected character: %c\n", line, c);
      hadError = true;
      break;
    }
  }

  private char peek() {
    if (isAtEnd())
      return '\0';
    return file.charAt(current);
  }

  private boolean isAtEnd() { return current >= file.length(); }

  private boolean match(char expected) {
    if (isAtEnd())
      return false;
    if (file.charAt(current) != expected)
      return false;

    current++;
    return true;
  }

  public void scanTokens() {
    while (!isAtEnd()) {
      scanToken();
    }

    if (hadError) {
      System.out.println("EOF  null");
      System.exit(65);
    }

    System.out.println("EOF  null");
  }
}
