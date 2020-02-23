/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.PowerCellConstants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * This subsystem is responsible for intaking the PowerCells to the
 * BallMagazine
 */

public class BallIntake extends SubsystemBase {
  
  //--------------------------------------------------------------------------------------------------
  //Variables/Features of BallIntake                                         // TODO: Change motor types************************

  private CANSparkMax intakeMotor1 = new CANSparkMax(P_INTAKE_spMAX_1, MotorType.kBrushless);   
  private CANSparkMax intakeMotor2 = new CANSparkMax(P_INTAKE_spMAX_2, MotorType.kBrushless);
  
  private Solenoid intakeSol1 = new Solenoid(P_INTAKE_sol_1);
  private Solenoid intakeSol2 = new Solenoid(P_INTAKE_sol_2);

  //--------------------------------------------------------------------------------------------------
  // Constructor
  public BallIntake() {
    intakeMotor2.follow(intakeMotor1, true);
    intakeSol1.set(false);
    intakeSol2.set(false);
  }

  //--------------------------------------------------------------------------------------------------
  // Intake Methods
  public void intakeBall(){
    intakeMotor1.set(-C_INTAKE_SPEED);
  }

  public void extend(){
    intakeSol1.set(true);
    intakeSol2.set(true);
  }

  public void contract(){
    intakeSol1.set(false);
    intakeSol2.set(false);
  }

  public void stopBall(){
    intakeMotor1.stopMotor();
  }

  //--------------------------------------------------------------------------------------------------
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
