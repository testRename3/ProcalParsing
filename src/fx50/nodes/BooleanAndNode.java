package fx50.nodes;

import java.math.BigDecimal;

/**
 * Boolean And Node
 */
public class BooleanAndNode implements BooleanNode {
    private final CalculatorNode left;
    private final CalculatorNode right;

    public BooleanAndNode(CalculatorNode left, CalculatorNode right) {
        this.left = left;
        this.right = right;
    }

    public BigDecimal evaluate() {
        return compare(left, right);
    }

    public String toString() {
        return "(" + left.toString() + ")and(" + right.toString() + ")";
    }

    public BigDecimal compare(CalculatorNode left, CalculatorNode right) {
        boolean leftComp = left.evaluate().compareTo(BigDecimal.ZERO) == 1;
        boolean rightComp = right.evaluate().compareTo(BigDecimal.ZERO) == 1;
        return new BigDecimal(
                leftComp&&rightComp ? 1 : 0
        );
    }
}
