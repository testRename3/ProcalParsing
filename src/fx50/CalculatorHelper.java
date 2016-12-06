package fx50;

import fx50.CalcMath.CalcMath;
import fx50.nodes.*;
import org.bychan.core.dynamic.Language;
import org.bychan.core.dynamic.LanguageBuilder;
import org.bychan.core.dynamic.TokenDefinitionBuilder;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static fx50.CalcMath.CalcMath.precision;

/**
 * CalculatorHelper.
 */
public class CalculatorHelper {

    public static class VariableMap {
        private static Map<String, BigDecimal> storage = new HashMap<>();
        public static BigDecimal getValue(String name) {
            if (storage.containsKey(name))
                return storage.get(name);
            else
                return setValue(name, new BigDecimal(0));
        }

        public static BigDecimal setValue(String name, BigDecimal value) {
            storage.put(name, value);
            return value;
        }

        public static void clearMemory() {
            storage.clear();
        }
    }


    public static LanguageBuilder<CalculatorNode> l = new LanguageBuilder<>("Fx-50F ULTRA");

    public static class Tokens {
        //TODO powerNode
        //TODO xRootNode

        public static TokenDefinitionBuilder<CalculatorNode> colon = l.newToken()
                .named("colon").matchesString(":")
                .led((left, parser, lexeme) -> new StatementNode(left, parser));

        public static TokenDefinitionBuilder<CalculatorNode> negate = l.newToken()
                .named("negate").matchesString("(-)")
                .nud((left, parser, lexeme) -> new NegationNode(parser.expression(left)));

        public static TokenDefinitionBuilder<CalculatorNode> rparen = l.newToken()
                .named("rparen").matchesString(")");

        public static TokenDefinitionBuilder<CalculatorNode> lparen = l.newToken()
                .named("lparen").matchesString("(")
                .nud((left, parser, lexeme) -> new ParenthesisNode(left, parser))
                .led((left, parser, lexeme) -> {
                    //Hidden Multiplication
                    CalculatorNode trailingNode = parser.expression(left);
                    parser.expectSingleLexeme(rparen.getKey());
                    return new MultiplicationNode(left, trailingNode);
                });

        public static TokenDefinitionBuilder<CalculatorNode> permutation = l.newToken()
                .named("permutation").matchesString("P")
                .led((left, parser, lexeme) -> new PermutationNode(left, parser.expression(left)));

        public static TokenDefinitionBuilder<CalculatorNode> combination = l.newToken()
                .named("combination").matchesString("C")
                .led((left, parser, lexeme) -> new CombinationNode(left, parser.expression(left)));

        public static TokenDefinitionBuilder<CalculatorNode> randomNumber = l.newToken()
                .named("randomNumber").matchesString("Ran#")
                .nud((left, parser, lexeme) -> new RandomNumberNode());

        public static TokenDefinitionBuilder<CalculatorNode> variable = l.newToken()
                .named("variable").matchesPattern("\\$[A-Za-z]+")
                .nud((left, parser, lexeme) -> new VariableNode(lexeme))
                //hidden multiplication
                .led((left, parser, lexeme) -> new MultiplicationNode(left, new VariableNode(lexeme)));

        public static TokenDefinitionBuilder<CalculatorNode> constant = l.newToken()
                .named("constant").matchesPattern("&[A-Za-z]+")
                .nud((left, parser, lexeme) -> new ConstantNode(parser, lexeme))
                //hidden multiplication
                .led((left, parser, lexeme) -> new MultiplicationNode(left, new ConstantNode(parser, lexeme)));

        public static TokenDefinitionBuilder<CalculatorNode> number = l.newToken()
                .named("number").matchesPattern("[0-9\\.]+")
                .nud((left, parser, lexeme) -> new NumberNode(new BigDecimal(lexeme.getText())))
                //hidden multiplication (imperfect, will multiply after a constant)
                .led((left, parser, lexeme) -> new MultiplicationNode(left, new NumberNode(new BigDecimal(lexeme.getText()))));

        public static TokenDefinitionBuilder<CalculatorNode> set = l.newToken()
                .named("set").matchesString("->")
                .led((left, parser, lexeme) -> {
                    if (!parser.nextIs(variable.getKey()))
                        parser.abort("Invalid assignment RHS. Expected a variable name");
                    return new AssignmentNode(left, (VariableNode) parser.expression(left));
                });

        public static TokenDefinitionBuilder<CalculatorNode> input = l.newToken()
                .named("input").matchesString("?")
                .nud((left, parser, lexeme) -> {
                    parser.expectSingleLexeme(set.getKey());
                    if (!parser.nextIs(variable.getKey()))
                        parser.abort("Invalid assignment RHS. Expected a variable name");
                    VariableNode variableNode = (VariableNode) parser.expression(left);
                    return new AssignmentNode(new InputNumberNode(System.in, System.out, variableNode.getName()), variableNode);
                });

        public static TokenDefinitionBuilder<CalculatorNode> display = l.newToken()
                .named("display").matchesString("display")
                .led((left, parser, lexeme) -> {
                    System.out.println(left.evaluate().setScale(10, BigDecimal.ROUND_HALF_UP).toPlainString());
                    return new StatementNode(left, parser);
                });

