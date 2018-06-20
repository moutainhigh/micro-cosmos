package storm;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author:thomas
 * @Date: 2018/4/4 15:19
 * @Description:
 */
public class TestGrepConsole {
    private static final Logger logger = LoggerFactory.getLogger(TestGrepConsole.class);

    public static void main(String[] args){
        logger.error("this is an error");
        logger.warn("this is a warn");
        logger.info("this is an info");
        logger.debug("this is a debug");
    }


}
