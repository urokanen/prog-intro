package expression.parser;

import expression.*;

public class ExpressionParser extends BaseParser implements TripleParser {
    @Override
    public ExpressionWithType parse(String expression) {
        setSource(new StringSource(expression));
        return parse();
    }

    private ExpressionWithType parse() {
        return parseOr();
    }

    private ExpressionWithType parseOr() {
        ExpressionWithType parsed = parseXor();
        while (true) {
            skipWhitespace();
            if (take('|')) {
                parsed = new Or(parsed, parseXor());
            } else {
                return parsed;
            }
        }
    }

    private ExpressionWithType parseXor() {
        ExpressionWithType parsed = parseAnd();
        while (true) {
            skipWhitespace();
            if (take('^')) {
                parsed = new Xor(parsed, parseAnd());
            } else {
                return parsed;
            }
        }
    }

    private ExpressionWithType parseAnd() {
        ExpressionWithType parsed = parseAddSubtract();
        while (true) {
            skipWhitespace();
            if (take('&')) {
                parsed = new And(parsed, parseAddSubtract());
            } else {
                return parsed;
            }
        }
    }

    private ExpressionWithType parseAddSubtract() {
        ExpressionWithType parsed = parseMultiplyDivide();
        while (true) {
            skipWhitespace();
            if (take('+')) {
                parsed = new Add(parsed, parseMultiplyDivide());
            } else if (take('-')) {
                parsed = new Subtract(parsed, parseMultiplyDivide());
            } else {
                return parsed;
            }
        }
    }

    private ExpressionWithType parseMultiplyDivide() {
        ExpressionWithType parsed = parseOther();
        while (true) {
            skipWhitespace();
            if (take('*')) {
                parsed = new Multiply(parsed, parseOther());
            } else if (take('/')) {
                parsed = new Divide(parsed, parseOther());
            } else {
                return parsed;
            }
        }
    }

    private ExpressionWithType parseOther() {
        skipWhitespace();
        if (take('(')) {
            ExpressionWithType result = parse();
            expect(')');
            return result;
        } else if (between('0', '9')) {
            return parseConst(1);
        } else if (between('x', 'z')) {
            return parseVariable();
        } else if (take('-')) {
            if (between('1', '9')) {
                return parseConst(-1);
            }
            skipWhitespace();
            if (take('(')) {
                ExpressionWithType result = parse();
                expect(')');
                return new UnaryMinus(result);
            } else if (between('0', '9')) {
                return new UnaryMinus(parseConst(1));
            } else if (between('x', 'z')) {
                return new UnaryMinus(parseVariable());
            } else if (test('-')) {
                return new UnaryMinus(parseOther());
            } else if (take('l')) {
                if (take('1')) {
                    return new UnaryMinus(new OnesLeft(parseOnes()));
                } else {
                    expect("ow");
                    return new UnaryMinus(new Low(parseOnes()));
                }
            } else if (take('t')) {
                expect('1');
                return new UnaryMinus(new OnesRight(parseOnes()));
            } else if (take('h')) {
                expect("igh");
                return new UnaryMinus(new High(parseOnes()));
            } else {
                return new UnaryMinus(parse());
            }
        } else if (take('l')) {
            if (take('1')) {
                return new OnesLeft(parseOnes());
            } else {
                expect("ow");
                return new Low(parseOnes());
            }
        } else if (take('t')) {
            expect('1');
            return new OnesRight(parseOnes());
        } else if (take('h')) {
            expect("igh");
            return new High(parseOnes());
        }
        else {
            System.err.println(ch);
            throw error("Incorrect data!");
        }
    }

    private ExpressionWithType parseOnes() {
        skipWhitespace();
        ExpressionWithType result;
        if (between('0', '9')) {
            result = parseConst(1);
        } else if (between('x', 'z')) {
            result = parseVariable();
        } else {
            result = parseOther();
        }
        return result;
    }

    private ExpressionWithType parseVariable() {
        skipWhitespace();
        char name = ch;
        take();
        return new Variable(String.valueOf(name));
    }

    private ExpressionWithType parseConst(int value) {
        skipWhitespace();
        long ans = 0;
        while (between('0', '9')) {
            ans = (ans * 10) + (ch - '0');
            take();
        }
        return new Const((int)(ans * value));
    }

    private void skipWhitespace() {
        while (Character.isWhitespace(ch)) {
            take();
        }
    }
}
