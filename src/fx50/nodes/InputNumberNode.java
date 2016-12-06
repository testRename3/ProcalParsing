package fx50.nodes;

import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Input Number Node
 */
public class InputNumberNode implements CalculatorNode {
    private final InputStream in;
    private final PrintStream out;
    private final String variableName;

    public InputNumberNode(InputStream in, PrintStream out, String variableName) {
        this.in = in;
        this.out = out;
        this.variableName = variableName;
    }

    public BigDecimal evaluate() {
        out.print(variableName + "?:");
        return new BigDecimal((new Scanner(in)).nextLine());
    }

    public String toString() {
        return "?";
    }
}
