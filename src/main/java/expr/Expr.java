package expr;

public abstract class Expr {
    public abstract <R> R accept(Visitor<R> visitor);

    public interface Visitor<R> {
        R visitLiteralExpr(Literal expr);
    }
} 