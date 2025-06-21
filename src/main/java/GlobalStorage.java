import java.util.ArrayList;
import java.util.List;

public class GlobalStorage {
  private static final List<String> logs = new ArrayList<>();
  private static final List<Token> tokens = new ArrayList<>();
  public static int exitCode = 0;

  public static void addLog(String log) {
    logs.add(log);
  }

  public static List<String> getLogs() {
    return logs;
  }

  public static void addToken(Token token) {
    tokens.add(token);
  }

  public static List<Token> getTokens() {
    return new ArrayList<>(tokens);
  }

  public static void clear() {
    logs.clear();
    tokens.clear();
    exitCode = 0;
  }
}
