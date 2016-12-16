package fx50.nodes;

import fx50.CalculatorHelper.VariableMap;
import org.bychan.core.basic.Lexeme;

import java.math.BigDecimal;

/**
 * Answer Node
 */
public class AnswerNode extends VariableNode{

    public AnswerNode() {
        super("~Ans");
    }

    public String toString() {
        return "Ans";
    }
}
