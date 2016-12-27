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

    public static void nextMustBeEnd(UserParserCallback parser, String nodeName, boolean displayable) {
        if (!parser.nextIs(EndToken.get().getKey()) &&
                !parser.nextIs(colon.getKey()) &&
                (!displayable || !parser.nextIs(display.getKey())))
            parser.abort("You must end '" + nodeName + "' with 'colon'" + (displayable ? " or a 'display'" : "") + " if it does not follow 'END'");
    }

    public static void optionalColon(UserParserCallback parser) {
        if (parser.nextIs(colon.getKey()))
            parser.expectSingleLexeme(colon.getKey());
    }

    public static String indent(String s) {
        return s.replaceAll("(?m)^", "  ");
    }
}
