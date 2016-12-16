package fx50.nodes;

import org.bychan.core.basic.EndToken;
import org.bychan.core.dynamic.UserParserCallback;

import java.math.BigDecimal;

import static fx50.CalculatorHelper.Tokens.*;

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
        if (!parser.nextIs(EndToken.get().getKey()) && !parser.nextIs(colon.getKey()))
            parser.abort("You must end 'WhileEnd' with 'colon' if it does not follow 'END'");
    }

    public BigDecimal evaluate() {
        BigDecimal doResult = new BigDecimal(0);
        while (conditionNode.evaluate().compareTo(BigDecimal.ONE) == 0 && !breaking) {
            doResult = doNode.evaluate();
        }
        return doResult;
    }

    public String toString() {
        return "While " +
                conditionNode.toString() +
                ":\n" + doNode.toString().replaceAll("(?m)^", "  ") +
                "\nWhileEnd";
    }
}
