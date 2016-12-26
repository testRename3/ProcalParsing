package fx50.nodes;

import org.bychan.core.dynamic.UserParserCallback;

import java.math.BigDecimal;

import static fx50.CalculatorHelper.VariableMap;
import static fx50.CalculatorHelper.Tokens.*;
import static fx50.ParsingHelper.indent;
import static fx50.ParsingHelper.nextMustBeEnd;
import static fx50.ParsingHelper.optionalColon;

/**
 * While Loop Node
 */

//TODO must start in a new statement
public class ForLoopNode implements CalculatorNode {
    private final CalculatorNode initNode;
    private final CalculatorNode toNode;
    private VariableNode controlVariable;
    private CalculatorNode doNode;
    private CalculatorNode stepNode;

    public ForLoopNode(CalculatorNode left, UserParserCallback parser) {
        //Expression before set
        initNode = (CalculatorNode) parser.expression(left, 4);
        parser.expectSingleLexeme(set.getKey());
        controlVariable = (VariableNode) parser.parseSingleToken(left, variable.getKey());
        parser.expectSingleLexeme(loopTo.getKey());

        toNode = (CalculatorNode) parser.expression(left, 3);

        if (parser.nextIs(loopStep.getKey())) {
            parser.expectSingleLexeme(loopStep.getKey());
            stepNode = (CalculatorNode) parser.expression(left, 3);
        }

        parser.expectSingleLexeme(colon.getKey());

        doNode = (CalculatorNode) parser.expression(left);

        parser.expectSingleLexeme(loopNext.getKey());
        nextMustBeEnd(parser, "Next", true);
    }

    public BigDecimal evaluate() {
        BigDecimal toResult = toNode.evaluate();
        String controlVariableName = controlVariable.getName();
        VariableMap.setValue(controlVariableName, initNode.evaluate());
        BigDecimal doResult = new BigDecimal(0);
        BigDecimal controlVariableValue;
        try {
            while ((controlVariableValue = VariableMap.getValue(controlVariableName)).compareTo(toResult) < 1) {
                doResult = doNode.evaluate();
                VariableMap.setValue(controlVariableName, controlVariableValue.add(BigDecimal.ONE));
            }
        } catch (RuntimeException e) {
            if (!e.getMessage().equals("Breaking"))
                throw e;
        }
        return doResult;
    }

    public String toString() {
        return "For " + initNode.toString() + " -> " + controlVariable.toString() +
                " To "+ toNode.toString() +
                (stepNode == null ? "" : " Step " + stepNode.toString()) + ":\n" +
                indent(doNode.toString()) +
                "\nNext";
    }
}
