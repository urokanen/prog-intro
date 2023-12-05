package expression;

    public interface ExpressionWithType extends Expression, TripleExpression, BigDecimalExpression {
    int type();
    int priority();
    boolean isRight();
}
