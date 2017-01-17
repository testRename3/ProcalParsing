package fx50.nodes;

import fx50.API.InputToken;

import java.math.BigDecimal;
import java.util.List;

/**
 * Addition Node
 */
public class AdditionNode implements CalculatorNode {
    private final CalculatorNode left;
    private final CalculatorNode right;

    public AdditionNode(CalculatorNode left, CalculatorNode right) {
        this.left = left;
        this.right = right;
    }

    public BigDecimal evaluate() {
        return left.evaluate().add(right.evaluate());
    }

    public String toString() {
        return "(" + left.toString() + "+" + right.toString() + ")";
    }

    public List<InputToken> toInputTokens() {
        List<InputToken> leftTokens = left.toInputTokens();
        leftTokens.add(new InputToken("+", "+"));
        leftTokens.addAll(right.toInputTokens());
        return leftTokens;
    }
}
