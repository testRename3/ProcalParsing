import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

/**
 * Created by Daniel on 1/12/2016.
 */
interface Node {
    @NotNull
    BigDecimal evaluate(VariableMap variableMap);
    VariableMap getVarMap();
}
