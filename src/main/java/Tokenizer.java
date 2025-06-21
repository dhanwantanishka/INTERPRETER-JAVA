import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Tokenizer {

    private String source;
    private int start = 0;
    private int current = 0;
    private int line = 1;

    public void Scanning(String filename) {
        GlobalStorage.clear();
        try {
            source = Files.readString(Path.of(filename));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            GlobalStorage.exitCode = 1;
            return;
        }

        while (!isAtEnd()) {
            start = current;
            scanToken();
        }

        GlobalStorage.addToken(new Token("EOF", "", null, line));
    }

    private void scanToken() {
        char c = advance();
        String c_string = String.valueOf(c);
        if (TokenType.SINGLE_CHAR_TOKENS.containsKey(c_string)) {
            handleSingleCharTokens(c);
        } else {
            switch (c) {
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
                        GlobalStorage.addLog("[line " + line + "] Error: Unexpected character: " + c);
                        GlobalStorage.exitCode = 65;
                    }
                    break;
            }
        }
    }

    private void handleSingleCharTokens(char c) {
        String c_string = String.valueOf(c);
        String tokenType = TokenType.SINGLE_CHAR_TOKENS.get(c_string);
        if (c == '!' && match('=')) {
            addToken("BANG_EQUAL");
        } else if (c == '=' && match('=')) {
            addToken("EQUAL_EQUAL");
        } else if (c == '<' && match('=')) {
            addToken("LESS_EQUAL");
        } else if (c == '>' && match('=')) {
            addToken("GREATER_EQUAL");
        } else if (c == '/' && match('/')) {
            while (peek() != '\n' && !isAtEnd()) advance();
        } else {
            addToken(tokenType);
        }
    }

    private void identifier() {
        while (isAlphaNumeric(peek())) advance();
        String text = source.substring(start, current);
        String type = TokenType.KEYWORDS.get(text);
        if (type == null) type = "IDENTIFIER";
        addToken(type);
    }

    private void number() {
        while (isDigit(peek())) advance();
        if (peek() == '.' && isDigit(peekNext())) {
            advance();
            while (isDigit(peek())) advance();
        }
        addToken("NUMBER", Double.parseDouble(source.substring(start, current)));
    }

    private void string() {
        while (peek() != '"' && !isAtEnd()) {
            if (peek() == '\n') line++;
            advance();
        }
        if (isAtEnd()) {
            GlobalStorage.addLog("[line " + line + "] Error: Unterminated string.");
            GlobalStorage.exitCode = 65;
            return;
        }
        advance();
        String value = source.substring(start + 1, current - 1);
        addToken("STRING", value);
    }

    private boolean match(char expected) {
        if (isAtEnd() || source.charAt(current) != expected) return false;
        current++;
        return true;
    }

    private char peek() {
        return isAtEnd() ? '\0' : source.charAt(current);
    }

    private char peekNext() {
        return current + 1 >= source.length() ? '\0' : source.charAt(current + 1);
    }

    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
    }

    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    private char advance() {
        return source.charAt(current++);
    }

    private void addToken(String type) {
        addToken(type, null);
    }

    private void addToken(String type, Object literal) {
        String text = source.substring(start, current);
        GlobalStorage.addToken(new Token(type, text, literal, line));
    }
} 