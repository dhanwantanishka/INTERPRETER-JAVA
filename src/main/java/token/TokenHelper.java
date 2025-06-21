package token;

import java.util.ArrayList;
import java.util.List;
import tool.Reporter;

public class TokenHelper {

    private static String source;
    private static final List<Token> tokens = new ArrayList<>();
    private static int start = 0;
    private static int current = 0;
    private static int line = 1;

    public static List<Token> scan(String fileContents) {
        source = fileContents;
        //- Reset state for each scan
        tokens.clear();
        start = 0;
        current = 0;
        line = 1;

        while (!isAtEnd()) {
            start = current;
            scanToken();
        }

        tokens.add(new Token(TokenType.get("EOF"), "", null, line));
        return new ArrayList<>(tokens);
    }

    private static void scanToken() {
        char c = advance();
        switch (c) {
            case '(': addToken(TokenType.get("(")); break;
            case ')': addToken(TokenType.get(")")); break;
            case '{': addToken(TokenType.get("{")); break;
            case '}': addToken(TokenType.get("}")); break;
            case ',': addToken(TokenType.get(",")); break;
            case '.': addToken(TokenType.get(".")); break;
            case '-': addToken(TokenType.get("-")); break;
            case '+': addToken(TokenType.get("+")); break;
            case ';': addToken(TokenType.get(";")); break;
            case '*': addToken(TokenType.get("*")); break;
            case '!':
                addToken(match('=') ? TokenType.get("!=") : TokenType.get("!"));
                break;
            case '=':
                addToken(match('=') ? TokenType.get("==") : TokenType.get("="));
                break;
            case '<':
                addToken(match('=') ? TokenType.get("<=") : TokenType.get("<"));
                break;
            case '>':
                addToken(match('=') ? TokenType.get(">=") : TokenType.get(">"));
                break;
            case '/':
                if (match('/')) {
                    while (peek() != '\n' && !isAtEnd()) advance();
                } else {
                    addToken(TokenType.get("/"));
                }
                break;
            case ' ':
            case '\r':
            case '\t':
                break;
            case '\n':
                line++;
                break;
            case '"':
                string();
                break;
            default:
                if (isDigit(c)) {
                    number();
                } else if (isAlpha(c)) {
                    identifier();
                } else {
                    Reporter.error(line, "Unexpected character: " + c);
                }
                break;
        }
    }

    private static void identifier() {
        while (isAlphaNumeric(peek())) advance();
        String text = source.substring(start, current);
        String type = TokenType.get(text);
        if (type == null) type = "IDENTIFIER";
        addToken(type);
    }

    private static void number() {
        while (isDigit(peek())) advance();
        if (peek() == '.' && isDigit(peekNext())) {
            advance();
            while (isDigit(peek())) advance();
        }
        addToken("NUMBER", Double.parseDouble(source.substring(start, current)));
    }

    private static void string() {
        while (peek() != '"' && !isAtEnd()) {
            if (peek() == '\n') line++;
            advance();
        }
        if (isAtEnd()) {
            Reporter.error(line, "Unterminated string.");
            return;
        }
        advance();
        String value = source.substring(start + 1, current - 1);
        addToken("STRING", value);
    }

    private static boolean match(char expected) {
        if (isAtEnd() || source.charAt(current) != expected) return false;
        current++;
        return true;
    }

    private static char peek() {
        return isAtEnd() ? '\0' : source.charAt(current);
    }

    private static char peekNext() {
        return current + 1 >= source.length() ? '\0' : source.charAt(current + 1);
    }

    private static boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
    }

    private static boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isAtEnd() {
        return current >= source.length();
    }

    private static char advance() {
        return source.charAt(current++);
    }

    private static void addToken(String type) {
        addToken(type, null);
    }

    private static void addToken(String type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }
} 