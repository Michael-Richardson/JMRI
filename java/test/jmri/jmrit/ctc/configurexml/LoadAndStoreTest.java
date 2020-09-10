package jmri.jmrit.ctc.configurexml;


import java.io.File;
import java.util.stream.Stream;

import jmri.util.JUnitUtil;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Test that configuration files can be read and then stored again consistently.
 * When done across various versions of schema, this checks ability to read
 * older files in newer versions; completeness of reading code; etc.
 * <p>
 * Functional checks, that e.g. check the details of a specific type are being
 * read properly, should go into another type-specific test class.
 * <p>
 * The functionality comes from the common base class, this is just here to
 * insert the test suite into the JUnit hierarchy at the right place.
 *
 * @author Bob Jacobsen Copyright 2009, 2014
 * @since 2.5.5 (renamed & reworked in 3.9 series)
 */
public class LoadAndStoreTest extends jmri.configurexml.LoadAndStoreTestBase {

    public static Stream<Arguments> data() {
        return getFiles(new File("java/test/jmri/jmrit/ctc/configurexml"), false, true);
    }

    @ParameterizedTest(name = "{index}: {0} (pass={1})")
    @MethodSource("data")
    public void loadAndStoreTest(File file) throws Exception {
        super.loadLoadStoreFileCheck(file);
    }

    @Override
    protected void postLoadProcessing() {
        // 5 second delay so that the Reset sensor gets set to green before the store occurs.
        JUnitUtil.waitFor(5000);
    }

    public LoadAndStoreTest() {
        super(SaveType.User, true);
    }
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LoadAndStoreTest.class);
}
