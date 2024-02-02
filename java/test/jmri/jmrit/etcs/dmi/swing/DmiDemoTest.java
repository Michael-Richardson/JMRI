package jmri.jmrit.etcs.dmi.swing;

import jmri.util.JUnitUtil;
import jmri.util.swing.JemmyUtil;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.netbeans.jemmy.operators.*;

/**
 * Tests for DmiDemo.
 * @author Steve Young Copyright (C) 2024
 */
@DisabledIfSystemProperty(named = "java.awt.headless", matches = "true")
public class DmiDemoTest {

    @Test
    public void testDemo(){

        DmiDemo.setDelayMultiplier(0);

        DmiFrame df = new DmiFrame("DmiDemoTest");
        DmiPanel p = df.getDmiPanel();
        Assertions.assertNotNull(p);
        df.setVisible(true);
        JFrameOperator jfo = new JFrameOperator(df.getTitle());

        DmiDemo d = new DmiDemo(p);
        d.runDemo();

        JLabelOperator label1oper = JemmyUtil.getLabelOperatorByName(jfo, "msglabel1");
        JUnitUtil.waitFor( () -> label1oper.getText().equals("Demo Complete"), "Demo Complete");

        jfo.requestClose();
        jfo.waitClosed();
    }

    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();
        jmri.jmrit.etcs.ResourceUtil.setInTest(true);
    }

    @AfterEach
    public void tearDown() {
        JUnitUtil.tearDown();
    }

}
