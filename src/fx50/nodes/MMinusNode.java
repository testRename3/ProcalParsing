package fx50.nodes;

import fx50.CalculatorHelper;
import org.bychan.core.dynamic.UserParserCallback;

import java.math.BigDecimal;

import static fx50.ParsingHelper.nextMustBeEnd;

/**
 * M+ Node
 */
public class MMinusNode implements CalculatorNode {
    private final CalculatorNode left;

    public MMinusNode(CalculatorNode left, UserParserCallback parser) {
        this.left = left;
        nextMustBeEnd(parser, "M-", true);
    }

    public BigDecimal evaluate() {
        BigDecimal m = CalculatorHelper.VariableMap.getValue("M");
        BigDecimal leftResult = left.evaluate();
        CalculatorHelper.VariableMap.setValue("M", m.subtract(leftResult));
        return leftResult;
    }

    public String toString() {
        return "M-";
    }
}
