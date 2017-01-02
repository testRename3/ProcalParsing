package fx50.nodes;

import org.bychan.core.basic.Lexeme;
import org.bychan.core.dynamic.UserParserCallback;

import java.math.BigDecimal;

/**
 * Boolean Not Node
 */
public class BooleanNotNode implements BooleanNode {
    private final CalculatorNode right;

    public BooleanNotNode(CalculatorNode left, UserParserCallback<CalculatorNode> parser, Lexeme<CalculatorNode> lexeme) {
        this.right = parser.expression(left);
    }

    public BigDecimal evaluate() {
        return new BigDecimal(right.evaluate().compareTo(BigDecimal.ZERO) != 1 ? 1 : 0);
    }

    public String toString() {
        return "not(" + right.toString() + ")";
    }

    public BigDecimal compare(CalculatorNode left, CalculatorNode right) {
        return new BigDecimal(0);
    }
}
