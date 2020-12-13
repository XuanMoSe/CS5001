import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.nio.file.DirectoryNotEmptyException;

/**
 * Multi thread happen here.
 * @author 200009834
 */
public class ConnectionHandler implements Runnable {
    private Socket conn;
    private String dir;
    private InputStream is;
    private BufferedReader br;

    /**
     * Why so many javadoc??? I think it is easy to understand.
     * @param conn socket.
     * @param dir directory.
     */
    public ConnectionHandler(Socket conn, String dir) {
        this.conn = conn;
        this.dir = dir;

        try {
            this.conn.setSoTimeout(ConfigurationParameter.TIME_OUT);
        } catch (SocketException se) {
            System.out.println("Connection Handler: Socket time out.");
        }

        try {
            is = conn.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
//            System.out.println(br.readLine());
        } catch (IOException ioe) {
            System.out.println("Connection Handler: " + ioe.getMessage());
        }
    }
    @Override
    public void run() {
        try {
            handleRequest();
        } catch (Exception e) {
            System.out.println("Connection Handler: run " + e.getMessage());
        } finally {
            cleanUp();
        }
    }

    private void handleRequest() throws IOException {
        String line = br.readLine();

        if (line != null) {
//            System.out.println(line);
            Logging.logRequest(line);
            new Respond(this.conn, this.dir, line);
        } else {
            throw new DirectoryNotEmptyException("... clint has closed the connection ...");
        }
    }

    private void cleanUp() {
        System.out.println("Connection Handler: ... Cleaning up and exiting ...");
        try {
            br.close();
            is.close();
            conn.close();
        } catch (IOException ioe) {
            System.out.println("Connection Handler: clean up " + ioe.getMessage());
        }

    }
}
