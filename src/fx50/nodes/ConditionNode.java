package fx50.nodes;


import org.bychan.core.dynamic.UserParserCallback;

import java.math.BigDecimal;

import static fx50.CalculatorHelper.Tokens.*;

/**
 * Subtraction Node
 */
public class ConditionNode implements CalculatorNode {
    private final CalculatorNode ifNode;
    private CalculatorNode thenNode;
    private final CalculatorNode elseNode;

    public ConditionNode(CalculatorNode left, UserParserCallback parser) {
        ifNode = (CalculatorNode) parser.expression(left);

        parser.expectSingleLexeme(conditionThen.getKey());
        thenNode = (CalculatorNode) parser.expression(left);

        if (parser.nextIs(conditionElse.getKey())) {
            parser.expectSingleLexeme(conditionElse.getKey());
            elseNode = (CalculatorNode) parser.expression(left);
        } else
            elseNode = null;

        parser.expectSingleLexeme(conditionIfEnd.getKey());
    }

    public BigDecimal evaluate() {
        if (ifNode.evaluate().compareTo(BigDecimal.ONE) == 0) {
            return thenNode.evaluate();
        } else if (elseNode != null)
            return elseNode.evaluate();
        return new BigDecimal(0);
    }

    public String toString() {
        return "(IF " +
                ifNode.toString() +
                " THEN " + thenNode.toString() +
                (elseNode != null ? " ELSE " + elseNode.toString() : "") +
                " IFEND)";
    }
}
