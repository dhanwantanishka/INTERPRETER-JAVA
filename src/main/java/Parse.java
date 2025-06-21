import java.util.List;

public class Parse {
  public void parse() {
    List<Token> tokens = GlobalStorage.getTokens();
    for (Token token : tokens) {
      if ("TRUE".equals(token.type)) {
        System.out.println("true");
      }
    }
  }
}
