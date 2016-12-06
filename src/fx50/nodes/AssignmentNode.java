package fx50.nodes;

import fx50.CalculatorHelper.VariableMap;
import org.bychan.core.basic.Lexeme;
import org.bychan.core.dynamic.UserParserCallback;

import java.math.BigDecimal;

/**
 * Assignment Node
 */
public class AssignmentNode extends NumberNode {
    private final CalculatorNode left;
    private final String variableName;

    public AssignmentNode(CalculatorNode left, VariableNode right) {
        this.left = left;
        this.variableName = right.getName();
    }

    public BigDecimal evaluate() {
        BigDecimal leftResult = left.evaluate();
        VariableMap.setValue(variableName, leftResult);
        return leftResult;
    }

    public String toString() {
        return "$"+variableName;
    }
}
