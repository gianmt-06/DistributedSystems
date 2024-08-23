package gian.utils.Logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogsManager {
    public static void log(String className, String message, Object data){
        Logger logger = LogManager.getLogger(className);
        logger.info(message + "\nDATA: " + data.toString());
    }

    public static void log(String className, String message){
        Logger logger = LogManager.getLogger(className);
        logger.info(message);
    }
}
