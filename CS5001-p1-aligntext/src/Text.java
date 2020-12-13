import java.util.ArrayList;
/**
 *This object text in here.
 * @author xy
 */
public class Text {
    private String[] content;
    private int lineLength;
    private String mode = "L";
    private ArrayList<String> paragraph = new ArrayList<>();
    private static final int SYMBOL_SPACE = 4;
    /**
     * Constructor.
     * @param content line read from txt file.
     * @param lineLength desired length of the line for wrapping the text.
     */
    public Text(String[] content, int lineLength) {
        this.content = content;
        this.lineLength = lineLength;
    }
    /**
     * Constructor.
     * @param content line read from txt file.
     * @param lineLength desired length of the line for wrapping the text.
     * @param mode support different mode type
     */
    public Text(String[] content, int lineLength, String mode) {
        this.content = content;
        this.lineLength = lineLength;
        this.mode = mode;
    }

    /**
     * Check mode type, and process data.
     */
    public void checkMode() {
        if (mode.equals("L")) {
            paragraph = UtilBox.leftAlign(content, lineLength);
        }
        if (mode.equals("R")) {
            paragraph = UtilBox.leftAlign(content, lineLength);
            paragraph = UtilBox.rightAlign(paragraph, lineLength);
        }
        if (mode.equals("C")) {
            paragraph = UtilBox.leftAlign(content, lineLength);
            paragraph = UtilBox.centreAlign(paragraph, lineLength);
        }
        if (mode.equals("B")) {
            paragraph = UtilBox.leftAlign(content, lineLength - SYMBOL_SPACE);
            paragraph = UtilBox.bubbleAlign(paragraph);
        }
    }

    /**
     * print the paragraph.
     */
    public void printOutPut() {
        for (String s : paragraph) {
            System.out.println(s);
        }
    }
}
