import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Symbol {
  LEFT_PAREN(0),
  RIGHT_PAREN(1),
  LEFT_BRACE(2),
  RIGHT_BRACE(3),
  EOF(-1);

  private final static Map<Integer, Symbol> INDEX_MAP =
      Arrays.stream(Symbol.values())
          .collect(Collectors.toMap(symbol -> symbol.index, symbol -> symbol));

  private final int index;

  Symbol(int index) { this.index = index; }

  public int getIndex() { return index; }

  public static Symbol symbol(int index) {
    return INDEX_MAP.getOrDefault(index, EOF);
  }
}