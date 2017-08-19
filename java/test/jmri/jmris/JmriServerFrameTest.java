//JmriServerFrameTest.java
package jmri.jmris;

import apps.tests.Log4JFixture;
import java.awt.GraphicsEnvironment;
import org.junit.After;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the jmri.jmris.JmriServerFrame class
 *
 * @author Paul Bender
 */
public class JmriServerFrameTest {

    @Test
    public void testCtorDefault() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        JmriServerFrame a = new JmriServerFrame();
        Assert.assertNotNull(a);
        a.dispose();
    }

    // The minimal setup for log4J
    @Before
    public void setUp() throws Exception {
        Log4JFixture.setUp();
    }

    @After
    public void tearDown() throws Exception {
        Log4JFixture.tearDown();
    }

}
