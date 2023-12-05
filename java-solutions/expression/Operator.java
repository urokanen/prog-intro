package expression;

import java.util.Objects;
import java.math.BigDecimal;

public abstract class Operator implements ExpressionWithType {
    private final ExpressionWithType element1;
    private final ExpressionWithType element2;

    public Operator(ExpressionWithType element1, ExpressionWithType element2) {
        this.element1 = element1;
        this.element2 = element2;
    }

    public abstract int type();
    protected abstract int calculate(int value1, int value2);
    protected abstract BigDecimal calculate(BigDecimal value1, BigDecimal value2);
    protected abstract String convert();

    @Override
    public int evaluate(int value) {
        return calculate(element1.evaluate(value), element2.evaluate(value));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(element1.evaluate(x, y, z), element2.evaluate(x, y, z));
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return calculate(element1.evaluate(x), element2.evaluate(x));
    }

    @Override
    public String toString() {
        return "(" + element1 + convert() + element2 + ")";
    }

    @Override
    public String toMiniString() {
        String s1 = element1.toMiniString();
        String s2 = element2.toMiniString();
        if (type() < element1.type()) {
            s1 = '(' + s1 + ')';
        }
        if ((type() < element2.type() ||
                (type() == element2.type() && priority() < element2.priority()))
        || (isRight() && (type() <= element2.type()))) {
            s2 = '(' + s2 + ')';
        }
        return s1 + convert() + s2;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || object.getClass() != getClass()) {
            return false;
        }
        Operator newOperator = (Operator) object;
        return this.element1.equals(newOperator.element1)
                && this.element2.equals(newOperator.element2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element1, element2, getClass());
    }
}
