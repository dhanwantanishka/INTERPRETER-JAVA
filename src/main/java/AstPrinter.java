import expr.Expr;
import expr.Literal;

public class AstPrinter implements Expr.Visitor<String> {

    String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitLiteralExpr(Literal expr) {
        if (expr.value == null) return "nil";
        if (expr.value instanceof Double) {
            String text = expr.value.toString();
            if (text.endsWith(".0")) {
                text = text.substring(0, text.length() - 2);
            }
            return text;
        }
        return expr.value.toString();
    }
} 