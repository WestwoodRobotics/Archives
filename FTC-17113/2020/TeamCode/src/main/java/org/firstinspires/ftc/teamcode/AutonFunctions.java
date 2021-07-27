package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;

public class AutonFunctions {
    public DcMotor frontLeftDrive;
    public DcMotor frontRightDrive;
    public DcMotor backLeftDrive;
    public DcMotor backRightDrive;
    public Servo shooterBlocker;
    public Servo shooterPusher;
    public DcMotor shooterMotor;
    public DcMotor shooterAngler;

    public final double BLOCKER_OPEN_POSITION = 0.5;
    public final double BLOCKER_CLOSED_POSITION = 0;
    public final double PUSHER_OPEN_POSITION = 0.333;
    public final double PUSHER_CLOSED_POSITION = 0;

    public ElapsedTime runtime = new ElapsedTime();

    public AutonFunctions(DcMotor fLDrive, DcMotor fRDrive, DcMotor bLDrive, DcMotor bRDrive, Servo shootB, Servo shootP, DcMotor shootM, DcMotor shootA){
        frontLeftDrive = fLDrive;
        frontRightDrive = fRDrive;
        backLeftDrive = bLDrive;
        backRightDrive = bRDrive;
        shooterBlocker = shootB;
        shooterPusher = shootP;
        shooterMotor = shootM;
        shooterAngler = shootA;
    }
    //waits for a given time
    public void pause (double seconds) {
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < seconds) {}
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
    public void turnFrontCenterClock (double degrees) {
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < (degrees/90)) {
            backLeftDrive.setPower(1);
            backRightDrive.setPower(-1);
        }
    }

    public void turnFrontCenterCounterClock (double degrees) {
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < (degrees/90)) {
            backLeftDrive.setPower(-1);
            backRightDrive.setPower(1);
        }
    }

    public void shoot() {
        //start spinning the shooter motor
        shooterMotor.setPower(1);
        //turn blocker servo 90 degrees
        shooterBlocker.setPosition(BLOCKER_OPEN_POSITION);
        //turn the shooter push servo 60 degrees and then back 60 degrees
        shooterPusher.setPosition(PUSHER_OPEN_POSITION);
        //delay
        pause(0.5);
        //
        shooterPusher.setPosition(PUSHER_CLOSED_POSITION);
        //turn blocker servo 90 degrees counter clockwise
        shooterBlocker.setPosition(BLOCKER_CLOSED_POSITION);
        //stop spinning the shooter motor
        shooterMotor.setPower(0);
    }

    public void shoot3times() {
        //start spinning the shooter motor
        shooterMotor.setPower(1);
        //turn blocker servo 90 degrees
        shooterBlocker.setPosition(BLOCKER_OPEN_POSITION);
        //turn the shooter push servo 60 degrees and then back 60 degrees
        for (int i = 0; i < 3 ; i++ ) {
            shooterPusher.setPosition(PUSHER_OPEN_POSITION);
            //delay
            pause(1);

            shooterPusher.setPosition(PUSHER_CLOSED_POSITION);
        }
        //turn blocker servo 90 degrees counter clockwise
        shooterBlocker.setPosition(BLOCKER_CLOSED_POSITION);
        //stop spinning the shooter motor
        shooterMotor.setPower(0);
    }

    public void stop() {
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }
}
