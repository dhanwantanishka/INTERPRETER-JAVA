package expr;

public abstract class Expr {
    // This is a placeholder. You'll need to fill this out based on your needs.
    public abstract <R> R accept(Visitor<R> visitor);

    public interface Visitor<R> {
        // Placeholder for visitor methods
    }
} 