package singleton;

/**
 * Created by dstoianov on 2015-05-27.
 */

import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.log4j.Logger;

public class SingletonTest extends TestCase {

    private ClassicSingleton sone = null, stwo = null;
    private static Logger logger = Logger.getRootLogger();

    public SingletonTest(String name) {
        super(name);
    }

    public void setUp() {
        logger.info("getting singleton...");
        sone = ClassicSingleton.getInstance();
        logger.info("...got singleton: " + sone);
        logger.info("getting singleton...");
        stwo = ClassicSingleton.getInstance();
        logger.info("...got singleton: " + stwo);
    }

    public void testUnique() {
        logger.info("checking singletons for equality");
        Assert.assertEquals(true, sone == stwo);
    }
}

