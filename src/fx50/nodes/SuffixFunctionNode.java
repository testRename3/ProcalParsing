package fx50.nodes;

import fx50.CalcMath.SuffixFn;
import org.bychan.core.basic.Lexeme;
import org.bychan.core.dynamic.UserParserCallback;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Suffix Function Node
 */
public class SuffixFunctionNode implements CalculatorNode {
    private final CalculatorNode left;
    private final String functionName;
    private final PrintStream out;
    private Method method;
    private ArrayList<BigDecimal> args = new ArrayList<>();

    public SuffixFunctionNode(CalculatorNode left, UserParserCallback parser, Lexeme lexeme, PrintStream out) {
        this(left, parser, lexeme.getText(), out);
    }

    public SuffixFunctionNode(CalculatorNode input, UserParserCallback parser, String functionName, PrintStream out) {
        this.left = input;
        this.functionName = functionName;
        this.out = out;
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
        args.clear();

        BigDecimal result = new BigDecimal(0);

        BigDecimal leftResult = left.evaluate();

        args.add(leftResult);

        try {
            result = (BigDecimal) method.invoke(this, args);
        } catch (IllegalArgumentException e) {out.println("Runtime Error: IllegalArgumentException");}
        catch (IllegalAccessException e) {out.println("Runtime Error: IllegalAccessException");}
        catch (InvocationTargetException e) {throw new ArithmeticException("Runtime Error: Math Error");}

        return result;
    }

    public String toString() {
        return "((" + left + ")" + functionName + ")";
    }
}
