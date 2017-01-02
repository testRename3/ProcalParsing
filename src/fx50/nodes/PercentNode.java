package fx50.nodes;

import org.bychan.core.basic.Lexeme;
import org.bychan.core.dynamic.UserParserCallback;

import java.math.BigDecimal;

/**
 * Subtraction Node
 */
public class PercentNode implements CalculatorNode {
    private final CalculatorNode left;

    public PercentNode(CalculatorNode left, UserParserCallback<CalculatorNode> parser, Lexeme<CalculatorNode> lexeme) {
        this.left = left;
    }

    public BigDecimal evaluate() {
        return left.evaluate().multiply(new BigDecimal(0.01));
    }

    public String toString() {
        return "(" + left.toString() + "%)";
    }
}
