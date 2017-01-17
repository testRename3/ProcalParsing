package fx50.nodes;

import fx50.API.InputToken;
import org.bychan.core.dynamic.UserParserCallback;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static fx50.CalcMath.CalcMath.sigfig;

/**
 * Statement Node
 */
public class DisplayNode extends StatementNode {
    private final PrintStream out;

    public DisplayNode(CalculatorNode left, UserParserCallback<CalculatorNode> parser, PrintStream out) {
        super(left, parser);
        this.out = out;
    }

    public BigDecimal evaluate() {
        BigDecimal leftResult;
        leftResult = left.evaluate();
        out.println(left.toString() + " = " + sigfig(leftResult, 10).toString() + " [DISPLAY]");
        if (!isLast) {
            return right.evaluate();
        } else
            return leftResult;
    }

    public String toString() {
        if (!isLast)
            return left.toString() + " display\n" + right.toString();
        else
            return left.toString() + " display";
    }

    public List<InputToken> toInputTokens() {
        return new ArrayList<>(Collections.singletonList(new InputToken("display", "◢")));
    }
}
