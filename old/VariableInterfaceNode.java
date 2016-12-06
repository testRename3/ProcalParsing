import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;

/**
 * Created by Daniel on 1/12/2016.
 */
public class VariableInterfaceNode implements VariableNode {
    @NotNull
    private final Node left;
    @NotNull
    private final String name;
    @Nullable
    public BigDecimal value;

    public VariableInterfaceNode(@NotNull Node left, @NotNull final String name) {
        this.left = left;
        this.name = name;
        this.value = new BigDecimal(0);
        getVarMap().setValue(name, value);
    }

    @NotNull
    @Override
    public BigDecimal evaluate(VariableMap variableMap) {
        return variableMap.get(name);
    }

    @NotNull
    @Override
    public VariableMap getVarMap() {
        return left.getVarMap();
    }

    @Override
    public String toString() {
        return  "Number(" + name + ")";
    }

    public void setValue(@SuppressWarnings("NullableProblems") @NotNull final BigDecimal value) {
        getVarMap().setValue(name, value);
    }

    @NotNull
    public void assign(@NotNull BigDecimal value, VariableMap variableMap) {
        variableMap.setValue(name, value);
    }
}
