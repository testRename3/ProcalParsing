package fx50.nodes;

import fx50.CalcMath.Constants;
import org.bychan.core.basic.Lexeme;
import org.bychan.core.dynamic.UserParserCallback;

/**
 * Constant Node
 */
public class ConstantNode extends NumberNode {
    private final String constantName;

    public ConstantNode(UserParserCallback parser, Lexeme lexeme) {
        this.constantName = lexeme.getText().substring(1);
        try {
            this.value = Constants.valueOf(constantName).getValue();
        } catch (IllegalArgumentException e) {parser.abort("Constant not present");}
    }

    public String toString() {
        return "&"+constantName;
    }
}
