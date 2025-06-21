import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TokenType {
    public static final Map<String, String> KEYWORDS;
    public static final Map<String, String> SINGLE_CHAR_TOKENS;

    static {
        Map<String, String> keywordsMap = new HashMap<>();
        keywordsMap.put("true", "TRUE");
        keywordsMap.put("false", "FALSE");
        keywordsMap.put("nil", "NIL");
        KEYWORDS = Collections.unmodifiableMap(keywordsMap);

        Map<String, String> singleCharMap = new HashMap<>();
        singleCharMap.put("(", "LEFT_PAREN");
        singleCharMap.put(")", "RIGHT_PAREN");
        singleCharMap.put("{", "LEFT_BRACE");
        singleCharMap.put("}", "RIGHT_BRACE");
        singleCharMap.put(",", "COMMA");
        singleCharMap.put(".", "DOT");
        singleCharMap.put("-", "MINUS");
        singleCharMap.put("+", "PLUS");
        singleCharMap.put(";", "SEMICOLON");
        singleCharMap.put("*", "STAR");
        singleCharMap.put("!", "BANG");
        singleCharMap.put("=", "EQUAL");
        singleCharMap.put("<", "LESS");
        singleCharMap.put(">", "GREATER");
        singleCharMap.put("/", "SLASH");
        SINGLE_CHAR_TOKENS = Collections.unmodifiableMap(singleCharMap);
    }
} 