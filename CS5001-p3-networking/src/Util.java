import java.io.File;
/**
 * Util class, save tools.
 * @author 200009834
 */
public class Util {
    /**
     * check args whether ok.
     * @param args from command line.
     * @return whether pass.
     */
    public static boolean argsCheck(String[] args) {
        if (args == null || args.length < 2) {
            System.out.println("Usage: java WebServerMain <document_root> <port>");
            return false;
        } else if (dirCheck(args[0]) && portCheck(args[1])) {
            return true;
        }
        return false;
    }
    /**
     *  check this file type.
     * @param needCheckFile the file want to check.
     * @return type of file.
     */
    public static String checkFileType(File needCheckFile) {
        String fileName = needCheckFile.getName();
        if (fileName.endsWith("txt") || fileName.endsWith("html")) {
            return "text/html";
        } else if (fileName.endsWith("gif")) {
            return "gif";
        } else if (fileName.endsWith("jpg")) {
            return "jpg";
        } else if (fileName.endsWith("png")) {
            return "png";
        } else {
            return "";
        }
    }

    private static boolean dirCheck(String dir) {
        File dirTest = new File(dir);

        if (dirTest.isDirectory()) {
            return true;
        } else {
            System.out.println(dir + " is not a dir, please recheck your input.");
            return false;
        }
    }

    private static boolean portCheck(String port) {
        int numPort;
        try {
            numPort = Integer.parseInt(port);
        } catch (NumberFormatException e) {
            System.out.println("Second input should be a port number. " + port
                    + " is your input, please recheck your input");
            return false;
        }
        if (numPort < 1 || numPort > Integer.MAX_VALUE) {
            System.out.println("You should put a correct number. " + numPort
                    + " is your input, please recheck your input");
            return false;
        }
        return true;
    }
}
