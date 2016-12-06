import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Created by Daniel on 1/12/2016.
 */
public class InputNode implements Node {
    @NotNull
    private final Node left;

    public InputNode(Node left, @NotNull final Node right) {
        this.left = left;
    }

    @NotNull
    public BigDecimal evaluate(VariableMap variableMap) {
        return BigDecimal.valueOf(Double.valueOf((new Scanner(System.in)).nextLine()));
    }

    public VariableMap getVarMap() {
        return left.getVarMap();
    }
}
