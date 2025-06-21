import java.util.ArrayList;
import java.util.List;

public class GlobalStorage {
  private static final List<String> logs = new ArrayList<>();
  public static int exitCode = 0;

  public static void addLog(String log) {
    logs.add(log);
  }

  public static List<String> getLogs() {
    return logs;
  }
}
