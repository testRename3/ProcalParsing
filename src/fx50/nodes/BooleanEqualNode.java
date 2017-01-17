package fx50.nodes;

import fx50.API.InputToken;

import java.math.BigDecimal;
import java.util.List;

/**
 * Boolean Equals Node
 */
public class BooleanEqualNode implements BooleanNode {
    private final CalculatorNode left;
    private final CalculatorNode right;

    public BooleanEqualNode(CalculatorNode left, CalculatorNode right) {
        this.left = left;
        this.right = right;
    }

    public BigDecimal evaluate() {
        return compare(left, right);
    }

    public String toString() {
        return "(" + left.toString() + ")==(" + right.toString() + ")";
    }

    public List<InputToken> toInputTokens() {
        List<InputToken> leftTokens = left.toInputTokens();
        leftTokens.add(new InputToken("=", "="));
        leftTokens.addAll(right.toInputTokens());
        return leftTokens;
    }

    public BigDecimal compare(CalculatorNode left, CalculatorNode right) {
        return new BigDecimal(left.evaluate().compareTo(right.evaluate()) == 0 ? 1 : 0);
    }
}
