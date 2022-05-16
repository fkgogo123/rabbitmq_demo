package workqueue;

import java.util.logging.Logger;

public class LoggerTest {
    public static void main(String[] args) {

        // Logger logger = LoggerFactory.getLogger(Work01.class.getName());
        // logger.info("123");
        Logger logger = Logger.getLogger("1");
        logger.info("123");

    }
}
