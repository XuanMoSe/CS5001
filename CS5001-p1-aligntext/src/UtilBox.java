import java.util.ArrayList;

/**
 * Tool method all in here.
 * @author xy
 */
public class UtilBox {
    /**
     * Check whether input is int type.
     * @param str the String want to check.
     * @return true is num, false isn't num.
     */
    public static boolean isNumeric(String str) {
        //pay attention --i, why not i--. keep remember.
        for (int i = str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    /**
     *Check input is mode "L,R,C,B".
     * @param str input form main method, check whether is a mode.
     * @return if return is ture that means input correct.
     */
    public static boolean isMode(String str) {
        return str.equals("L") || str.equals("R") || str.equals("C") || str.equals("B");
    }
    /**
     *To generate space.
     * @param requireSymbolNum input the number of symbol want to generate.
     * @param symbol which symbol want to generate.
     * @return the number of symbol want to generate.
     */
    public static String generateSymbol(int requireSymbolNum, String symbol) {
        StringBuilder wishSymbol = new StringBuilder();
        wishSymbol.append(symbol.repeat(Math.max(0, requireSymbolNum))); // add space.
        return wishSymbol.toString();
    }
    /**
     *This is leftAlign method.
     * @param content read a line form txt file.
     * @param lineLength the length of line need to output.
     * @return Output that require.
     */
    public static ArrayList<String> leftAlign(String[] content, int lineLength) {
        ArrayList<String> paragraph = new ArrayList<>();
        for (String s : content) {
            String[] words = s.split(" ");
            String line = "";
                for (String word : words) {
                if (line.equals("")) {
                    line = word;
                } else if ((line + " " + word).length() <= lineLength) {
                    line = line + " " + word;
                } else if (word.length() >= lineLength) {
                    paragraph.add(line);
                    line = word;
                } else {
                    paragraph.add(line);
                    line = word;
                }
            }
            paragraph.add(line);
        }
        return paragraph;
    }
    /**
     *This is rightAlign method.
     * @param content complete leftAlign paragraph.
     * @param lineLength the length of line need to output.
     * @return Output that require.
     */
    public static ArrayList<String> rightAlign(ArrayList<String> content, int lineLength) {
        ArrayList<String> paragraph = new ArrayList<>();
        String line;
        for (String s: content) {
            line = s;
            if (lineLength - line.length() > 0) {
                line = generateSymbol(lineLength - line.length(), " ") + line;
                paragraph.add(line);
            } else {
                paragraph.add(line);
            }
        }
        return paragraph;
    }
    /**
     *This is centreAlign method.
     * @param content complete leftAlign paragraph.
     * @param lineLength the length of line need to output.
     * @return Output that require.
     */
    public static ArrayList<String> centreAlign(ArrayList<String> content, int lineLength) {
        ArrayList<String> paragraph = new ArrayList<>();
        String line;
        for (String s: content) {
            line = s;
            if (lineLength - line.length() > 0) {
                if ((lineLength - line.length()) % 2 == 0) {
                    line = generateSymbol((lineLength - line.length()) / 2, " ") + line
                            + generateSymbol((lineLength - line.length()) / 2, " ");
                    paragraph.add(line);
                } else {
                    line = generateSymbol((lineLength - line.length()) / 2 + 1, " ") + line
                            + generateSymbol((lineLength - line.length()) / 2, " ");
                    paragraph.add(line);
                }
            } else {
                paragraph.add(line);
            }
        }
        return paragraph;
    }
    /**
     *This is bubbleAlign method.
     * @param content complete leftAlign paragraph.
     * @return Output that require.
     */
    public static ArrayList<String> bubbleAlign(ArrayList<String> content) {
        ArrayList<String> paragraph = new ArrayList<>();
        String longestLine = "";
        int maxCharNum;
        for (String s: content) {
            if (s.length() > longestLine.length()) {
                longestLine = s;
            }
        }
        maxCharNum = longestLine.length();
        paragraph.add(" " + generateSymbol(maxCharNum + 2, "_"));
        for (String s: content) {
            int thisLineNum = s.length();
            paragraph.add("| " + s + generateSymbol(maxCharNum - thisLineNum, " ") + " |");
        }
        paragraph.add(" " + generateSymbol(maxCharNum + 2, "-"));
        paragraph.add("        \\");
        paragraph.add("         \\");
        return paragraph;
    }

}
