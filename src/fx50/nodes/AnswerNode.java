package fx50.nodes;

import fx50.API.InputToken;
import fx50.CalculatorHelper.VariableMap;
import org.bychan.core.basic.Lexeme;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    public List<InputToken> toInputTokens() {
        return new ArrayList<>(Collections.singletonList(new InputToken("Ans", "Ans")));
    }
}
