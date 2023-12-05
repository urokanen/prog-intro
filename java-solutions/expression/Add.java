package expression;

import java.math.BigDecimal;

public class Add extends Operator {
    public Add(ExpressionWithType a, ExpressionWithType b) {
        super(a, b);
    }

    @Override
    public int type() {
        return 2;
    }

    @Override
    protected BigDecimal calculate(BigDecimal value1, BigDecimal value2) {
        return value1.add(value2);
    }

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public boolean isRight() {
        return false;
    }

    @Override
    protected int calculate(int value1, int value2) {
        return value1 + value2;
    }

    @Override
    protected String convert() {
        return " + ";
    }
}
