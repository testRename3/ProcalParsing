package fx50.nodes;

import fx50.CalcMath.Fn;
import org.bychan.core.basic.Lexeme;
import org.bychan.core.dynamic.UserParserCallback;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;

import static fx50.CalculatorHelper.Tokens.lparen;

/**
 * Function Node
 */
public class FunctionNode implements CalculatorNode {
    private final CalculatorNode right;
    private final UserParserCallback parser;
    private final String functionName;
    private Method method;
    private ArrayList<BigDecimal> args = new ArrayList<>();

    public FunctionNode(CalculatorNode right, UserParserCallback parser, Lexeme lexeme) {
        this.right = right;
        this.parser = parser;
        functionName = lexeme.getText();
        try {
            method = Fn.class.getMethod(functionName, ArrayList.class);
        } catch (SecurityException e) {parser.abort("Security Exception");}
        catch (NoSuchMethodException e) {parser.abort("No Such Method: " + functionName);}
    }

    public FunctionNode(CalculatorNode input, UserParserCallback parser, String functionName) {
        this.right = new NumberNode(new BigDecimal(0));
        this.parser = parser;
        this.functionName = functionName;
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
        BigDecimal result = new BigDecimal(0);

        args.add(right.evaluate());

        try {
            result = (BigDecimal) method.invoke(this, args);
        } catch (IllegalArgumentException e) {throw new RuntimeException("Runtime Error: IllegalArgumentException");}
        catch (IllegalAccessException e) {throw new RuntimeException("Runtime Error: IllegalAccessException");}
        catch (InvocationTargetException e) {throw new ArithmeticException("Math Error");}

        return result;
    }

    public String toString() {
        return "(" + functionName + right + ")";
    }
}
