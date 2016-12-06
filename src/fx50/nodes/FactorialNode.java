package fx50.nodes;

import fx50.CalcMath.CalcMath;

import java.math.BigDecimal;

/**
 * Factorial Node
 */
public class FactorialNode implements CalculatorNode {
    private final CalculatorNode left;

    public FactorialNode(CalculatorNode left) {
        this.left = left;
    }

    public BigDecimal evaluate() {
        BigDecimal leftResult = left.evaluate();
        if (!CalcMath.isInt(leftResult) || leftResult.compareTo(new BigDecimal(0)) < 0) {
            System.out.println("Math Error: Number must be non-negative integer");
            return null;
        }
        return CalcMath.factorial(leftResult);
    }

    public String toString() {
        return "(" + left.toString() + "!)";
    }
}
