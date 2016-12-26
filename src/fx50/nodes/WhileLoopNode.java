package fx50.nodes;

import fx50.CalculatorHelper;
import org.bychan.core.dynamic.UserParserCallback;

import java.math.BigDecimal;

import static fx50.CalculatorHelper.Tokens.*;
import static fx50.ParsingHelper.indent;
import static fx50.ParsingHelper.nextMustBeEnd;

/**
 * While Loop Node
 */

//TODO must start in a new statement
public class WhileLoopNode implements CalculatorNode {
    private final CalculatorNode conditionNode;
    private CalculatorNode doNode;

    public WhileLoopNode(CalculatorNode left, UserParserCallback parser) {
        conditionNode = (CalculatorNode) parser.expression(left, 3);

        parser.expectSingleLexeme(colon.getKey());
        doNode = (CalculatorNode) parser.expression(left);

        parser.expectSingleLexeme(loopWhileEnd.getKey());
        nextMustBeEnd(parser, "WhileEnd", true);
    }

    public BigDecimal evaluate() {
        BigDecimal doResult = new BigDecimal(0);
        try {
            while (conditionNode.evaluate().compareTo(BigDecimal.ONE) == 0 && !CalculatorHelper.breakingLoop) {
                doResult = doNode.evaluate();
            }
        } catch (RuntimeException e) {
            if (!e.getMessage().equals("Breaking"))
                throw e;
        }
        CalculatorHelper.breakingLoop = false;
        return doResult;
    }

    public String toString() {
        return "While " +
                conditionNode.toString() +
                ":\n" + indent(doNode.toString()) +
                "\nWhileEnd";
    }
}
