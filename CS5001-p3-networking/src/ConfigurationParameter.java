import java.io.File;
/**
 * This is a configuration class. Save normal value.
 * @author 200009834
 */
public abstract class ConfigurationParameter {
    /**
     * max client allowed.
     */
    public static final int MAX_THREAD = 10;
    /**
     * time out setting.
     */
    public static final int TIME_OUT = 10000;
    /**
     * byte array size.
     */
    public static final int BYTE_SIZE = 1500;
    static final File LOG_FILE = new File("log.txt");
    static final String BREAKER = "\n===========================================\n";
}
