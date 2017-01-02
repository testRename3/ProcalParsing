package fx50.nodes;

import org.bychan.core.basic.Lexeme;
import org.bychan.core.dynamic.UserParserCallback;

import java.math.BigDecimal;

import static fx50.CalculatorHelper.VariableMap;
import static fx50.ParsingHelper.nextMustBeSeparator;

/**
 * Goto Node
 */
public class GotoNode implements CalculatorNode {
    private final String label;
    private UserParserCallback<CalculatorNode> parser;

    //TODO Erroneous
    public GotoNode(CalculatorNode left, UserParserCallback<CalculatorNode> parser, Lexeme<CalculatorNode> lexeme) {
        label = lexeme.getText().replaceAll("Goto +", "");
        if (label.equals(""))
            parser.abort("Label string missing");
        this.parser = parser;
        nextMustBeSeparator(parser, "label");
    }

    public BigDecimal evaluate() {
        /*if (labels.containsKey(label))
            return parser.expression(labels.get(label)).evaluate();*/
        throw new RuntimeException("Label '" + label + "' not found.");
    }

    public String toString() {
        return "Goto " + label + ":";
    }
}
