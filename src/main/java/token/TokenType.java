package token;

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
        // ... existing code ...
        tempMap.put("var", "RESERVED");
        tempMap.put("while", "RESERVED");
        tempMap.put("EOF", "EOF");
        tempMap.put("IDENTIFIER", "IDENTIFIER");
        category = Collections.unmodifiableMap(tempMap);
    }

    static {
        Map<String, String> tempMap = new HashMap<>();
        tempMap.put("1", "NUMBER");
        tempMap.put("2", "NUMBER");
        // ... existing code ...
        tempMap.put("var", "VAR");
        tempMap.put("while", "WHILE");
        tempMap.put("EOF", "EOF");
        tempMap.put("IDENTIFIER", "IDENTIFIER");

        constants = Collections.unmodifiableMap(tempMap); // Make it read-only
    }

    public static String get(String key) { return constants.get(key); }

    // ... existing code ...
    public static boolean containsCategory(String key) {
        return category.containsKey(key);
    }
} 