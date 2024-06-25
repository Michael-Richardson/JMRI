package jmri.jmrix.bachrus.speedmatcher.basic;

import javax.swing.JButton;
import javax.swing.JLabel;

import jmri.DccLocoAddress;
import jmri.PowerManager;
import jmri.jmrix.bachrus.Speed;
import jmri.jmrix.bachrus.speedmatcher.SpeedMatcherConfig;

import org.slf4j.Logger;

/**
 * Configuration data for a basic speed matcher
 *
 * @author Todd Wegter Copyright (C) 2024
 */
public class BasicSpeedMatcherConfig extends SpeedMatcherConfig{
    
    //<editor-fold defaultstate="collapsed" desc="Enums">
    public enum SpeedTable {
        SIMPLE, ADVANCED, ESU
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Instance Variables">
    public float targetStartSpeed;
    public float targetTopSpeed;
    //</editor-fold>
    
    public BasicSpeedMatcherConfig(
            DccLocoAddress address, 
            float targetStartSpeed,
            float targetTopSpeed,
            Speed.Unit speedUnit, 
            boolean trimReverseSpeed, 
            int warmUpForwardSeconds, 
            int warmUpReverseSeconds, 
            PowerManager powerManager,
            Logger logger, 
            JLabel statusLabel, 
            JButton startStopButton)
    {
        super(address, speedUnit, trimReverseSpeed, warmUpForwardSeconds, warmUpReverseSeconds, powerManager, logger, statusLabel, startStopButton);
        
        this.targetStartSpeed = targetStartSpeed;
        this.targetTopSpeed = targetTopSpeed;
    }

}
