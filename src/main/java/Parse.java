import java.util.List;

public class Parse {
  public void parse() {
    List<Token> tokens = GlobalStorage.getTokens();
    for (Token token : tokens) {
      switch (token.type) {
        case "TRUE":
          System.out.println("true");
          break;
        case "FALSE":
          System.out.println("false");
          break;
        case "NIL":
          System.out.println("nil");
          break;
      }
    }
  }
}
