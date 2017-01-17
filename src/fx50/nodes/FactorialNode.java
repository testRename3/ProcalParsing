package fx50.nodes;

import fx50.API.InputToken;
import fx50.CalcMath.CalcMath;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Factorial Node
 */
public class FactorialNode implements CalculatorNode {
    private final CalculatorNode left;
    private final PrintStream out;

    public FactorialNode(CalculatorNode left, PrintStream out) {
        this.left = left;
        this.out = out;
    }

    public BigDecimal evaluate() {
        BigDecimal leftResult = left.evaluate();
        if (!CalcMath.isInt(leftResult) || leftResult.compareTo(new BigDecimal(0)) < 0) {
            out.println("Math Error: Number must be non-negative integer");
            return null;
        }
        return CalcMath.factorial(leftResult);
    }

    public String toString() {
        return "(" + left.toString() + "!)";
    }

    public List<InputToken> toInputTokens() {
        return new ArrayList<>(Collections.singletonList(new InputToken("!", "!")));
    }
}
