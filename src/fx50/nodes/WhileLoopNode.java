package fx50.nodes;

import org.bychan.core.basic.EndToken;
import org.bychan.core.dynamic.UserParserCallback;

import java.math.BigDecimal;

import static fx50.CalculatorHelper.Tokens.*;
import static fx50.ParsingHelper.indent;
import static fx50.ParsingHelper.nextMustBeEnd;

/**
 * While Loop Node
 */

//TODO must start in a new statement
//TODO add break
public class WhileLoopNode implements CalculatorNode {
    private final CalculatorNode conditionNode;
    private CalculatorNode doNode;
    private boolean breaking = false;

    public WhileLoopNode(CalculatorNode left, UserParserCallback parser) {
        conditionNode = (CalculatorNode) parser.expression(left, 3);

        parser.expectSingleLexeme(colon.getKey());
        doNode = (CalculatorNode) parser.expression(left);

        parser.expectSingleLexeme(loopWhileEnd.getKey());
        nextMustBeEnd(parser, "WhileEnd", true);
    }

    public BigDecimal evaluate() {
        BigDecimal doResult = new BigDecimal(0);
        while (conditionNode.evaluate().compareTo(BigDecimal.ONE) == 0 && !breaking) {
            doResult = doNode.evaluate();
        }
        return doResult;
    }

    public CalculatorNode breakLoop() {
        breaking = true;
        return doNode;
    }

    public String toString() {
        return "While " +
                conditionNode.toString() +
                ":\n" + indent(doNode.toString()) +
                "\nWhileEnd";
    }
}
