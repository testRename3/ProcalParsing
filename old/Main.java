import org.bychan.core.dynamic.Language;
import org.bychan.core.dynamic.LanguageBuilder;
import org.bychan.core.dynamic.TokenDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Language<Double> l = CalculatorHelper.getSimpleCalculatorLanguage();
        l.repl().run();
    }
}

class CalculatorHelper {
    public static Map<String, Double> vars = new HashMap<String, Double>();
    public static String isSetting = "";
    public static Language<Double> getSimpleCalculatorLanguage() throws Exception {
        LanguageBuilder<Double> b = new LanguageBuilder<>("simpleCalc");
        TokenDefinition<Double> name = b.newToken()
                .named("name")
                .matchesPattern("\\$[A-Za-z]+")
                .nud((left, parser, lexeme) -> {
                    String key = lexeme.getText();
                    if (vars.containsKey(key))
                        return vars.get(key);
                    else
                        return 0.0;
                }).led((left, parser, lexeme) -> {
                    String key = lexeme.getText();
                    if (isSetting == "set") {
                        vars.put(lexeme.getText(), left);
                        return left;
                    } else if (isSetting == "input") {
                        System.out.print(key + "? ");
                        Double input = Double.parseDouble((new Scanner(System.in)).nextLine());
                        vars.put(lexeme.getText(), input);
                        return input;
                    } else
                        parser.abort("Unrecognized token!");
                    isSetting = "";
                    return 0.0;
                })
                .build();
        b.newToken().named("whitespace").matchesPattern("\\s+")
                .discardAfterLexing()
                .build();
        TokenDefinition<Double> comma = b.newToken()
                .named("comma")
                .matchesString(",")
                .build();
        TokenDefinition<Double> colon = b.newToken()
                .named("colon")
                .matchesString(":")
                .nud((left, parser, lexeme) -> {
                    return 0.0;
                })
                .led((left, parser, lexeme) -> {
                    return parser.expression(left);
                })
                .build();

        b.newToken().named("set").matchesString("->")
                .led((left, parser, lexeme) -> {
                    isSetting = "set";
                    return left;
                })
                .build();
        b.newToken().named("input").matchesString("?->")
                .nud((left, parser, lexeme) -> {
                    isSetting = "input";
                    return -1.0;
                })
                .build();
        TokenDefinition<Double> rparen = b.newToken()
                .named("rparen")
                .matchesString(")")
                .build();
        b.newToken().named("lparen").matchesString("(")
                .nud((left, parser, lexeme) -> {
                    Double next = parser.expression(left);
                    parser.expectSingleLexeme(rparen.getKey());
                    return next;
                })
                .build();
        b.newToken().named("cosine").matchesString("cos(")
                .nud((left, parser, lexeme) -> {
                    Double next = parser.expression(left);
                    parser.expectSingleLexeme(rparen.getKey());
                    return Math.cos(next);
                })
                .build();
        b.newToken().named("sine").matchesString("sin(")
                .nud((left, parser, lexeme) -> {
                    Double next = parser.expression(left);
                    parser.expectSingleLexeme(rparen.getKey());
                    return Math.sin(next);
                })
                .build();
        b.newToken().named("tangent").matchesString("tan(")
                .nud((left, parser, lexeme) -> {
                    Double next = parser.expression(left);
                    parser.expectSingleLexeme(rparen.getKey());
                    if (next % (Math.PI/2) == 0 && next % Math.PI != 0)
                        parser.abort("Math error: result of angle is undefined");
                    return Math.tan(next%(Math.PI*2));
                })
                .build();
        b.newToken().named("round").matchesString("Rnd(")
                .nud((left, parser, lexeme) -> {
                    Double next = parser.expression(left);
                    parser.expectSingleLexeme(rparen.getKey());
                    return (double) Math.round(next);
                })
                .build();


        b.newToken().named("plus").matchesString("+")
                .led((left, parser, lexeme) -> left + parser.expression(left))
                .build();
        b.newToken().named("minus").matchesString("-")
                .nud((left, parser, lexeme) ->  -parser.expression(left))
                .led((left, parser, lexeme) -> left - parser.expression(left))
                .build();
        b.newToken().named("times").matchesString("*")
                .led((left, parser, lexeme) -> left * parser.expression(left))
                .build();
        b.newToken().named("divide").matchesString("/")
                .led((left, parser, lexeme) -> left / parser.expression(left))
                .build();

        b.newToken().named("power").matchesPattern("\\^")
                .led((left, parser, lexeme) -> Math.pow(left, parser.expression(left)))
                .build();
        b.newToken().named("sqrt").matchesString("sqrt(")
                .nud((left, parser, lexeme) -> {
                    Double next = parser.expression(left);
                    parser.expectSingleLexeme(rparen.getKey());
                    return Math.sqrt(next);
                }).build();
        b.newToken().named("rt").matchesString("xRoot(")
                .led((left, parser, lexeme) -> {
                    Double next = parser.expression(left);
                    parser.expectSingleLexeme(rparen.getKey());
                    return Math.pow(next, 1/left);
                }).build();
        b.newToken().named("percent").matchesString("%")
                .led((left, parser, lexeme) -> 0.01 * left)
                .build();
        b.newToken().named("mod").matchesString("mod")
                .led((left, parser, lexeme) -> left % parser.expression(left))
                .build();
        b.newToken().named("permutation").matchesString("P")
                .led((left, parser, lexeme) -> {
                    Double next = parser.expression(left);
                    if (!isInt(next) || !isInt(left) || next <= 0 || left <= 0)
                        parser.abort("Math error: n and r must be positive non-zero integers");
                    if (next > left)
                        parser.abort("Math error: r cannot be larger than n");
                    return (double) permutation(left, next);
                })
                .build();
        b.newToken().named("combination").matchesString("C")
                .led((left, parser, lexeme) -> {
                    Double next = parser.expression(left);
                    if (!isInt(next) || !isInt(left) || next <= 0 || left <= 0)
                        parser.abort("Math error: n and r must be positive non-zero integers");
                    if (next > left)
                        parser.abort("Math error: r cannot be larger than n");
                    return (double) combination(left, next);
                })
                .build();
        b.newToken().named("factorial").matchesString("!")
                .led((left, parser, lexeme) -> {
                    if (!isInt(left) || !isInt(left) || left <= 0 || left <= 0)
                        parser.abort("Math error: n and r must be positive non-zero integers");
                    return (double) fac(Math.round(left));
                })
                .build();


        b.newToken().named("double").matchesPattern("[0-9\\.]+")
                .nud((left, parser, lexeme) -> Double.parseDouble(lexeme.getText()))
                .build();
        b.newToken().named("pi").matchesString("pi")
                .nud((left, parser, lexeme) -> Math.PI)
                .build();

        return b.completeLanguage();
    }

    static Long fac (Long n) {
        if (n == 0) return 1l;
        return n * fac(n-1l);
    }

    static Double permutation (double n, double r) {
        long x = Math.round(n);
        long y = Math.round(r);
        return (double) fac(x) * fac(x-y);
    }

    static Double combination (double n, double r) {
        long y = Math.round(r);
        return (double) permutation(n, r)/fac(y);
    }

    static boolean isInt (double n) {
        return (n % 1) == 0;
    }
}