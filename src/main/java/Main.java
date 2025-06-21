import static tool.Reporter.hasError;

import expr.Expr;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import token.Token;
import token.TokenHelper;
import tool.Reporter;

public class Main {

  public static void main(String[] args) {
    //        args = new String[]{"parse", "test.lox"};
    if (args.length < 2) {
      System.err.println("Usage: ./your_program.sh tokenize <filename>");
      System.exit(1);
    }

    String command = args[0];
    String filename = args[1];

    if (!command.equals("tokenize") && !command.equals("parse")) {
      System.err.println("Unknown command: " + command);
      System.exit(1);
    }

    String fileContents = "";
    try {
      fileContents = Files.readString(Path.of(filename));
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
      System.exit(1);
    }

    if (command.equals("tokenize")) {
      List<Token> tokens = TokenHelper.scan(fileContents);
      for (Token token : tokens) {
        Reporter.info(token);
      }
    }

    if (command.equals("parse")) {
      List<Token> tokens = TokenHelper.scan(fileContents);
      Parser parser = new Parser(tokens);
      List<Expr> exprs = parser.parse();
      AstPrinter printer = new AstPrinter();
      exprs.forEach(e -> System.out.println(e.accept(printer)));
    }

    if (hasError) {
      System.exit(65);
    }
  }
}