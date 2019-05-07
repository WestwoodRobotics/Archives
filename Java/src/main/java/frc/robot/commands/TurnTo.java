/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveTrain;

public class TurnTo extends Command {
  
  public DriveTrain dt_s = DriveTrain.getInstance();
  
  private PIDController pid;

  private static double P = 0,
                        I = 0,
                        D = 0;

  public TurnTo(double degrees){
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(dt_s);

    pid = new PIDController(P, I, D, new PIDSource(){
      PIDSourceType source= PIDSourceType.kDisplacement;
      
      @Override
      public double pidGet(){
        return dt_s.getZHeading();
      }

      @Override
      public void setPIDSourceType(PIDSourceType pidSource) {
        source = pidSource;
      }

      @Override
      public PIDSourceType getPIDSourceType() {
        return source;
      }

    }, d -> dt_s.turnRate(d));

    pid.setInputRange(-720, 720);
    pid.setOutputRange(-0.5, 0.5);
    pid.setPercentTolerance(.1);
    pid.setSetpoint(degrees);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    dt_s.resetIMU();
    pid.reset();
    pid.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return pid.onTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    pid.disable();
    dt_s.stopWheels();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    pid.disable();
    dt_s.stopWheels();
  }
}