        public static TokenDefinitionBuilder<CalculatorNode> factorial = l.newToken()
                .named("factorial").matchesString("!")
                .led((left, parser, lexeme) -> new FactorialNode(left));

        public static TokenDefinitionBuilder<CalculatorNode> modulo = l.newToken()
                .named("modulo").matchesString("mod")
                .nud((left, parser, lexeme) -> parser.expression(left))
                .led((left, parser, lexeme) -> new ModulusNode(left, parser.expression(left)));

        public static TokenDefinitionBuilder<CalculatorNode> multiply = l.newToken()
                .named("multiply").matchesString("*")
                .nud((left, parser, lexeme) -> parser.expression(left))
                .led((left, parser, lexeme) -> new MultiplicationNode(left, parser.expression(left)));

        public static TokenDefinitionBuilder<CalculatorNode> divide = l.newToken()
                .named("divide").matchesString("/")
                .nud((left, parser, lexeme) -> parser.expression(left))
                .led((left, parser, lexeme) -> new DivisionNode(left, parser.expression(left)));

        public static TokenDefinitionBuilder<CalculatorNode> minus = l.newToken()
                .named("minus").matchesString("-")
                .nud((left, parser, lexeme) -> new NegationNode(parser.expression(left)))
                .led((left, parser, lexeme) -> new SubtractionNode(left, parser.expression(left)));

        public static TokenDefinitionBuilder<CalculatorNode> plus = l.newToken()
                .named("plus").matchesString("+")
                .nud((left, parser, lexeme) -> parser.expression(left))
                .led((left, parser, lexeme) -> new AdditionNode(left, parser.expression(left)));

        public static TokenDefinitionBuilder<CalculatorNode> exponential = l.newToken()
                .named("exponential").matchesPattern("E-*\\d\\d?(?!\\d)")
                .nud((left, parser, lexeme) -> new ExponentialNode(lexeme))
                .led((left, parser, lexeme) -> new MultiplicationNode(left, new ExponentialNode(lexeme)));

        public static TokenDefinitionBuilder<CalculatorNode> function = l.newToken()
                .named("function").matchesPattern("[A-Za-z][A-Za-z0-9]+(?=\\()")
                .nud((left, parser, lexeme) -> new FunctionNode(parser.expression(left), parser, lexeme));

        public static TokenDefinitionBuilder<CalculatorNode> suffixFunction = l.newToken()
                .named("suffixFunction").matchesPattern("[A-Za-z][A-Za-z0-9]+")
                .led((left, parser, lexeme) -> new SuffixFunctionNode(left, parser, lexeme));

        public static TokenDefinitionBuilder<CalculatorNode> booleanNotEqual = l.newToken()
                .named("booleanNotEqual").matchesString("!=")
                .led((left, parser, lexeme) -> new BooleanNotEqualNode(left, parser.expression(left)));

        public static TokenDefinitionBuilder<CalculatorNode> booleanEqual = l.newToken()
                .named("booleanEqual").matchesString("==")
                .led((left, parser, lexeme) -> new BooleanEqualNode(left, parser.expression(left)));

        public static TokenDefinitionBuilder<CalculatorNode> booleanLesserThanOrEquals = l.newToken()
                .named("booleanLesserThanOrEquals").matchesString("<=")
                .led((left, parser, lexeme) -> new BooleanLesserThanOrEqualNode(left, parser.expression(left)));

        public static TokenDefinitionBuilder<CalculatorNode> booleanLesserThan =l.newToken()
                .named("booleanLesserThan").matchesString("<")
                .led((left, parser, lexeme) -> new BooleanLesserThanNode(left, parser.expression(left)));

        public static TokenDefinitionBuilder<CalculatorNode> booleanGreaterThanOrEquals = l.newToken()
                .named("booleanGreaterThanOrEquals").matchesString(">=")
                .led((left, parser, lexeme) -> new BooleanGreaterThanOrEqualNode(left, parser.expression(left)));

        public static TokenDefinitionBuilder<CalculatorNode> booleanGreaterThan = l.newToken()
                .named("booleanGreaterThan").matchesString(">")
                .led((left, parser, lexeme) -> new BooleanGreaterThanNode(left, parser.expression(left)));

        public static TokenDefinitionBuilder<CalculatorNode> booleanAnd = l.newToken()
                .named("and").matchesString("and")
                .led((left, parser, lexeme) -> new BooleanAndNode(left, parser.expression(left)));

        public static TokenDefinitionBuilder<CalculatorNode> booleanOr = l.newToken()
                .named("or").matchesString("or")
                .led((left, parser, lexeme) -> new BooleanOrNode(left, parser.expression(left)));

        public static TokenDefinitionBuilder<CalculatorNode> booleanXor = l.newToken()
                .named("xor").matchesString("xor")
                .led((left, parser, lexeme) -> new BooleanXorNode(left, parser.expression(left)));

