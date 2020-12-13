/**
 * Main method, all program start.
 * @author 200009834
 */
public class WebServerMain {
    /**
     * main method.
     * @param args accept at least two arguments, first dir, second port.
     */
    public static void main(String[] args) {

        if (Util.argsCheck(args)) {
            new WebServer(args[0], Integer.parseInt(args[1]));
        } else {
            System.exit(1);
        }
    }
}
