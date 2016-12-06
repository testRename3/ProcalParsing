import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

/**
 * Created by Daniel on 1/12/2016.
 */
class AssignNode implements Node {
    @NotNull
    private final Node left;
    @NotNull
    private final VariableNode right;

    public AssignNode(@NotNull final Node left, @NotNull final Node right) {
        if (!(right instanceof VariableNode)) {
            throw new IllegalArgumentException("Cannot assign to non-variable node '" + right + "'");
        }
        this.left = left;
        this.right = (VariableNode) right;
    }

    @NotNull
    @Override
    public VariableMap getVarMap() {
        return left.getVarMap();
    }

    @NotNull
    @Override
    public BigDecimal evaluate(VariableMap variableMap) {
        BigDecimal lhsValue = left.evaluate(variableMap);
        right.assign(lhsValue, getVarMap());
        return lhsValue;
    }

    public String toString() {
        return "(= " + left + " " + right + ")";
    }
}
