package jmri.jmrix.powerline.dmx512;

import jmri.util.JUnitUtil;

import org.junit.Assert;
import org.junit.jupiter.api.*;

/**
 * Tests for SpecificLight class.
 *
 * @author Paul Bender Copyright (C) 2016
 **/

public class SpecificLightTest {

   private SpecificTrafficController tc = null;

   @Test
   public void ConstructorTest(){
      Assert.assertNotNull("SpecificLight constructor",new SpecificLight("PL1",tc));
      Assert.assertNotNull("SpecificLight constructor",new SpecificLight("PL256",tc));
      Assert.assertNotNull("SpecificLight constructor",new SpecificLight("PL512",tc));
      Assert.assertNull("SpecificLight constructor",new SpecificLight("PL0",tc));
      Assert.assertNull("SpecificLight constructor",new SpecificLight("PL513",tc));
      Assert.assertNull("SpecificLight constructor",new SpecificLight("PLA1",tc));
      Assert.assertNull("SpecificLight constructor",new SpecificLight("PL",tc));
      Assert.assertNull("SpecificLight constructor",new SpecificLight("PL01.02.03",tc));
      Assert.assertNull("SpecificLight constructor",new SpecificLight("PLA1.A2.03",tc));
   }

   @BeforeEach
   public void setUp() {
        JUnitUtil.setUp();

        jmri.util.JUnitUtil.initDefaultUserMessagePreferences();
        SpecificSystemConnectionMemo memo = new SpecificSystemConnectionMemo();
        tc = new SpecificTrafficController(memo);
        memo.setTrafficController(tc);
        memo.configureManagers();
        memo.setSerialAddress(new jmri.jmrix.powerline.SerialAddress(memo));
   }

   @AfterEach
   public void tearDown(){
        JUnitUtil.clearShutDownManager(); // put in place because AbstractMRTrafficController implementing subclass was not terminated properly
        JUnitUtil.tearDown();

        tc = null;
   }

}
