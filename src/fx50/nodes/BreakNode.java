package fx50.nodes;

import org.bychan.core.dynamic.UserParserCallback;

import java.math.BigDecimal;

import static fx50.ParsingHelper.nextMustBeEnd;

/**
 * Break Node
 */

public class BreakNode implements CalculatorNode {

    public BreakNode(UserParserCallback parser) {
        nextMustBeEnd(parser, "break", false);
    }

    public BigDecimal evaluate() {
        throw new RuntimeException("Breaking");
    }

    public String toString() {
        return "Break";
    }
}
