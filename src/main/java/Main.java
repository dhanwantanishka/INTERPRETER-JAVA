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

    int fileLen = fileContents.length();
    int exitCode = 0;
    int lineNum = 1;
    for (int i = 0; i < fileLen; i++) {
      if (fileContents.charAt(i) == '(') {
        System.out.println("LEFT_PAREN ( null");
      } else if (fileContents.charAt(i) == ')') {
        System.out.println("RIGHT_PAREN ) null");
      } else if (fileContents.charAt(i) == '{') {
        System.out.println("LEFT_BRACE { null");
      } else if (fileContents.charAt(i) == '}') {
        System.out.println("RIGHT_BRACE } null");
      } else if (fileContents.charAt(i) == '.') {
        System.out.println("DOT . null");
      } else if (fileContents.charAt(i) == ',') {
        System.out.println("COMMA , null");
      } else if (fileContents.charAt(i) == '+') {
        System.out.println("PLUS + null");
      } else if (fileContents.charAt(i) == '*') {
        System.out.println("STAR * null");
      } else if (fileContents.charAt(i) == '/') {
        System.out.println("SLASH / null");
      } else if (fileContents.charAt(i) == '-') {
        System.out.println("MINUS - null");
      } else if (fileContents.charAt(i) == ';') {
        System.out.println("SEMICOLON ; null");
      } else if (fileContents.charAt(i) == '\n') {
        lineNum++;
      } else if (i < fileLen - 1 && fileContents.charAt(i) == '=' &&
                 fileContents.charAt(i + 1) == '=') {
        System.out.println("EQUAL_EQUAL == null");
        i++;
      } else if (fileContents.charAt(i) == '=') {
        System.out.println("EQUAL = null");
      } else {
        System.err.println(
            "[line " + lineNum +
            "] Error: Unexpected character: " + fileContents.charAt(i));
        exitCode = 65;
      }
    }
    System.out.println("EOF  null"); // Placeholder, remove this line when
                                     // implementing the scanner
    System.exit(exitCode);
  }
}