package org.warriors2583.frc2014;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Austin Reuland
 */
public class SS_Sensors extends Subsystem implements RMap {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private static final Ultrasonic m_ultrasonic;
    
    private static ITable m_table;
    
	private static final SS_Sensors instance = new SS_Sensors();

	public static SS_Sensors getInstance(){
		return instance;
	}

	static{
        m_ultrasonic = new Ultrasonic(DIO_SENSOR_ULTRASONIC_TRIGGER,
                DIO_SENSOR_ULTRASONIC_ECHO, Ultrasonic.Unit.kInches);
        
        m_ultrasonic.setAutomaticMode(true);

		
	}

	private SS_Sensors(){
		super("SS_Sensors");
        initSensorsTable(NetworkTable.getTable(NETTABLE_ROBOT_TABLE).getSubTable(NETTABLE_SENSORS));
	}
    
    public static double getDistance(){
        return m_ultrasonic.getRangeInches();
    }
    
    public static void updateValues(){
        m_table.putNumber(NETTABLE_SENSORS_ULTRASONIC, m_ultrasonic.getRangeInches());
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_UpdateSensors());
    }
    
    private static void initSensorsTable(ITable subtable){
        m_table = subtable;
        m_table.putNumber(NETTABLE_SENSORS_ULTRASONIC, m_ultrasonic.getRangeInches());
        
    }
}
