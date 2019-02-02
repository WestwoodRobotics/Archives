/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.subsystems.PistonLift;

public class AdjustLift extends Command {
  
  public PistonLift pl = PistonLift.getInstance();
  
  private boolean frontSol;
  private boolean backSol;

  private RobotMap.LiftMode mode = RobotMap.LiftMode.SET;

  public AdjustLift(RobotMap.LiftMode mode, boolean frontSol, boolean backSol) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(pl);

    this.mode = mode;

    if(mode == RobotMap.LiftMode.SET){
      this.frontSol = frontSol;
      this.backSol = backSol;
    }
  }

  public AdjustLift(RobotMap.LiftMode mode){
    this.mode = mode;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    if(mode == RobotMap.LiftMode.SET){
      pl.setFrontSol(frontSol);
      pl.setBackSol(backSol);
    }
    else if(mode == RobotMap.LiftMode.TOGGLE_FRONT){
      pl.toggleFrontSol();
    }
    else if(mode == RobotMap.LiftMode.TOGGLE_BACK){
      pl.toggleBackSol();
    }
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
