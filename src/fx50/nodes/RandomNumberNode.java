package fx50.nodes;

import fx50.CalcMath.Fn;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Random Number Node
 */
public class RandomNumberNode implements CalculatorNode {

    public RandomNumberNode() {}

    public BigDecimal evaluate() {
        return Fn.Ran(new ArrayList<BigDecimal>());
    }

    public String toString() {
        return "Ran#";
    }
}
