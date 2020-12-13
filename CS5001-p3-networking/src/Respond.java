import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.Socket;
/**
 * All respond is here.
 * @author 200009834
 */
public class Respond {
    private Socket conn;
    private String dir;
    private String request;
    private PrintWriter printWriter;
    private String requestFileName;
    private String requestFilePath;

    /**
     * constructor method.
     * @param conn socket.
     * @param dir target directory.
     * @param request client send the request.
     * @throws IOException input output exception.
     */
    public Respond(Socket conn, String dir, String request) throws IOException {
        this.conn = conn;
        this.dir = dir;
        this.request = request;
        this.printWriter = new PrintWriter(this.conn.getOutputStream(), true);
        /*Split all space*/
        String[] requestHeader = request.split("\\s+");
        this.requestFileName = requestHeader[1];
        this.requestFilePath = this.dir + this.requestFileName;
        try {
            if (request.contains("GET")) {
                respondGET();
            } else if (request.contains("HEAD")) {
                respondHEAD();
            } else if (request.contains("DELETE")) {
                respondDELETE();
            } else if (request.contains("OPTIONS")) {
                respondOPTIONS();
            } else {
                respondNOTIMPLEMENTED();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    private String getHeader(String contentType, long resourceLength) {
        String header = "HTTP/1.1 200 OK\n"
                + "Server: Simple Java Http Server\n"
                + "Content-Type: " + contentType + "\r\n"
                + "Content-Length: " + resourceLength + "\r\n";
        Logging.logResponse(header);
        return header;
    }

    private String notFoundHeader(String contentType, long resourceLength) {
        String header = "HTTP/1.1 404 Not Found\n"
                + "Server: Simple Java Http Server\n"
                + "Content-Type: " + contentType + "\r\n"
                + "Content-Length: " + resourceLength + "\r\n";
        Logging.logResponse(header);
        return  header;
    }

    private String notImplementedHeader(String contentType, long resourceLength) {
        String header = "HTTP/1.1 501 Not Implemented\n"
                + "Server: Simple Java Http Server\n"
                + "Content-Type: " + contentType + "\r\n"
                + "Content-Length: " + resourceLength + "\r\n";
        Logging.logResponse(header);
        return header;
    }

    private void record() {
        try {
            FileWriter fw = new FileWriter(ConfigurationParameter.LOG_FILE, true);
            PrintWriter logger = new PrintWriter(fw);
            logger.write(Logging.getRequest());
            logger.write(Logging.getResponse());
            logger.write(ConfigurationParameter.BREAKER + ConfigurationParameter.BREAKER);
            logger.flush();
            logger.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
    private void respondGET() {
        File resource = new File(requestFilePath);

        if (resource.exists()) {
            printWriter.println(getHeader(Util.checkFileType(resource), resource.length()));
            sendResource(requestFilePath);
            record();
        } else {
            respondNOTFOUND();
        }
    }

    private void respondHEAD() {
        File resource = new File(requestFilePath);

        if (resource.exists()) {
            printWriter.println(getHeader(Util.checkFileType(resource), resource.length()));
            record();
        } else {
            respondNOTFOUND();
        }
    }

    private void respondDELETE() {
        File resource = new File(requestFilePath);

        if (resource.exists()) {
            record();
        } else {
            respondNOTFOUND();
        }
    }

    private void respondOPTIONS() {
        String page = "<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\"><html>"
                + "<head><title> Server-Supported HTTP Methods</title></head>"
                + "<body><h1>HTTP methods which the server serves</h1><p>HEAD</p><p>GET</p><p>DELETE</p><p>OPTIONS</p></body>"
                + "</html>";

        printWriter.println(getHeader("html/txt", page.length()));
        printWriter.println(page);
        record();
    }

    private void respondNOTFOUND() {
        File resource = new File(requestFilePath);
        String page = "<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\"><html>"
                + "<head><title>404 Not Found</title></head>"
                + "<body><h1>404 Not Found</h1><p>The requested URL "
                + requestFileName + " was not found on this server.</p></body>"
                + "</html>";

        printWriter.println(notFoundHeader(Util.checkFileType(resource), page.length()));
        printWriter.println(page);
        record();
    }

    private void respondNOTIMPLEMENTED() {
        printWriter.println(notImplementedHeader("text/html", 0));
        record();
    }

    private void sendResource(String requestFilePath) {
        try {
            InputStream in = new FileInputStream(requestFilePath);
            OutputStream out = conn.getOutputStream();

            byte[] buf = new byte[ConfigurationParameter.BYTE_SIZE];
            int len;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
