import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2 || !args[0].equals("tokenize")) {
            System.err.println("Usage: java Main tokenize <source_file>");
            System.exit(1);
        }

        String sourceFile = args[1];
        try {
            String source = new String(Files.readAllBytes(Paths.get(sourceFile)));
            // TODO: Implement tokenization logic
            // For now, just print the source to verify file reading works
            System.out.println(source);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }
    }
} 