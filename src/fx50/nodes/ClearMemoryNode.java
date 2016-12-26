package fx50.nodes;

import fx50.CalculatorHelper;
import org.bychan.core.basic.EndToken;
import org.bychan.core.dynamic.UserParserCallback;

import java.io.PrintStream;
import java.math.BigDecimal;

import static fx50.ParsingHelper.nextMustBeEnd;

/**
 * Clear Memory Node
 */
public class ClearMemoryNode implements CalculatorNode {
    private final CalculatorNode left;
    private final PrintStream out;

    public ClearMemoryNode(CalculatorNode left, UserParserCallback parser, PrintStream out) {
        this.left = left;
        this.out = out;

        nextMustBeEnd(parser, "clearMemory", true);
    }

    public BigDecimal evaluate() {
        CalculatorHelper.VariableMap.clearMemory();
        out.println("Memory Cleared!");
        return new BigDecimal(0);
    }

    public String toString() {
        return "ClrMemory";
    }
}
