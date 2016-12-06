package fx50.nodes;

import java.math.BigDecimal;

/**
 * Number Node
 */
public class NumberNode implements CalculatorNode {
    protected BigDecimal value;

    public NumberNode() {
        this.value = new BigDecimal(0);
    }

    public NumberNode(BigDecimal value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumberNode numberNode = (NumberNode) o;

        return value == numberNode.value;
    }

    public BigDecimal evaluate() {
        return value;
    }

    public String toString() {
        return value.toPlainString();
    }
}
