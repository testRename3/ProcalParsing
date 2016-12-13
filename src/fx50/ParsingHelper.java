package fx50;


import org.bychan.core.basic.EndToken;
import org.bychan.core.dynamic.UserParserCallback;

import static fx50.CalculatorHelper.Tokens.*;

public class ParsingHelper {
    public static boolean nextIsStatementEnd(UserParserCallback parser) {
        return parser.nextIs(EndToken.get().getKey()) ||
                parser.nextIs(conditionIfEnd.getKey()) ||
                parser.nextIs(conditionElse.getKey()) ||
                parser.nextIs(colon.getKey()) ||
                parser.nextIs(display.getKey());
    }
}
