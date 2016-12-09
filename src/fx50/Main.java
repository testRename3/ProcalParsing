package fx50;

import fx50.nodes.CalculatorNode;
import org.bychan.core.basic.ParseResult;
import org.bychan.core.dynamic.Language;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    static Language<CalculatorNode> l;

    public static void main(String[] args) throws Exception {
        l = CalculatorHelper.getSimpleCalculatorLanguage();
        System.out.println("Welcome to Fx-50F Ultra!\n" +
                "type \"help\" for help.\n" +
                "type \"quit\" or another common exit word to quit.");
        Run.run();
    }

    private static class Run {
        static void run(){
            Scanner scanner = new Scanner(System.in);
            System.out.print("> ");
            String line = scanner.nextLine().replaceAll("\\s+", " ").trim();
            if (line.matches("quit|exit|leave|stop|halt")) {
                System.out.println("Goodbye!");
                return;
            } else if (line.equals("")) {
                run();
                return;
            } else if (line.matches("prog [A-Za-z][A-Za-z\\d]*")) {
                try {
                    line = readFile(line.substring(5)+".procal", StandardCharsets.UTF_8);
                } catch (IOException e) {
                    System.out.println("No program found with name \"" + line.substring(5) + "\"!");
                    run();
                    return;
                }
            }
            //TODO make temp file for printstream and return last line
            //String[] lines = line.split(":");
            /*for (int i = 0; i < lines.length; i++) {
                //TODO remove line splitting and implement statement parsing
                String result = "";
                String parsedResult = "";
                if (!lines[i].equals("")) {
                    ParseResult<CalculatorNode> pr;
                    try {
                        pr = l.newLexParser().tryParse(lines[i]);
                        parsedResult = pr.getRootNode().toString();
                        //TODO incorrect exponential number
                        result = pr.getRootNode().evaluate().setScale(10, BigDecimal.ROUND_HALF_UP).toPlainString();
                    } catch (Exception e) {
                        result = e.getMessage();
                    }
                }
                if (i == lines.length - 1)
                    System.out.println(parsedResult + "\n=" + result);
            }*/
            String result = "";
            String parsedResult = "";
            if (!line.equals("")) {
                ParseResult<CalculatorNode> pr;
                try {
                    pr = l.newLexParser().tryParse(line);
                    parsedResult = pr.getRootNode().toString();
                    //TODO incorrect exponential number
                    result = pr.getRootNode().evaluate().setScale(10, BigDecimal.ROUND_HALF_UP).toPlainString();
                } catch (Exception e) {
                    result = e.getMessage();
                }
            }
                System.out.println(parsedResult + "\n=" + result);

            run();
        }
    }

    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}