        public static TokenDefinitionBuilder<CalculatorNode> booleanXnor = l.newToken()
                .named("xnor").matchesString("xnor")
                .led((left, parser, lexeme) -> new BooleanXnorNode(left, parser.expression(left)));

        public static TokenDefinitionBuilder<CalculatorNode> conditionIf = l.newToken()
                .named("if").matchesString("If")
                .nud((left, parser, lexeme) -> new ConditionNode(left, parser));

        public static TokenDefinitionBuilder<CalculatorNode> conditionThen = l.newToken()
                .named("then").matchesString("Then");

        public static TokenDefinitionBuilder<CalculatorNode> conditionElse = l.newToken()
                .named("else").matchesString("Else");

        public static TokenDefinitionBuilder<CalculatorNode> conditionIfEnd = l.newToken()
                .named("ifEnd").matchesString("IfEnd");

        public static TokenDefinitionBuilder<CalculatorNode> comma = l.newToken()
                .named("comma").matchesString(",");
    }

    public static Language<CalculatorNode> getSimpleCalculatorLanguage() throws Exception {
        l.newToken().named("help").matchesString("help")
                .nud((left, parser, lexeme) -> {
                    Scanner scanner = new Scanner(System.in);
                    while (true) {
                        System.out.println("" +
                                "HELP\n" +
                                "    prefix functions: <name>(<args>)\n" +
                                "    suffix functions: <args> <name>\n" +
                                "    variables: $<name>\n" +
                                "    constants: &<name>\n\n" +
                                "    <1> - constants\n" +
                                "    <2> - programs\n\n" +
                                "- enter empty line to exit help -"
                        );
                        System.out.print("help:");
                        String input = scanner.nextLine();
                        if (input.equals("1")) {
                            System.out.println("CONSTANTS");
                            CalcMath.listAllConstants();
                        } else if (input.equals("2")) {
                            System.out.println("" +
                                    "PROGRAMS\n" +
                                    "Using a program\n" +
                                    "    prog<program number>\n" +
                                    "        This will run the program stored in a file with the same name as the command.\n" +
                                    "        'program number' is a positive integer\n\n" +
                                    "Saving a program\n" +
                                    "    Create a file named prog<program number> and save it in the same directory as the .jar file.\n\n" +
                                    "- enter empty line to go back -"
                            );
                            System.out.print("help>programs:");
                            scanner.nextLine();
                        } else
                            break;
                    }
                    throw new RuntimeException("--- Exit help ---");
                }).build();

        l.newToken().named("clearMemory").matchesString("ClrMemory")
                .nud((left, parser, lexeme) -> {
                    VariableMap.clearMemory();
                    throw new RuntimeException("Memory Cleared!");
                }).build();

        l.newToken().named("whitespace").matchesPattern("\\s+").discardAfterLexing().build();

        l.newToken().named("comment").matchesPattern("\\/\\*\\S*?\\*\\/").discardAfterLexing().build();

        Tokens.display.leftBindingPower(2).build();

        Tokens.colon.leftBindingPower(2).build();

        Tokens.conditionIfEnd.leftBindingPower(3).build();
        Tokens.conditionIf.leftBindingPower(3).build();
        Tokens.conditionThen.leftBindingPower(3).build();
        Tokens.conditionElse.leftBindingPower(3).build();
        Tokens.set.leftBindingPower(2).build();

        Tokens.negate.leftBindingPower(13).build();

        Tokens.rparen.leftBindingPower(4).build();
        Tokens.lparen.leftBindingPower(4).build();

        Tokens.comma.leftBindingPower(5).build();

        Tokens.booleanXor.leftBindingPower(5).build();
        Tokens.booleanXnor.leftBindingPower(5).build();
        Tokens.booleanOr.leftBindingPower(5).build();

        Tokens.booleanAnd.leftBindingPower(6).build();

        Tokens.booleanEqual.leftBindingPower(8).build();
        Tokens.booleanNotEqual.leftBindingPower(8).build();
        Tokens.booleanLesserThanOrEquals.leftBindingPower(8).build();
        Tokens.booleanLesserThan.leftBindingPower(8).build();
        Tokens.booleanGreaterThanOrEquals.leftBindingPower(8).build();
        Tokens.booleanGreaterThan.leftBindingPower(8).build();

        Tokens.plus.leftBindingPower(9).build();
        Tokens.minus.leftBindingPower(9).build();

        Tokens.multiply.leftBindingPower(10).build();
        Tokens.divide.leftBindingPower(10).build();
        Tokens.modulo.leftBindingPower(10).build();

        Tokens.permutation.leftBindingPower(11).build();
        Tokens.combination.leftBindingPower(11).build();

        Tokens.factorial.leftBindingPower(14).build();

        Tokens.exponential.leftBindingPower(16).build();

        Tokens.function.leftBindingPower(15).build();
        Tokens.suffixFunction.leftBindingPower(15).build();

        Tokens.number.leftBindingPower(16).build();
        Tokens.variable.leftBindingPower(16).build();
        Tokens.constant.leftBindingPower(16).build();
        Tokens.randomNumber.leftBindingPower(16).build();
        Tokens.input.leftBindingPower(16).build();

        return l.completeLanguage();
    }
}
