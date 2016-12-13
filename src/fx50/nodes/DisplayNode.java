package fx50.nodes;

import fx50.CalculatorHelper;
import org.bychan.core.basic.EndToken;
import org.bychan.core.dynamic.UserParserCallback;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static fx50.CalculatorHelper.Tokens.colon;
import static fx50.CalculatorHelper.Tokens.conditionElse;
import static fx50.CalculatorHelper.Tokens.conditionIfEnd;

/**
 * Statement Node
 */
public class DisplayNode extends StatementNode {
    private final PrintStream out;

    public DisplayNode(CalculatorNode left, UserParserCallback parser, PrintStream out) {
        super(left, parser);
        this.out = out;
    }

    public BigDecimal evaluate() {
        BigDecimal leftResult;
        leftResult = left.evaluate();
        out.println(leftResult.round(new MathContext(10, RoundingMode.HALF_UP)).stripTrailingZeros().toString() + " [DISPLAY]");
        if (!isLast) {
            return right.evaluate();
        } else
            return leftResult;
    }

    public String toString() {
        if (!isLast)
            return left.toString() + "display:" + right.toString();
        else
            return left.toString() + "display:";
    }
}
