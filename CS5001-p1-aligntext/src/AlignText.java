/**
 *This is a small program for aligning text.
 * @author xy
 */
public class AlignText {
    private static final int NUMBER_OF_NORMAL_ARGS = 2;
    private static final int NUMBER_OF_ENHANCE_ARGS = 3;
    /**
     * Main method about this whole program.
     * @param args first one is the path of file, second is  desired length of the line for wrapping the text,
     *             third one(if have) different type wrapping text method. "L" for Left-align text, "R" for
     *             Right-align text, "C" for Centre-align text, "B" for Put text in a speech bubble.
     */
    public static void main(String[] args) {
        if (args.length == NUMBER_OF_NORMAL_ARGS && UtilBox.isNumeric(args[1])) {
            Text txt = new Text(FileUtil.readFile(args[0]), Integer.parseInt(args[1]));
            txt.checkMode();
            txt.printOutPut();
        } else if (args.length == NUMBER_OF_ENHANCE_ARGS && UtilBox.isNumeric(args[1]) && UtilBox.isMode(args[2])) {
            Text txt = new Text(FileUtil.readFile(args[0]), Integer.parseInt(args[1]), args[2]);
            txt.checkMode();
            txt.printOutPut();
        } else if (args.length == NUMBER_OF_ENHANCE_ARGS) {
            System.out.println("usage: java AlignText file_name line_length [align_mode]");
            System.exit(0);
        } else {
            System.out.println("usage: java AlignText file_name line_length");
            System.exit(0);
        }
    }
}
