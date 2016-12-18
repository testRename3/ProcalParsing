package fx50.nodes;

import org.bychan.core.basic.EndToken;
import org.bychan.core.dynamic.UserParserCallback;

import java.math.BigDecimal;

import static fx50.CalculatorHelper.Tokens.*;
import static fx50.ParsingHelper.indent;
import static fx50.ParsingHelper.nextMustBeEnd;

/**
 * Condition Node
 */

//TODO must start in a new statement
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
        nextMustBeEnd(parser, "IfEnd", true);
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
                "Then\n" + indent(thenNode.toString()) +
                (elseNode != null ? "\nElse\n" + indent(elseNode.toString()) : "") +
                "\nIfEnd";
    }
}
