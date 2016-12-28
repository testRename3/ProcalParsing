package fx50;


import org.bychan.core.basic.EndToken;
import org.bychan.core.dynamic.UserParserCallback;

import static fx50.CalculatorHelper.Tokens.*;

public class ParsingHelper {
    public static boolean nextIsStatementEnd(UserParserCallback parser) {
        return parser.nextIs(EndToken.get().getKey()) ||
                parser.nextIs(set.getKey()) ||
                parser.nextIs(loopNext.getKey()) ||
                parser.nextIs(loopWhileEnd.getKey()) ||
                parser.nextIs(conditionIfEnd.getKey()) ||
                parser.nextIs(conditionElse.getKey()) ||
                parser.nextIs(colon.getKey()) ||
                parser.nextIs(display.getKey());
    }

    public static void nextMustBeSeparator(UserParserCallback parser, String nodeName) {
        if (!nextIsStatementEnd(parser))
            parser.abort("You must end '" + nodeName + "' with 'colon' or a 'display' if the statement is not the end of a code block.");
    }

    public static void optionalColon(UserParserCallback parser) {
        if (parser.nextIs(colon.getKey()))
            parser.expectSingleLexeme(colon.getKey());
    }

    public static String indent(String s) {
        return s.replaceAll("(?m)^", "  ");
    }
}
