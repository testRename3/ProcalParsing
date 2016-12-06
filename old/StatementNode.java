import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

/**
 * Created by Daniel on 1/12/2016.
 */
public class StatementNode implements Node {
    @NotNull
    private final Node left;
    @NotNull
    private final Node right;

    public StatementNode(@NotNull final Node left, @NotNull final Node right) {
        this.left = left;
        this.right = right;
    }

    @NotNull
    @Override
    public BigDecimal evaluate(VariableMap variableMap) {
        left.evaluate(variableMap);
        return right.evaluate(variableMap);
    }

    @NotNull
    @Override
    public VariableMap getVarMap() {
        return left.getVarMap();
    }

    @Override
    public String toString() {
        return "(x " + left + " " + right + ")";
    }
}
