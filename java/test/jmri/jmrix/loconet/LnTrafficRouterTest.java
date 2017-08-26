package jmri.jmrix.loconet;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;

/**
 * Generated by JBuilder
 * <p>
 * Title: LnTrafficRouterTest </p>
 * <p>
 * Description: </p>
 * <p>
 * Copyright: Copyright (c) 2002</p>
 *
 * @author Bob Jacobsen
 */
public class LnTrafficRouterTest extends TestCase {

    public LnTrafficRouterTest(String s) {
        super(s);
    }

    public void testConnectAndSend() {
        // scaffold for upstream
        LocoNetInterfaceScaffold upstream = new LocoNetInterfaceScaffold();

        // create object
        LnTrafficRouter router = new LnTrafficRouter();
        Assert.assertEquals("router is instance", LnTrafficController.instance(), router);

        // connect
        router.connect(upstream);
        Assert.assertTrue("connected", router.status());

        // send a message
        LocoNetMessage m = new LocoNetMessage(3);
        router.sendLocoNetMessage(m);

        // check receipt
        Assert.assertEquals("one message sent", 1, upstream.outbound.size());
        Assert.assertTrue(upstream.outbound.elementAt(0) == m);
    }

    static int count = 0;

    public void testReceiveAndForward() {
        // create object
        LnTrafficRouter router = new LnTrafficRouter();
        Assert.assertEquals("router is instance", LnTrafficController.instance(), router);

        count = 0;
        // register a listener
        LocoNetListener l = new LocoNetListener() {
            @Override
            public void message(LocoNetMessage m) {
                count++;
            }
        };
        router.addLocoNetListener(~0, l);
        // send a message
        LocoNetMessage m = new LocoNetMessage(3);
        router.message(m);

        // check receipt
        Assert.assertEquals("one message sent", 1, count);
    }

    public void testConnectAndDisconnect() {
        // scaffold for upstream
        LocoNetInterfaceScaffold upstream = new LocoNetInterfaceScaffold();

        // create object
        LnTrafficRouter router = new LnTrafficRouter();
        Assert.assertEquals("router is instance", LnTrafficController.instance(), router);

        // connect
        router.connect(upstream);
        Assert.assertTrue("connected", router.status());

        // disconnect
        router.disconnectPort(upstream);
        Assert.assertTrue("not connected", !router.status());
    }

    // Main entry point
    static public void main(String[] args) {
        String[] testCaseName = {LnTrafficRouterTest.class.getName()};
        junit.textui.TestRunner.main(testCaseName);
    }

    // test suite from all defined tests
    public static Test suite() {
        TestSuite suite = new TestSuite(LnTrafficRouterTest.class);
        return suite;
    }

    // The minimal setup for log4J
    @Override
    protected void setUp() {
        apps.tests.Log4JFixture.setUp();
    }

    @Override
    protected void tearDown() {
        apps.tests.Log4JFixture.tearDown();
    }

}
