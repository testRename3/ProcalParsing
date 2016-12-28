package fx50;

import fx50.nodes.CalculatorNode;
import org.bychan.core.basic.LexParser;
import org.bychan.core.basic.ParseResult;
import org.bychan.core.dynamic.Language;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static fx50.CalcMath.CalcMath.sigfig;
import static fx50.CalculatorHelper.labels;

public class Fx50Parser {
    private Language<CalculatorNode> l;
    private InputStream in;
    private PrintStream out;

    public Fx50Parser(InputStream in, PrintStream out) throws Exception {
        this.in = in;
        this.out = out;
        CalculatorHelper.setInOut(in, out);
        l = CalculatorHelper.getFx50Language();
    }

    public void parse(String line) throws UnsupportedEncodingException {
        line = line.replaceAll("\\s+", " ").trim();
        if (line.equals("")) {
            return;
        } else if (line.matches("prog [A-Za-z][A-Za-z\\d]*")) {
            try {
                line = readFile(line.substring(5) + ".procal", StandardCharsets.UTF_8);
            } catch (IOException e) {
                out.println("No Prog Error: No program found with name \"" + line.substring(5) + "\"!");
                return;
            }
        }
        String result;
        String parsedResult;
        LexParser<CalculatorNode> lexParser = l.newLexParser();
        ParseResult<CalculatorNode> pr;
        try {
            labels.clear();
            pr = lexParser.tryParse(line.replaceAll("display(?!:)", "display:").replaceAll("\\s*$", "").replaceAll("(?<=[^:])$", ":"));
            parsedResult = pr.getRootNode().toString();
            out.println("---PARSE RESULT---\n" + parsedResult + "\n---END OF PARSE RESULT---");
            out.println("=" + sigfig(pr.getRootNode().evaluate(), 10).toString());
        } catch (Exception e) {
            result = "Error: " + e.getMessage();
            out.println(result);
        }
    }

    private static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}

