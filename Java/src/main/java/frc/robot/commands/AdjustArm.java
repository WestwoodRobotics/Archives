/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.subsystems.Arm;

public class AdjustArm extends Command {
  public Arm arm = Arm.getInstance();

  //public RobotMap.ArmEnum v = RobotMap.ArmEnum.OFF;

  public AdjustArm(RobotMap.ArmEnum v) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(arm);

    //this.v = v;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    /*if(v == RobotMap.ArmEnum.UP){
      arm.setArmSpeed(.75);
    }
    else if(v == RobotMap.ArmEnum.DOWN){
      if(arm.getPowerMode())
        arm.setArmSpeed(-.6);
      else
        arm.setArmSpeed(-.2);
      //arm.setArmSpeed(-.3);
    }
    else if(v == RobotMap.ArmEnum.OFF){
      arm.brakeArm();
    }*/
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    arm.brakeArm();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
