package org.firstinspires.ftc.teamcode;

import
        com.qualcomm.hardware.bosch.BNO055IMU;

import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


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
    public int numberOfRings = -1;
    public final int INCHES_TO_TICKS = 100;
    BNO055IMU imu;
    Orientation angles;


    public autonfunctionsR(DcMotor leftBackDrive, DcMotor rightBackDrive,
                           DcMotor leftFrontDrive, DcMotor rightFrontDrive,
                           DcMotor conveyorMotor, DcMotor shooterMotor, DcMotor intakeMotor, CRServoImpl shooterAngle ){
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
        this.numberOfRings = numberOfRings;
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        conveyorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }
    public void moveForward(double inches){

        runEncoders();
        int ticks =  (int)(INCHES_TO_TICKS * inches);
        stopAndResetEncoders();
        leftBackDrive.setTargetPosition(ticks);
        leftFrontDrive.setTargetPosition(ticks);
        rightBackDrive.setTargetPosition(ticks);
        rightFrontDrive.setTargetPosition(ticks);
        runToPosition();
        leftBackDrive.setPower(1);
        leftFrontDrive.setPower(1);
        rightBackDrive.setPower(1);
        rightFrontDrive.setPower(1);
        while (leftBackDrive.isBusy()||leftFrontDrive.isBusy()||rightBackDrive.isBusy()||rightFrontDrive.isBusy()){
            if (!leftBackDrive.isBusy()){
                leftBackDrive.setPower(0);
            }
            if (!rightBackDrive.isBusy()){
                rightBackDrive.setPower(0);
            }
            if (!leftFrontDrive.isBusy()){
                leftFrontDrive.setPower(0);
            }
            if (!rightFrontDrive.isBusy()){
                rightFrontDrive.setPower(0);
            }
        }
        stop();
    }
    public void moveBack(double inches){
        runEncoders();
        int ticks =  (int)(INCHES_TO_TICKS * inches);
        stopAndResetEncoders();
        leftBackDrive.setTargetPosition(ticks);
        leftFrontDrive.setTargetPosition(ticks);
        rightBackDrive.setTargetPosition(ticks);
        rightFrontDrive.setTargetPosition(ticks);
        runToPosition();
        leftBackDrive.setPower(-1);
        leftFrontDrive.setPower(-1);
        rightBackDrive.setPower(-1);
        rightFrontDrive.setPower(-1);
        while (leftBackDrive.isBusy()||leftFrontDrive.isBusy()||rightBackDrive.isBusy()||rightFrontDrive.isBusy()){
            if (!leftBackDrive.isBusy()){
                leftBackDrive.setPower(0);
            }
            if (!rightBackDrive.isBusy()){
                rightBackDrive.setPower(0);
            }
            if (!leftFrontDrive.isBusy()){
                leftFrontDrive.setPower(0);
            }
            if (!rightFrontDrive.isBusy()){
                rightFrontDrive.setPower(0);
            }
        }
        stop();
    }

    public void moveright(double inches){
        runEncoders();
        int ticks =  (int)(INCHES_TO_TICKS * inches);
        stopAndResetEncoders();
        leftBackDrive.setTargetPosition(ticks);
        leftFrontDrive.setTargetPosition(ticks);
        rightBackDrive.setTargetPosition(ticks);
        rightFrontDrive.setTargetPosition(ticks);
        runToPosition();
        leftBackDrive.setPower(-1);
        leftFrontDrive.setPower(1);
        rightBackDrive.setPower(1);
        rightFrontDrive.setPower(-1);
        while (leftBackDrive.isBusy()||leftFrontDrive.isBusy()||rightBackDrive.isBusy()||rightFrontDrive.isBusy()){
            if (!leftBackDrive.isBusy()){
                leftBackDrive.setPower(0);
            }
            if (!rightBackDrive.isBusy()){
                rightBackDrive.setPower(0);
            }
            if (!leftFrontDrive.isBusy()){
                leftFrontDrive.setPower(0);
            }
            if (!rightFrontDrive.isBusy()){
                rightFrontDrive.setPower(0);
            }
        }
        stop();

    }

    public void moveleft(double inches){
        runEncoders();
        int ticks =  (int)(INCHES_TO_TICKS * inches);
        stopAndResetEncoders();
        leftBackDrive.setTargetPosition(ticks);
        leftFrontDrive.setTargetPosition(ticks);
        rightBackDrive.setTargetPosition(ticks);
        rightFrontDrive.setTargetPosition(ticks);
        runToPosition();
        leftBackDrive.setPower(1);
        leftFrontDrive.setPower(-1);
        rightBackDrive.setPower(-1);
        rightFrontDrive.setPower(1);
        while (leftBackDrive.isBusy()||leftFrontDrive.isBusy()||rightBackDrive.isBusy()||rightFrontDrive.isBusy()){
            if (!leftBackDrive.isBusy()){
                leftBackDrive.setPower(0);
            }
            if (!rightBackDrive.isBusy()){
                rightBackDrive.setPower(0);
            }
            if (!leftFrontDrive.isBusy()){
                leftFrontDrive.setPower(0);
            }
            if (!rightFrontDrive.isBusy()){
                rightFrontDrive.setPower(0);
            }
        }
        stop();

    }


    public void turnRightCenter (double degrees){
        runtime.reset();
        double seconds =  degrees/10;
        leftBackDrive.setPower(1);
        leftFrontDrive.setPower(1);
        rightBackDrive.setPower(-1);
        rightFrontDrive.setPower(-1);
        while (runtime.seconds()< seconds){
        }
        stop();

    }
    public void turnLeftCenter (double degrees){
        runtime.reset();
        double seconds =  degrees/10;
        leftBackDrive.setPower(-1);
        leftFrontDrive.setPower(-1);
        rightBackDrive.setPower(1);
        rightFrontDrive.setPower(1);
        while (runtime.seconds()< seconds){
        }
        stop();

    }
    public void turnRightForwardCenter (double degrees){
        runtime.reset();
        double seconds =  degrees/10;
        leftBackDrive.setPower(1);
        rightBackDrive.setPower(-1);

        while (runtime.seconds()< seconds){
        }
        stop();

    }
    public void turnLeftForwardCenter (double degrees){
        runtime.reset();
        double seconds =  degrees/10;
        leftBackDrive.setPower(-1);
        rightBackDrive.setPower(1);
        while (runtime.seconds()< seconds){
        }
        stop();

    }

    public void shootOneRing (){
        runtime.reset();
        conveyorMotor.setPower(1);
        while (runtime.seconds()<5){
        }
        conveyorMotor.setPower(0);
        runtime.reset();
        shooterMotor.setPower(1);
        while (runtime.seconds()<2){
        }
        shooterMotor.setPower(0);
    }

//    public void readRings (){
//       if (readring ==1){
//           numberOfRings = 1
//       }
//        if (readring ==0){
//            numberOfRings = 0
//        }
//        if (readring ==1){
//            numberOfRings = 4
//        }
//
//    }



    public void stop(){
        leftBackDrive.setPower(0);
        leftFrontDrive.setPower(0);
        rightBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        shooterMotor.setPower(0);
        conveyorMotor.setPower(0);
    }
    public void pause(double seconds){
        while (runtime.seconds()< seconds){
        }
    }

    public void stopAndResetEncoders(){
        leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public void runEncoders(){
        leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void runToPosition(){
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}


