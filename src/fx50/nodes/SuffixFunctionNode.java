package fx50.nodes;

import fx50.CalcMath.SuffixFn;
import org.bychan.core.basic.Lexeme;
import org.bychan.core.dynamic.UserParserCallback;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Suffix Function Node
 */
public class SuffixFunctionNode implements CalculatorNode {
    private final CalculatorNode right;
    private final UserParserCallback parser;
    private final String functionName;
    private Method method;
    private ArrayList<BigDecimal> args = new ArrayList<>();

    public SuffixFunctionNode(CalculatorNode right, UserParserCallback parser, Lexeme lexeme) {
        this.right = right;
        this.parser = parser;
        functionName = lexeme.getText();
        try {
            method = SuffixFn.class.getMethod(functionName, ArrayList.class);
        } catch (SecurityException e) {parser.abort("Security Exception");}
        catch (NoSuchMethodException e) {parser.abort("No Such Method: " + functionName);}
    }

    public SuffixFunctionNode(CalculatorNode input, UserParserCallback parser, String functionName) {
        this.right = new NumberNode(new BigDecimal(0));
        this.parser = parser;
        this.functionName = functionName;
        try {
            method = SuffixFn.class.getMethod(functionName, ArrayList.class);
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
        } catch (IllegalArgumentException e) {System.out.println("Runtime Error: IllegalArgumentException");}
        catch (IllegalAccessException e) {System.out.println("Runtime Error: IllegalAccessException");}
        catch (InvocationTargetException e) {throw new ArithmeticException("Runtime Error: Math Error");}

        return result;
    }

    public String toString() {
        return "((" + right + ")" + functionName + ")";
    }
}
