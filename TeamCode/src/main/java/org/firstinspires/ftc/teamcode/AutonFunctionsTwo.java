package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;

public class AutonFunctionsTwo {
    public DcMotor frontLeftDrive;
    public DcMotor frontRightDrive;
    public DcMotor backLeftDrive;
    public DcMotor backRightDrive;

    public DcMotor shooterMotor;
    public DcMotor shooterAngler;

    public Servo shooterBlocker;
    public Servo shooterPusher;

    public int encoderCounts;

    public double wheelCircumference;

    public ElapsedTime runtime = new ElapsedTime();


    public AutonFunctionsTwo(DcMotor fLDrive, DcMotor fRDrive, DcMotor bLDrive, DcMotor bRDrive, Servo shootB, Servo shootP, DcMotor shootM, DcMotor shootA){
        frontLeftDrive = fLDrive;
        frontRightDrive = fRDrive;
        backLeftDrive = bLDrive;
        backRightDrive = bRDrive;
        shooterBlocker = shootB;
        shooterPusher = shootP;
        shooterMotor = shootM;
        shooterAngler = shootA;

        wheelCircumference = 9.435;
    }


    public void pause(double seconds) {
        double startTime = runtime.seconds();

        while(runtime.seconds() - startTime < seconds) {}
    }


    public void stop() {
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }


    public void moveForward(double inches) {
        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        encoderCounts = (int) Math.rint(28 * inches / wheelCircumference);

        frontLeftDrive.setTargetPosition(encoderCounts);
        frontRightDrive.setTargetPosition(encoderCounts);
        backLeftDrive.setTargetPosition(encoderCounts);
        backRightDrive.setTargetPosition(encoderCounts);

        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}