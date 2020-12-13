import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Log record method here.
 * @author 200009834
 */
public abstract class Logging {
    private static String request = "";
    private static String response = "";
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm:ss");
//    public synchronized void logValid(String request)\
    static void logRequest(String request) {
        Logging.request = "Request: " + request + "\n" + "At:" + getDateTime();
    }

    static void logResponse(String header) {
        Logging.response = "Response...\nHeader: \n" + header + "At: " + getDateTime();
    }

    private static String getDateTime() {
        LocalDateTime now = LocalDateTime.now();
        return DTF.format(now) + "\n";
    }

    static String getRequest() {
        return request;
    }
    static String getResponse() {
        return response;
    }
}
