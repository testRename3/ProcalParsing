package fx50.nodes;

import org.bychan.core.basic.EndToken;
import org.bychan.core.dynamic.UserParserCallback;

import java.math.BigDecimal;

import static fx50.CalculatorHelper.Tokens.*;

/**
 * Statement Node
 */
public class StatementNode implements CalculatorNode {
    private final CalculatorNode left;
    private final CalculatorNode right;
    private final boolean isLast;

    public StatementNode(CalculatorNode left, UserParserCallback parser) {
        this.left = left;
        isLast = parser.nextIs(EndToken.get().getKey()) || parser.nextIs(conditionIfEnd.getKey()) || parser.nextIs(conditionElse.getKey());
        if (!isLast)
            this.right = ((CalculatorNode) parser.expression(left));
        else this.right = null;
    }

    public BigDecimal evaluate() {
        if (!isLast) {
            left.evaluate();
            return right.evaluate();
        } else
            return left.evaluate();
    }

    public String toString() {
        if (!isLast)
            return left.toString() + ":" + right.toString();
        else
            return left.toString() + ":";
    }
}
