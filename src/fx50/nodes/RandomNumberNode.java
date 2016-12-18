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
        ArrayList<BigDecimal> args = new ArrayList<>();
        args.add(new BigDecimal(1));
        return Fn.Ran(args);
    }

    public String toString() {
        return "Ran#";
    }
}
