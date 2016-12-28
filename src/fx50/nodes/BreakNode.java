package fx50.nodes;

import org.bychan.core.dynamic.UserParserCallback;

import java.math.BigDecimal;

import static fx50.ParsingHelper.nextMustBeSeparator;

/**
 * Break Node
 */

public class BreakNode implements CalculatorNode {

    public BreakNode(UserParserCallback parser) {
        nextMustBeSeparator(parser, "break");
    }

    public BigDecimal evaluate() {
        throw new RuntimeException("Breaking");
    }

    public String toString() {
        return "Break";
    }
}
