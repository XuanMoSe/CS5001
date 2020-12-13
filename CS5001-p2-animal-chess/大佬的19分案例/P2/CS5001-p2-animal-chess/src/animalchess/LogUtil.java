package animalchess;

/**
 * Use to print log.
 * There is a switcher to control the whole project's Log print.
 * It's more convenient than inputing System.out.print().
 * @author Xiuping Fan
 *
 */
public class LogUtil {
    /**
     * Use to control the whole project's Log print.
     * If true, open the log print
     * If false, close the log print
     */
    private static final boolean SWITCHER = false;

    /**
     * Print a String.
     * @param content of printing
     */
    public static void print(String content) {
        if (SWITCHER) {
            System.out.println(content);
        }
    }

    /**
     * Print a String.
     * @param indicator use to classify the operation
     * @param content of printing
     */
    public static void print(Character indicator, String content) {
        if (SWITCHER) {
            if (indicator.equals('i')) {
                System.out.println("important: " + content);
            } else if (indicator.equals('d')) {
                System.out.println("delete: " + content);
            } else if (indicator.equals('a')) {
                System.out.println("add: " + content);
            } else if (indicator.equals('u')) {
                System.out.println("update: " + content);
            }
        }
    }

    /**
     * Print two number.
     * @param i is the first number
     * @param j is the second number
     */
    public static void print(int i, int j) {
        if (SWITCHER) {
            System.out.println("i = " + i + "; j = " + j + ";");
        }
    }

}
