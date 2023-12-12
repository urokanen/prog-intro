package expression.parser;

import expression.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpressionParser expressionParser = new ExpressionParser();
        ExpressionWithType expression = expressionParser.parse(sc.nextLine());
        System.out.println(expression);
        System.out.println(expression.toMiniString());
    }
}
