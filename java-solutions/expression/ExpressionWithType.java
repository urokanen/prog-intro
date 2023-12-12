package expression;

import expression.BigDecimalExpression;
import expression.Expression;
import expression.TripleExpression;

public interface ExpressionWithType extends Expression, TripleExpression, BigDecimalExpression {
    int type();
    int priority();
    boolean isRight();
}
