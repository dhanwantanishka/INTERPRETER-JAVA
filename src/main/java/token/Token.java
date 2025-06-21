package token;

public class Token {
    public final String type;
    public final String lexeme;
    public final Object literal;
    public final int line;

    public Token(String type, String lexeme, Object literal, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }

    @Override
    public String toString() {
        return type + " " + lexeme + " " + (literal == null ? "null" : literal);
    }
} 