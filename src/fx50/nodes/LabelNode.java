package fx50.nodes;

import org.bychan.core.basic.Lexeme;
import org.bychan.core.dynamic.UserParserCallback;

import java.math.BigDecimal;

import static fx50.CalculatorHelper.*;
import static fx50.ParsingHelper.nextMustBeSeparator;

/**
 * Label Node
 */
public class LabelNode implements CalculatorNode {
    private final String label = "";

    //TODO Erroneous
    public LabelNode(CalculatorNode left, UserParserCallback<CalculatorNode> parser, Lexeme<CalculatorNode> lexeme) {
        /*label = lexeme.getText().replaceAll("Lbl +", "");
        if (label.equals(""))
            parser.abort("Label string missing");
        nextMustBeSeparator(parser, "label");
        labels.put(label, this);*/
    }

    public BigDecimal evaluate() {
        return VariableMap.getValue("~Ans");
    }

    public String toString() {
        return "Lbl " + label;
    }
}
