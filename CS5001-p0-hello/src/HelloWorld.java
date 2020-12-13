/**
 * This is the first couse work for CS5001.
 */
public class HelloWorld {
    /** 
     * This is mian method.
     * @param args input name.
     */
        public static void main(String[] args) {

        if (args.length > 0) {
            System.out.println("Hello " + args[0]);
        } else {
            System.out.println("Hello World");
        }
    }
}
