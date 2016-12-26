package fx50.nodes;

import fx50.CalculatorHelper;
import org.bychan.core.basic.EndToken;
import org.bychan.core.dynamic.UserParserCallback;

import java.math.BigDecimal;

import static fx50.CalculatorHelper.Tokens.colon;
import static fx50.ParsingHelper.nextMustBeEnd;

/**
 * M+ Node
 */
public class MPlusNode implements CalculatorNode {
    private final CalculatorNode left;

    public MPlusNode(CalculatorNode left, UserParserCallback parser) {
        this.left = left;
        nextMustBeEnd(parser, "M+", true);
    }

    public BigDecimal evaluate() {
        BigDecimal m = CalculatorHelper.VariableMap.getValue("M");
        BigDecimal leftResult = left.evaluate();
        CalculatorHelper.VariableMap.setValue("M", m.add(left.evaluate()));
        return leftResult;
    }

    public String toString() {
        return "(" + left.toString() + ") M+";
    }
}
