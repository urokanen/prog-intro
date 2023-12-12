package expression;

import java.math.BigDecimal;

public class Xor extends Operator {
    public Xor(ExpressionWithType a, ExpressionWithType b) {
        super(a, b);
    }

    @Override
    public int type() {
        return 4;
    }

    @Override
    protected BigDecimal calculate(BigDecimal value1, BigDecimal value2) {
        throw new IllegalArgumentException("Incorrect operation");
    }

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public boolean isRight() {
        return false;
    }

    @Override
    protected int calculate(int value1, int value2) {
        return value1 ^ value2;
    }

    @Override
    protected String convert() {
        return " ^ ";
    }
}
