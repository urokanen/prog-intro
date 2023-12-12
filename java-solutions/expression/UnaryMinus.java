package expression;

import java.math.BigDecimal;

public class UnaryMinus implements ExpressionWithType {
    private final ExpressionWithType expression;

    public UnaryMinus(ExpressionWithType expression) {
        this.expression = expression;
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public boolean isRight() {
        return false;
    }

    @Override
    public int type() {
        return 0;
    }

    @Override
    public String toString() {
        return "-(" + expression.toString() + ")";
    }

    @Override
    public int evaluate(int x) {
        return -1 * expression.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -1 * expression.evaluate(x, y, z);
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return expression.evaluate(x).multiply(new BigDecimal(-1));
    }

    @Override
    public String toMiniString() {
        if (expression instanceof Operator) {
            return "-(" + expression.toMiniString() + ')';
        }
        return "- " + expression.toMiniString();
    }
}
