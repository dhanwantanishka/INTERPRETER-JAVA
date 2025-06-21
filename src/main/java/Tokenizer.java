import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class Tokenizer {

  public String tokenCheck(String token) {
    if (TokenType.containsKey(token)) {
      if (TokenType.getCategory(token).equals("COMMENT")) {
        return "SKIP_LINE";
      } else if (TokenType.getCategory(token).equals("WHITESPACE")) {
        return "CONTINUE";
      } else {
        return "OPERATOR";
      }
    } else {
      return "NOT_FOUND";
    }
  }

  public String parseString(String line, int i) {
    String str = "";
    while (i < line.length() && line.charAt(i) != '"') {
      str += line.charAt(i);
      i++;
    }
    return str;
  }

  public String parseNumber(String line, int i) {
    String num = "";
    int dot = 0;
    while (i < line.length() && (Character.isDigit(line.charAt(i)) ||
                                 line.charAt(i) == '.' && dot == 0)) {
      if (line.charAt(i) == '.') {
        dot++;
      }
      num += line.charAt(i);
      i++;
    }
    return num;
  }

  public void Scanning(String filePath) {
    int exitCode = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      int lineNumber = 1;
      while ((line = br.readLine()) != null) {

        for (int i = 0; i < line.length(); i++) {
          if (i < line.length() - 1 &&
              TokenType.containsKey(line.substring(i, i + 2))) {
            if (TokenType.getCategory(line.substring(i, i + 2))
                    .equals("COMMENT")) {
              break;
            }
            System.out.println(TokenType.get(line.substring(i, i + 2)) + " " +
                               line.substring(i, i + 2) + " null");
            i++;
          }

          else if (TokenType.containsKey(line.substring(i, i + 1))) {
            if (TokenType.getCategory(line.substring(i, i + 1))
                    .equals("WHITESPACE")) {
              continue;
            }
            if (TokenType.getCategory(line.substring(i, i + 1))
                    .equals("STRING_LITERAL")) {
              String str = parseString(line, ++i);
              i += str.length();
              if (i == line.length() ||
                  (i < line.length() && line.charAt(i) != '"')) {
                System.err.println("[line " + lineNumber +
                                   "] Error: Unterminated string.");
                exitCode = 65;
                continue;
              }
              System.out.println("STRING \"" + str + "\" " + str);
              continue;
            }
            if (TokenType.getCategory(line.substring(i, i + 1))
                    .equals("NUMBER")) {
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
            System.out.println(TokenType.get(line.substring(i, i + 1)) + " " +
                               line.substring(i, i + 1) + " null");
          } else if (Character.isAlphabetic(line.charAt(i)) ||
                     line.charAt(i) == '_') {
            String identifier = "";
            while (i < line.length() &&
                   !(line.charAt(i) == ' ' || line.charAt(i) == '(' ||
                     line.charAt(i) == ')' || line.charAt(i) == ',' ||
                     line.charAt(i) == '{' || line.charAt(i) == '}' ||
                     line.charAt(i) == ';')) {
              identifier += line.charAt(i);
              i++;
            }
            i--;
            if (TokenType.containsKey(identifier)) {
              System.out.println(TokenType.get(identifier) + " " + identifier +
                                 " null");
            } else {
              System.out.println("IDENTIFIER " + identifier + " null");
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
