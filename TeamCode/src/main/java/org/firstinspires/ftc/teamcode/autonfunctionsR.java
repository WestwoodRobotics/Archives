package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class autonfunctionsR {
    public ElapsedTime runtime = new ElapsedTime();
    public DcMotor leftBackDrive = null;
    public DcMotor rightBackDrive = null;
    public DcMotor leftFrontDrive = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor conveyorMotor = null;
    public DcMotor shooterMotor = null;
    public DcMotor intakeMotor = null;
    public CRServoImpl wobbleClaw = null;
    public CRServoImpl wobbleClaw2 = null;
    public CRServoImpl shooterAngle = null;

    public autonfunctionsR(DcMotor leftBackDrive, DcMotor rightBackDrive,
                           DcMotor leftFrontDrive, DcMotor rightFrontDrive,
                           DcMotor conveyorMotor, DcMotor shooterMotor, DcMotor intakeMotor,
                           CRServoImpl wobbleClaw, CRServoImpl wobbleClaw2, CRServoImpl shooterAngle, ElapsedTime runtime){
        this.runtime= runtime;
        this.leftBackDrive= leftBackDrive;
        this.rightBackDrive= rightBackDrive;
        this.leftFrontDrive= leftFrontDrive;
        this.rightFrontDrive= rightFrontDrive;
        this.conveyorMotor= conveyorMotor;
        this.shooterMotor= shooterMotor;
        this.intakeMotor= intakeMotor;
        this.wobbleClaw= wobbleClaw;
        this.wobbleClaw2= wobbleClaw2;
        this.shooterAngle= shooterAngle;
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        conveyorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }
    public void moveForward(double inches){
        runtime.reset();
        double seconds =  5/36 * inches;
        leftBackDrive.setPower(1);
        leftFrontDrive.setPower(1);
        rightBackDrive.setPower(1);
        rightFrontDrive.setPower(1);
        while (runtime.seconds() < seconds){
        }
        stop();
    }
    public void moveBack(double inches){
        runtime.reset();
        double seconds = 5/36 * inches;
        leftBackDrive.setPower(-1);
        leftFrontDrive.setPower(-1);
        rightBackDrive.setPower(-1);
        rightFrontDrive.setPower(-1);
        while (runtime.seconds()< seconds){
        }
        stop();

    }
    public void turnRight (double inches){
        runtime.reset();
        double seconds = 5/36 * inches;
        leftBackDrive.setPower(1);
        leftFrontDrive.setPower(1);
        rightBackDrive.setPower(-1);
        rightFrontDrive.setPower(-1);
        while (runtime.seconds()< seconds){
        }
        stop();

    }


    public void stop(){
        leftBackDrive.setPower(0);
        leftFrontDrive.setPower(0);
        rightBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
    }
    public void wait(double seconds){
        while (runtime.seconds()< seconds){
        }
    }
}


