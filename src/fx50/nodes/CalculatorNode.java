package fx50.nodes;

import java.math.BigDecimal;

/**
 * Calculator Node
 */
public interface CalculatorNode {
    BigDecimal evaluate();
    String toString();
}
