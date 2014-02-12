package org.warriors2583.lib.limitswitch;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.parsing.IInputOutput;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Austin Reuland
 */
public class LimitSwitch implements IStandardSwitch, IInputOutput, LiveWindowSendable {
    
    public static class SwitchType{
        private final int type;
        private SwitchType(int type){
            this.type = type;
        }
        public int getType(){
            return type;
        }
        public boolean getValue(boolean value){
            return ((type == 1 && value) || (type == 2 && !value));
        }
        /**
         * Normally Open Switch
         */
        public static final SwitchType NO = new SwitchType(1);
        /**
         * Normally Closed Switch
         */
        public static final SwitchType NC = new SwitchType(2);
    }
    
    private DigitalInput m_limitSwitch;
    private SwitchType m_type;
    
    public LimitSwitch(int channel, SwitchType type){
        this(1, channel, type);
    }
    
    public LimitSwitch(int module, int channel, SwitchType type){
        m_limitSwitch = new DigitalInput(module, channel);
        this.m_type = type;
    }
    
    public boolean state() {
        return m_type.getValue(m_limitSwitch.get());
    }

    public int getChannel() {
        return m_limitSwitch.getChannel();
    }

    public SwitchType getType() {
        return m_type;
    }

    /**
     * {@inheritDoc}
     */
    public void updateTable() {
        m_limitSwitch.updateTable();
    }

    /**
     * {@inheritDoc}
     */
    public void startLiveWindowMode() {
        m_limitSwitch.startLiveWindowMode();
    }

    /**
     * {@inheritDoc}
     */
    public void stopLiveWindowMode() {
        m_limitSwitch.stopLiveWindowMode();
    }

    /**
     * {@inheritDoc}
     */
    public void initTable(ITable arg0) {
        m_limitSwitch.initTable(arg0);
    }

    /**
     * {@inheritDoc}
     */
    public ITable getTable() {
        return m_limitSwitch.getTable();
    }

    /**
     * {@inheritDoc}
     */
    public String getSmartDashboardType() {
        return m_limitSwitch.getSmartDashboardType();
    }

}
