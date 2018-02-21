package org.usfirst.frc.team2583.robot.commands.auto;

import org.usfirst.frc.team2583.robot.RobotMap;
import org.usfirst.frc.team2583.robot.commands.DriveDistance;
import org.usfirst.frc.team2583.robot.commands.OperateIntake;
import org.usfirst.frc.team2583.robot.commands.ShiftGears;
import org.usfirst.frc.team2583.robot.commands.TurnTo;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2583.robot.subsystems.DriveTrain;
/**
 



* Autonomous mode switch placer: center -> left
 */
public class SwitchCL extends CommandGroup {

    public SwitchCL() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	if(DriveTrain.getInstance().getGear() == true){
    		addSequential(new ShiftGears());
    	}
    	
    	addSequential(new DriveDistance(84)); //Drive half the distance towards the switch
    	addSequential(new TurnTo(-90)); //Turn Left
    	addSequential(new DriveDistance(82.5)); //Drive a bit past end of switch
    	addSequential(new TurnTo(90)); //Turn Right
    	addSequential(new DriveDistance(84)); //Drive to switch
    	addSequential(new TurnTo(90)); // Turn Right
    	addSequential(new OperateIntake(RobotMap.Take.OUT)); //Push PowerCube(tm) Out
    	
    }
}
