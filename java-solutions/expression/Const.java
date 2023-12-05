package expression;

import java.math.BigDecimal;
import java.util.Objects;

public class Const implements ExpressionWithType {
    private final BigDecimal value;

    public Const(int value) {
        this.value = new BigDecimal(value);
    }

    public Const(BigDecimal value) {
        this.value = value;
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
    public String toMiniString() {
        return String.valueOf(value);
    }

    @Override
    public int evaluate(int x) {
        return value.intValue();
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }

    public String toString() {
        return String.valueOf(value);
    }

    public int hashCode() {
        return Objects.hash(value, getClass());
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Const newConst = (Const) object;
        return Objects.equals(this.value, newConst.value);
    }
}
