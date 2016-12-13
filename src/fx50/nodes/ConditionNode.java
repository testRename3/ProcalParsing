package fx50.nodes;

import org.bychan.core.basic.EndToken;
import org.bychan.core.dynamic.UserParserCallback;

import java.math.BigDecimal;

import static fx50.CalculatorHelper.Tokens.*;

/**
 * Condition Node
 */
public class ConditionNode implements CalculatorNode {
    private final CalculatorNode ifNode;
    private CalculatorNode thenNode;
    private final CalculatorNode elseNode;

    public ConditionNode(CalculatorNode left, UserParserCallback parser) {
        ifNode = (CalculatorNode) parser.expression(left, 3);

        parser.expectSingleLexeme(conditionThen.getKey());
        thenNode = (CalculatorNode) parser.expression(left);

        if (parser.nextIs(conditionElse.getKey())) {
            parser.expectSingleLexeme(conditionElse.getKey());
            elseNode = (CalculatorNode) parser.expression(left);
        } else
            elseNode = null;

        parser.expectSingleLexeme(conditionIfEnd.getKey());
        if (!parser.nextIs(EndToken.get().getKey()) && !parser.nextIs(colon.getKey()))
            parser.abort("You must end 'IfEnd' with 'colon' if it does not follow 'END'");

        if (parser.nextIs(conditionThen.getKey()))
            parser.abort("Forbidden to use an if-statement as condition");
    }

    public BigDecimal evaluate() {
        if (ifNode.evaluate().compareTo(BigDecimal.ONE) == 0) {
            return thenNode.evaluate();
        } else if (elseNode != null)
            return elseNode.evaluate();
        return new BigDecimal(0);
    }

    public String toString() {
        return "If " +
                ifNode.toString() +
                " Then " + thenNode.toString() +
                (elseNode != null ? " Else " + elseNode.toString() : "") +
                " IfEnd";
    }
}
