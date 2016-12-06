import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

/**
 * Created by Daniel on 1/12/2016.
 */
interface VariableNode extends Node {
    public void assign(@NotNull BigDecimal value, VariableMap varMap);
}
