package jmri.jmrit.operations.rollingstock.engines;

import java.awt.Dimension;
import java.util.List;
import java.util.ResourceBundle;

import jmri.InstanceManager;
import jmri.jmrit.operations.OperationsXml;
import jmri.jmrit.operations.rollingstock.RollingStockSetFrame;
import jmri.jmrit.operations.setup.Control;
import jmri.util.swing.JmriJOptionPane;

/**
 * Frame for user to place engine on the layout
 *
 * @author Dan Boudreau Copyright (C) 2008, 2010
 */
public class EngineSetFrame extends RollingStockSetFrame<Engine> {

    protected static final ResourceBundle rb = ResourceBundle
            .getBundle("jmri.jmrit.operations.rollingstock.engines.JmritOperationsEnginesBundle");

    EngineManager manager = InstanceManager.getDefault(EngineManager.class);
    EngineManagerXml managerXml = InstanceManager.getDefault(EngineManagerXml.class);

    Engine _engine;

    public EngineSetFrame() {
        super(Bundle.getMessage("TitleEngineSet"));
    }

    @Override
    public void initComponents() {
        super.initComponents();

        // build menu
        addHelpMenu("package.jmri.jmrit.operations.Operations_LocomotivesSet", true); // NOI18N

        // disable location unknown, final destination fields
        locationUnknownCheckBox.setVisible(false);
        paneOptional.setVisible(false);
        pFinalDestination.setVisible(false);
        autoTrainCheckBox.setVisible(false);

        // tool tips
        outOfServiceCheckBox.setToolTipText(getRb().getString("TipLocoOutOfService"));

        initMinimumSize(new Dimension(Control.panelWidth500, Control.panelHeight300));
    }

    public void load(Engine engine) {
        _engine = engine;
        super.load(engine);
    }

    @Override
    protected ResourceBundle getRb() {
        return rb;
    }

    @Override
    protected boolean save() {
        if (!super.save()) {
            return false;
        }
        // check for train change
        checkTrain(_engine);
        // is this engine part of a consist?
        if (_engine.getConsist() != null) {
            if (JmriJOptionPane.showConfirmDialog(this, Bundle.getMessage("engineInConsist"),
                    Bundle.getMessage("enginePartConsist"), JmriJOptionPane.YES_NO_OPTION) == JmriJOptionPane.YES_OPTION) {
                // convert cars list to rolling stock list
                List<Engine> list = _engine.getConsist().getEngines();
                if (!updateGroup(list)) {
                    return false;
                }
            }
        }
        OperationsXml.save();
        return true;
    }

//    private final static Logger log = LoggerFactory.getLogger(EngineSetFrame.class);
}
