package expression;

import java.math.BigDecimal;

public class Low implements ExpressionWithType {
    private final ExpressionWithType expression;

    public Low(ExpressionWithType expression) {
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
        return "low(" + expression.toString() + ")";
    }

    private int count(int value) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if (((1 << i) & value) != 0) {
                res = (1 << i);
                break;
            }
        }
        return res;
    }

    @Override
    public int evaluate(int x) {
        return count(expression.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return count(expression.evaluate(x, y, z));
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return expression.evaluate(x).multiply(new BigDecimal(-1));
    }

    @Override
    public String toMiniString() {
        if (expression instanceof Operator) {
            return "low(" + expression.toMiniString() + ')';
        }
        return "low " + expression.toMiniString();
    }
}
