package expr;

public class Literal extends Expr {
    public final Object value;

    public Literal(Object value) {
        this.value = value;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        // This is a placeholder for the visitor pattern.
        return null;
    }
} 