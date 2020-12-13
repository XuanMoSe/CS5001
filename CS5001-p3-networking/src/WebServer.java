import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Creat a web server.
 * @author 200009834
 */
public class WebServer {
    private ServerSocket ss;

    /**
     * Multi thread is using here.
     * @param dir directory of web page.
     * @param port localhost:port number.
     */
    public WebServer(String dir, int port) {
        //reduce waste, improve performance.
        ExecutorService threadPool = null;
        try {
            ss = new ServerSocket(port);
            System.out.println("Web server started ... ...\n" + "Listening on port " + port + "\n");
            threadPool = Executors.newFixedThreadPool(ConfigurationParameter.MAX_THREAD);
            while (true) {
                Socket conn = ss.accept();
                System.out.println("Server got a new connection request from "
                        + Objects.requireNonNull(conn).getInetAddress());
                ConnectionHandler ch = new ConnectionHandler(conn, dir);
                threadPool.submit(ch);
//                System.out.println(conn);
                System.out.println(threadPool);
            }
        } catch (IOException ioe) {
            System.out.println("Sorry. " + ioe.getLocalizedMessage());
            //shut down thread.
            Objects.requireNonNull(threadPool).shutdown();
        }
    }
}
