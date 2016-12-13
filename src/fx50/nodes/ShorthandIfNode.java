package fx50.nodes;


import org.bychan.core.basic.EndToken;
import org.bychan.core.dynamic.UserParserCallback;

import java.math.BigDecimal;

import static fx50.CalculatorHelper.Tokens.*;

/**
 * Shorthand If Node
 */
public class ShorthandIfNode implements CalculatorNode {
    private final CalculatorNode ifNode;
    private CalculatorNode thenNode;

    public ShorthandIfNode(CalculatorNode left, UserParserCallback parser) {
        ifNode = left;

        thenNode = (CalculatorNode) parser.expression(left);
    }

    public BigDecimal evaluate() {
        if (ifNode.evaluate().compareTo(BigDecimal.ONE) == 0)
            return thenNode.evaluate();
        return new BigDecimal(0);
    }

    public String toString() {
        return ifNode.toString() + "=>" + thenNode.toString();
    }
}
