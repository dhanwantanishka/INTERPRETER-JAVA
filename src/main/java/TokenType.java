import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TokenType {
  private static final Map<String, String> constants;
  private static final Map<String, String> category;

  static {
    Map<String, String> tempMap = new HashMap<>();
    tempMap.put("1", "NUMBER");
    tempMap.put("2", "NUMBER");
    tempMap.put("3", "NUMBER");
    tempMap.put("4", "NUMBER");
    tempMap.put("5", "NUMBER");
    tempMap.put("6", "NUMBER");
    tempMap.put("7", "NUMBER");
    tempMap.put("8", "NUMBER");
    tempMap.put("9", "NUMBER");
    tempMap.put("0", "NUMBER");
    tempMap.put("//", "COMMENT");
    tempMap.put(")", "CONTAINER");
    tempMap.put("(", "CONTAINER");
    tempMap.put("{", "CONTAINER");
    tempMap.put("}", "CONTAINER");
    tempMap.put("+", "OPERATOR");
    tempMap.put("-", "OPERATOR");
    tempMap.put("*", "OPERATOR");
    tempMap.put("/", "OPERATOR");
    tempMap.put(".", "OPERATOR");
    tempMap.put("=", "OPERATOR");
    tempMap.put("==", "OPERATOR");
    tempMap.put("!", "OPERATOR");
    tempMap.put("!=", "OPERATOR");
    tempMap.put("<", "OPERATOR");
    tempMap.put(">", "OPERATOR");
    tempMap.put(";", "END_OF_LOGIC");
    tempMap.put("<=", "OPERATOR");
    tempMap.put(">=", "OPERATOR");
    tempMap.put(",", "OPERATOR");
    tempMap.put(" ", "WHITESPACE");
    tempMap.put("\t", "WHITESPACE");
    tempMap.put("\"", "STRING_LITERAL");
    tempMap.put("and", "RESERVED");
    tempMap.put("class", "RESERVED");
    tempMap.put("else", "RESERVED");
    tempMap.put("false", "RESERVED");
    tempMap.put("for", "RESERVED");
    tempMap.put("fun", "RESERVED");
    tempMap.put("if", "RESERVED");
    tempMap.put("nil", "RESERVED");
    tempMap.put("or", "RESERVED");
    tempMap.put("print", "RESERVED");
    tempMap.put("return", "RESERVED");
    tempMap.put("super", "RESERVED");
    tempMap.put("this", "RESERVED");
    tempMap.put("true", "RESERVED");
    tempMap.put("var", "RESERVED");
    tempMap.put("while", "RESERVED");
    category = Collections.unmodifiableMap(tempMap);
  }

  static {
    Map<String, String> tempMap = new HashMap<>();
    tempMap.put("1", "NUMBER");
    tempMap.put("2", "NUMBER");
    tempMap.put("3", "NUMBER");
    tempMap.put("4", "NUMBER");
    tempMap.put("5", "NUMBER");
    tempMap.put("6", "NUMBER");
    tempMap.put("7", "NUMBER");
    tempMap.put("8", "NUMBER");
    tempMap.put("9", "NUMBER");
    tempMap.put("0", "NUMBER");
    tempMap.put("(", "LEFT_PAREN");
    tempMap.put(")", "RIGHT_PAREN");
    tempMap.put("{", "LEFT_BRACE");
    tempMap.put("}", "RIGHT_BRACE");
    tempMap.put("+", "PLUS");
    tempMap.put("*", "STAR");
    tempMap.put(".", "DOT");
    tempMap.put(",", "COMMA");
    tempMap.put("-", "MINUS");
    tempMap.put(";", "SEMICOLON");
    tempMap.put("/", "SLASH");
    tempMap.put("//", "COMMENT");
    tempMap.put("=", "EQUAL");
    tempMap.put("==", "EQUAL_EQUAL");
    tempMap.put("!=", "BANG_EQUAL");
    tempMap.put("!", "BANG");
    tempMap.put("<", "LESS");
    tempMap.put(">", "GREATER");
    tempMap.put("<=", "LESS_EQUAL");
    tempMap.put(">=", "GREATER_EQUAL");
    tempMap.put(" ", "SPACE");
    tempMap.put("\t", "TABSPACE");
    tempMap.put("\"", "STRING_LITERAL");
    tempMap.put("and", "AND");
    tempMap.put("class", "CLASS");
    tempMap.put("else", "ELSE");
    tempMap.put("false", "FALSE");
    tempMap.put("for", "FOR");
    tempMap.put("fun", "FUN");
    tempMap.put("if", "IF");
    tempMap.put("nil", "NIL");
    tempMap.put("or", "OR");
    tempMap.put("print", "PRINT");
    tempMap.put("return", "RETURN");
    tempMap.put("super", "SUPER");
    tempMap.put("this", "THIS");
    tempMap.put("true", "TRUE");
    tempMap.put("var", "VAR");
    tempMap.put("while", "WHILE");

    constants = Collections.unmodifiableMap(tempMap); // Make it read-only
  }

  public static String get(String key) { return constants.get(key); }

  public static boolean containsKey(String key) {
    return constants.containsKey(key);
  }

  public static String getCategory(String key) { return category.get(key); }

  public static boolean containsCategory(String key) {
    return category.containsKey(key);
  }
}
