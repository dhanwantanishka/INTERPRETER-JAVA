public class Main {

  public static void main(String[] args) {
    System.err.println("Logs from your program will appear here!");

    if (args.length < 2) {
      System.err.println("Usage: ./your_program.sh tokenize <filename>");
      System.exit(1);
    }

    String command = args[0];
    String filename = args[1];

    if (!command.equals("tokenize")) {
      System.err.println("Unknown command: " + command);
      System.exit(1);
    }

    // Use Tokenizer instead of Scanner
    Tokenizer tokenizer = new Tokenizer();
    tokenizer.Scanning(filename);
  }
}
