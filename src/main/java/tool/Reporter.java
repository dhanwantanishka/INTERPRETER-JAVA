package tool;

import token.Token;

public class Reporter {
    public static boolean hasError = false;

    public static void info(Token token) {
        System.out.println(token.toString());
    }

    public static void error(int line, String message) {
        report(line, "", message);
    }

    private static void report(int line, String where, String message) {
        System.err.println(
            "[line " + line + "] Error" + where + ": " + message);
        hasError = true;
    }
} 