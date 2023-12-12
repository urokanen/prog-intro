package expression;

import java.math.BigDecimal;
import java.util.Objects;

public class Variable implements ExpressionWithType {
    private final String title;

    public Variable(String title) {
        this.title = title;
    }

    @Override
    public int type() {
        return 0;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return switch (title) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new IllegalArgumentException("Incorrect argument!");
        };
    }

    @Override
    public String toString() {
        return title;
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
    public String toMiniString() {
        return title;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Variable newVariable = (Variable) object;
        return title.equals(newVariable.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, getClass());
    }
}
