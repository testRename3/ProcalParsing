package fx50.nodes;

import fx50.CalcMath.Fn;
import org.bychan.core.basic.Lexeme;
import org.bychan.core.dynamic.UserParserCallback;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static fx50.CalculatorHelper.Tokens.comma;
import static fx50.CalculatorHelper.Tokens.lparen;
import static fx50.CalculatorHelper.Tokens.rparen;
import static fx50.ParsingHelper.nextIsStatementEnd;

/**
 * Function Node
 */
public class FunctionNode implements CalculatorNode {
    private ArrayList<CalculatorNode> argNodes = new ArrayList<>();
    private final String functionName;
    private Method method;
    private ArrayList<BigDecimal> args = new ArrayList<>();

    public FunctionNode(CalculatorNode left, UserParserCallback<CalculatorNode> parser, Lexeme lexeme) {
        argNodes.clear();
        parser.expectSingleLexeme(lparen.getKey());
        argNodes.add(parser.expression(left, 5));
        while(parser.nextIs(comma.getKey())) {
            //Has next argument
            parser.expectSingleLexeme(comma.getKey());
            argNodes.add(parser.expression(left, 5));
        }
        if (!nextIsStatementEnd(parser))
            parser.expectSingleLexeme(rparen.getKey());
        this.functionName = lexeme.getText();
        getFunction(parser);
    }

    public FunctionNode(CalculatorNode input, UserParserCallback parser, String functionName) {
        argNodes.add(input);
        this.functionName = functionName;
        getFunction(parser);
    }

    private void getFunction(UserParserCallback parser) {
        try {
            method = Fn.class.getMethod(functionName, ArrayList.class);
        } catch (SecurityException e) {parser.abort("Security Exception");}
        catch (NoSuchMethodException e) {parser.abort("No Such Method: " + functionName);}
    }

    public String getFunctionName() {
        return functionName;
    }

    public ArrayList<BigDecimal> getArgs() {
        return args;
    }

    public BigDecimal evaluate() {
        BigDecimal result;

        args.clear();

        for(CalculatorNode argNode: argNodes) {
            args.add(argNode.evaluate());
        }

        try {
            result = (BigDecimal) method.invoke(this, args);
        } catch (IllegalArgumentException e) {throw new RuntimeException("Runtime Error: IllegalArgumentException");}
        catch (IllegalAccessException e) {throw new RuntimeException("Runtime Error: IllegalAccessException");}
        catch (InvocationTargetException e) {e.printStackTrace(System.out); throw new ArithmeticException("Math Error");}

        return result;
    }

    public String toString() {
        return  functionName + "(" + argNodes.stream().map(Object::toString).collect(Collectors.joining(",")) + ")";
    }
}
