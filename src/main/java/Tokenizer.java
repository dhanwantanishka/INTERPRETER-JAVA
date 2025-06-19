import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Tokenizer {

  // Map for token types
  private static final Map<String, String> TOKEN_TYPE = new HashMap<>() {{
    put("(", "LEFT_PAREN");
    put(")", "RIGHT_PAREN");
    put("{", "LEFT_BRACE");
    put("}", "RIGHT_BRACE");
    put(",", "COMMA");
    put(".", "DOT");
    put("-", "MINUS");
    put("+", "PLUS");
    put(";", "SEMICOLON");
    put("*", "STAR");
    put("/", "SLASH");
    put("//", "COMMENT");
    put(" ", "WHITESPACE");
    put("\t", "WHITESPACE");
    put("\r", "WHITESPACE");
    put("\n", "WHITESPACE");
    put("=", "EQUAL");
    put("==", "EQUAL_EQUAL");
    put("!", "BANG");
    put("!=", "BANG_EQUAL");
    put("<", "LESS");
    put("<=", "LESS_EQUAL");
    put(">", "GREATER");
    put(">=", "GREATER_EQUAL");
    put("\"", "STRING_LITERAL");
  }};

  // Map for token categories
  private static final Map<String, String> TOKEN_CATEGORY = new HashMap<>() {{
    put("//", "COMMENT");
    put(" ", "WHITESPACE");
    put("\t", "WHITESPACE");
    put("\r", "WHITESPACE");
    put("\n", "WHITESPACE");
    put("\"", "STRING_LITERAL");
    // For single character tokens, treat as operator
    put("(", "OPERATOR");
    put(")", "OPERATOR");
    put("{", "OPERATOR");
    put("}", "OPERATOR");
    put(",", "OPERATOR");
    put(".", "OPERATOR");
    put("-", "OPERATOR");
    put("+", "OPERATOR");
    put(";", "OPERATOR");
    put("*", "OPERATOR");
    put("/", "OPERATOR");
    put("=", "OPERATOR");
    put("==", "OPERATOR");
    put("!", "OPERATOR");
    put("!=", "OPERATOR");
    put("<", "OPERATOR");
    put("<=", "OPERATOR");
    put(">", "OPERATOR");
    put(">=", "OPERATOR");
  }};

  public String tokenCheck(String token) {
    if (TOKEN_TYPE.containsKey(token)) {
      String category = TOKEN_CATEGORY.getOrDefault(token, "OPERATOR");
      if (category.equals("COMMENT")) {
        return "SKIP_LINE";
      } else if (category.equals("WHITESPACE")) {
        return "CONTINUE";
      } else {
        return "OPERATOR";
      }
    } else {
      return "NOT_FOUND";
    }
  }

  public String parseString(String line, int i) {
    StringBuilder str = new StringBuilder();
    while (i < line.length() && line.charAt(i) != '"') {
      str.append(line.charAt(i));
      i++;
    }
    return str.toString();
  }

  public String parseNumber(String line, int i) {
    StringBuilder num = new StringBuilder();
    int dot = 0;
    while (i < line.length() && (Character.isDigit(line.charAt(i)) ||
                                 (line.charAt(i) == '.' && dot == 0))) {
      if (line.charAt(i) == '.') {
        dot++;
      }
      num.append(line.charAt(i));
      i++;
    }
    return num.toString();
  }

  public void Scanning(String filePath) {
    int exitCode = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      int lineNumber = 1;
      while ((line = br.readLine()) != null) {
        for (int i = 0; i < line.length(); i++) {
          // Check for two-character tokens first
          if (i < line.length() - 1 && TOKEN_TYPE.containsKey(line.substring(i, i + 2))) {
            String twoChar = line.substring(i, i + 2);
            String category = TOKEN_CATEGORY.getOrDefault(twoChar, "OPERATOR");
            if (category.equals("COMMENT")) {
              break;
            }
            System.out.println(TOKEN_TYPE.get(twoChar) + " " + twoChar + " null");
            i++;
          }
          // Single character tokens
          else if (TOKEN_TYPE.containsKey(line.substring(i, i + 1))) {
            String oneChar = line.substring(i, i + 1);
            String category = TOKEN_CATEGORY.getOrDefault(oneChar, "OPERATOR");
            if (category.equals("WHITESPACE")) {
              continue;
            }
            if (category.equals("STRING_LITERAL")) {
              String str = parseString(line, ++i);
              i += str.length();
              if (i == line.length() || (i < line.length() && line.charAt(i) != '"')) {
                System.err.println("[line " + lineNumber + "] Error: Unterminated string.");
                exitCode = 65;
                continue;
              }
              System.out.println("STRING \"" + str + "\" " + str);
              continue;
            }
            // Number literal
            if (Character.isDigit(oneChar.charAt(0))) {
              String num = parseNumber(line, i);
              i += num.length() - 1;
              BigDecimal bd = new BigDecimal(num);
              String cleaned = bd.stripTrailingZeros().toPlainString();
              if (cleaned.split("\\.").length == 1) {
                System.out.println("NUMBER " + num + " " + cleaned + ".0");
              } else {
                System.out.println("NUMBER " + num + " " + cleaned);
              }
              continue;
            }
            System.out.println(TOKEN_TYPE.get(oneChar) + " " + oneChar + " null");
          }
          // Identifier
          else if (Character.isAlphabetic(line.charAt(i)) || line.charAt(i) == '_') {
            StringBuilder identifier = new StringBuilder();
            while (i < line.length() &&
                   !(line.charAt(i) == ' ' || line.charAt(i) == '(' ||
                     line.charAt(i) == ')' || line.charAt(i) == ',' ||
                     line.charAt(i) == '{' || line.charAt(i) == '}' ||
                     line.charAt(i) == ';')) {
              identifier.append(line.charAt(i));
              i++;
            }
            i--;
            System.out.println("IDENTIFIER " + identifier + " null");
          } else if (Character.isDigit(line.charAt(i))) {
            // Number literal not caught above
            String num = parseNumber(line, i);
            i += num.length() - 1;
            BigDecimal bd = new BigDecimal(num);
            String cleaned = bd.stripTrailingZeros().toPlainString();
            if (cleaned.split("\\.").length == 1) {
              System.out.println("NUMBER " + num + " " + cleaned + ".0");
            } else {
              System.out.println("NUMBER " + num + " " + cleaned);
            }
          } else {
            System.err.println(
                "[line " + lineNumber +
                "] Error: Unexpected character: " + line.substring(i, i + 1));
            exitCode = 65;
          }
        }
        lineNumber++;
      }
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
      System.exit(1);
    } finally {
      System.out.println("EOF  null");
      if (exitCode != 0) {
        System.exit(exitCode);
      }
    }
  }
}
