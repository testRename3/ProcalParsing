package fx50.nodes;

import org.bychan.core.basic.Lexeme;
import org.bychan.core.dynamic.UserParserCallback;

import java.math.BigDecimal;

import static fx50.ParsingHelper.nextMustBeSeparator;

/**
 * Break Node
 */

public class BreakNode implements CalculatorNode {

    public BreakNode(CalculatorNode left, UserParserCallback<CalculatorNode> parser, Lexeme<CalculatorNode> lexeme) {
        nextMustBeSeparator(parser, "break");
    }

    public BigDecimal evaluate() {
        throw new RuntimeException("Breaking");
    }

    public String toString() {
        return "Break";
    }
}
