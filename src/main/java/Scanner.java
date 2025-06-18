import java.util.Map;

public class Scanner {
  private final Map<String, String> tokens =
      Map.of("(", "LEFT_PAREN", ")", "RIGHT_PAREN", "{", "LEFT_BRACE", "}",
             "RIGHT_BRACE", "*", "STAR", ".", "DOT", ",", "COMMA", "+", "PLUS",
             "-", "MINUS", ";", "SEMICOLON");

  public boolean scan(String fileContents) {
    boolean isValidToken = true;
    int LnNBR = 1;
    int start, end;
    String val;
    String numberPattern = "[0-9\\\\.]";

    if (fileContents.length() > 0) {
      for (int i = 0; i < fileContents.length(); i++) {
        char c = fileContents.charAt(i);
        // System.err.printf("i is %d c is %c%n", i, c);
        if (tokens.containsKey(String.valueOf(c))) {
          System.out.println(tokens.get(String.valueOf(c)) + " " + c + " null");
        } else if (c == '=') {
          if (((i + 1) < fileContents.length()) &&
              (fileContents.charAt(i + 1) == '=')) {
            i = i + 1;
            System.out.println("EQUAL_EQUAL == null");
          } else {
            System.out.println("EQUAL = null");
          }
        } else if (c == '!') {
          if (((i + 1) < fileContents.length()) &&
              (fileContents.charAt(i + 1) == '=')) {
            i = i + 1;
            System.out.println("BANG_EQUAL != null");
          } else {
            System.out.println("BANG ! null");
          }
        } else if (c == '<') {
          if (((i + 1) < fileContents.length()) &&
              (fileContents.charAt(i + 1) == '=')) {
            i = i + 1;
            System.out.println("LESS_EQUAL <= null");
          } else {
            System.out.println("LESS < null");
          }
        } else if (c == '>') {
          if (((i + 1) < fileContents.length()) &&
              (fileContents.charAt(i + 1) == '=')) {
            i = i + 1;
            System.out.println("GREATER_EQUAL >= null");
          } else {
            System.out.println("GREATER > null");
          }
        } else if (c == '/') {
          if (((i + 1) < fileContents.length()) &&
              (fileContents.charAt(i + 1) == '/')) {
            i = i + 1;
            while ((i < fileContents.length()) &&
                   (fileContents.charAt(i) != '\n')) {
              i = i + 1;
            }
            LnNBR = LnNBR + 1;
          } else {
            System.out.println("SLASH / null");
          }
        } else if (c == '\n' || c == ' ' || c == '\t') {
          if (c == '\n') {
            LnNBR = LnNBR + 1;
          }
        } else if (c == '"') {
          //  string tokens;
          i = i + 1;
          start = i;
          while ((i < fileContents.length()) &&
                 (fileContents.charAt(i) != '"')) {
            // System.err.println("c is " + c);
            i = i + 1;
          }
          System.err.println("start " + start + "] end " + i);
          if (i >= fileContents.length()) {
            System.err.println("[line " + LnNBR +
                               "] Error: Unterminated string.");
            isValidToken = false;

          } else {
            end = i;

            val = fileContents.substring(start, end);
            System.out.println("STRING " +
                               fileContents.substring(start - 1, end + 1) +
                               " " + val);
          }
          start = 0;
          end = 0;

        } else if (String.valueOf(c).matches(numberPattern)) {
          // number tokens;
          start = i;
          while (
              (i < fileContents.length()) &&
              (String.valueOf(fileContents.charAt(i)).matches(numberPattern))) {
            i = i + 1;
            System.err.println("c is " + fileContents.charAt(i - 1) + " i is " +
                               i);
          }
          end = i;
          val = fileContents.substring(start, end);
          System.err.println("val is " + val);
          double num = Double.parseDouble(val);
          System.out.println("NUMBER " + val + " " + num);
          i = i - 1;

        }

        else {
          System.err.println("[line " + LnNBR +
                             "] Error: Unexpected character: " + c);
          isValidToken = false;
        }
      }
      System.out.println("EOF  null");
    } else {
      System.out.println("EOF  null");
    }
    return isValidToken;
  }
}