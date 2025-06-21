import expr.Expr;
import expr.Literal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import token.Token;
import token.TokenConstant;

/**
 * <br>
 *
 * @author 张成<br>
 * @date 2025年01月03日<br>
 */
public class Parser {

  private final List<Token> tokens;

  private int current = 0;

  public Parser(List<Token> tokens) { this.tokens = tokens; }

  public List<Expr> parse() {

    List<Expr> exprs = new ArrayList<>();

    while (!isEnd()) {

      if (match(TokenConstant.literalTokens)) {
        Token literal = previous();
        Object value = switch (literal.type) {
          case "STRING" -> literal.literal;
          case "NUMBER" -> literal.literal;
          case "TRUE" -> true;
          case "FALSE" -> false;
          case "NIL" -> null;
          default -> null; // Should not be reached
        };
        exprs.add(new Literal(value));
      } else {
        forward();
      }
    }
    return exprs;
  }

  private boolean match(Collection<String> tokenTypes) {
    for (String tokenType : tokenTypes) {
      if (tokenType.equals(cur().type)) {
        forward();
        return true;
      }
    }
    return false;
  }

  private boolean match(String... tokenTypes) {
    List<String> list = Arrays.stream(tokenTypes).toList();
    return match(list);
  }

  private boolean isEnd() { return "EOF".equals(cur().type); }

  private Token cur() { return tokens.get(current); }

  private Token forward() {
    if (!isEnd())
      current++;
    return previous();
  }

  private Token previous() { return tokens.get(current - 1); }
}