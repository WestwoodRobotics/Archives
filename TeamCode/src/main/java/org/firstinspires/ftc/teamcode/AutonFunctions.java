package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class AutonFunctions {
    public DcMotor frontLeftDrive;
    public DcMotor frontRightDrive;
    public DcMotor backLeftDrive;
    public DcMotor backRightDrive;

    public ElapsedTime runtime = new ElapsedTime();

    public AutonFunctions(DcMotor fLDrive, DcMotor fRDrive, DcMotor bLDrive, DcMotor bRDrive){
        frontLeftDrive = fLDrive;
        frontRightDrive = fRDrive;
        backLeftDrive = bLDrive;
        backRightDrive = bRDrive;
    }
    //assumes robot moves at 5in per sec
    public void moveForward (double inch, double power) {
        double seconds = inch/5;
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < seconds) {
            frontLeftDrive.setPower(power);
            frontRightDrive.setPower(power);
            backLeftDrive.setPower(power);
            backRightDrive.setPower(power);
        }
        stop();
    }

    public void moveBackward (double inch, double power) {
        double seconds = inch/5;
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < seconds) {
            frontLeftDrive.setPower(-power);
            frontRightDrive.setPower(-power);
            backLeftDrive.setPower(-power);
            backRightDrive.setPower(-power);
        }
        stop();
    }

    public void moveLeft (double inch, double power) {
        double seconds = inch/5;
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < seconds) {
            frontLeftDrive.setPower(-power);
            frontRightDrive.setPower(power);
            backLeftDrive.setPower(power);
            backRightDrive.setPower(-power);
        }
        stop();
    }

    public void moveRight (double inch, double power) {
        double seconds = inch/5;
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < seconds) {
            frontLeftDrive.setPower(power);
            frontRightDrive.setPower(-power);
            backLeftDrive.setPower(-power);
            backRightDrive.setPower(power);
        }
        stop();
    }
    public void moveDiagFL (double inch, double power) {
        double seconds = inch/5;
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < seconds) {
            frontRightDrive.setPower(power);
            backLeftDrive.setPower(power);
        }
        stop();
    }
    public void moveDiagFR (double inch, double power) {
        double seconds = inch/5;
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < seconds) {
            frontLeftDrive.setPower(power);
            backRightDrive.setPower(power);
        }
        stop();
    }
    public void moveDiagBL (double inch, double power) {
        double seconds = inch/5;
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < seconds) {
            frontLeftDrive.setPower(-power);
            backRightDrive.setPower(-power);
        }
        stop();
    }
    public void moveDiagBR (double inch, double power) {
        double seconds = inch/5;
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < seconds) {
            frontRightDrive.setPower(-power);
            backLeftDrive.setPower(-power);
        }
        stop();
    }

    //assumes robot spins at 90 degrees per second
    public void turnCenterClock (double degrees) {
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < (degrees/90)) {
            frontLeftDrive.setPower(1);
            frontRightDrive.setPower(-1);
            backLeftDrive.setPower(-1);
            backRightDrive.setPower(1);
        }
    }

    public void turnCenterCounterClock (double degrees) {
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < (degrees/90)) {
            frontLeftDrive.setPower(-1);
            frontRightDrive.setPower(1);
            backLeftDrive.setPower(1);
            backRightDrive.setPower(-1);
        }
    }

    public void shoot() {
        //turn blocker servo 90 degrees
        //start spinning the shooter motor
        //turn the shooter push servo 60 degrees and then back 60 degrees
    }

    public void stop() {
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }
}
