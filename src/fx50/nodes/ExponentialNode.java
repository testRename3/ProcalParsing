package fx50.nodes;

import org.bychan.core.basic.Lexeme;

import java.math.BigDecimal;

import static fx50.CalcMath.CalcMath.precision;

/**
 * Exponential Node
 */
public class ExponentialNode implements CalculatorNode {
    private final Lexeme lexeme;

    public ExponentialNode(Lexeme lexeme) {
        this.lexeme = lexeme;
    }

    public BigDecimal evaluate() {
        int rightResult = Integer.parseInt(lexeme.getText().substring(1));
        boolean isNeg = rightResult < 0;
        if (!isNeg)
            return new BigDecimal(10).pow(rightResult);
        else
            return BigDecimal.ONE.divide(new BigDecimal(10).pow(-rightResult), precision);
    }

    public String toString() {
        return lexeme.getText();
    }
}
