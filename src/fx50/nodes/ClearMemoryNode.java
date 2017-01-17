package fx50.nodes;

import fx50.API.InputToken;
import fx50.CalculatorHelper;
import org.bychan.core.dynamic.UserParserCallback;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static fx50.ParsingHelper.nextMustBeSeparator;

/**
 * Clear Memory Node
 */
public class ClearMemoryNode implements CalculatorNode {
    private final CalculatorNode left;
    private final PrintStream out;

    public ClearMemoryNode(CalculatorNode left, UserParserCallback parser, PrintStream out) {
        this.left = left;
        this.out = out;

        nextMustBeSeparator(parser, "clearMemory");
    }

    public BigDecimal evaluate() {
        CalculatorHelper.VariableMap.clearMemory();
        out.println("Memory Cleared!");
        return new BigDecimal(0);
    }

    public String toString() {
        return "ClrMemory";
    }

    public List<InputToken> toInputTokens() {
        return new ArrayList<>(Collections.singletonList(new InputToken("ClrMemory", "ClrMemory")));
    }
}
