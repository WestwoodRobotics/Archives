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
    public int encoderCountsTurning;
    public int encoderCountsCircle;

    public final int ENCODER_COUNTS_WHEEL_ROTATION = 28;
    public double wheelCircumference;

    public final double BLOCKER_OPEN_POSITION;
    public final double BLOCKER_CLOSED_POSITION;
    public final double PUSHER_OPEN_POSITION;
    public final double PUSHER_CLOSED_POSITION;

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


        encoderCountsCircle = 10; //Will change once we do some testing for actual value
        wheelCircumference = 9.435;

        BLOCKER_OPEN_POSITION = 0.5;
        BLOCKER_CLOSED_POSITION = 0;
        PUSHER_OPEN_POSITION = 0.333;
        PUSHER_CLOSED_POSITION = 0;
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

    public void move(double in, double fL, double fR, double bL, double bR) {//When motors go backwards encoder values decrease instead of increasing
        encoderCounts = (int) frontLeftDrive.getCurrentPosition() +
                (int) Math.rint(ENCODER_COUNTS_WHEEL_ROTATION * in / wheelCircumference);

        while (frontLeftDrive.getCurrentPosition() < encoderCounts) {
            frontLeftDrive.setPower(fL);
            frontRightDrive.setPower(fR);
            backLeftDrive.setPower(bL);
            frontLeftDrive.setPower(bR);
        }

        stop();
    }


    public void moveForward(double inches) {
        move(inches,1,1,1,1);
    }

    public void moveBackwards(double inches) {
        move(inches,-1,-1,-1,-1);
    }

    public void moveLeft(double inches) {
        move(inches,-1,1,1,-1);
    }

    public void moveRight(double inches) {
        move(inches,1,-1,-1,1);
    }

    public void moveFL(double inches) { //Will not work because encoder checks are based on front left motor which is not moving
        move(inches,0,1,1,0); //For diagonal movement unsure if motors set to 0 will brake and break something
    }

    public void moveFR(double inches) {
        move(inches,1,0,0,1);
    }

    public void moveBL(double inches) {
        move(inches,-1,0,0,-1);
    }

    public void moveBR(double inches) {
        move(inches,0,-1,-1,0);
    }

    public void turnClockwise(double deg) {
        encoderCountsTurning = (int) frontLeftDrive.getCurrentPosition() + (int) (encoderCountsCircle * (deg/360));

        while(frontLeftDrive.getCurrentPosition() < encoderCountsTurning) {
            frontLeftDrive.setPower(1);
            frontRightDrive.setPower(-1);
            backLeftDrive.setPower(1);
            backRightDrive.setPower(-1);
        }
    }

    public void turnCounterClockwise(double deg) {
        encoderCountsTurning = (int) frontRightDrive.getCurrentPosition() + (int) (encoderCountsCircle * (deg/360));

        while(frontRightDrive.getCurrentPosition() < encoderCountsTurning) {
            frontLeftDrive.setPower(-1);
            frontRightDrive.setPower(1);
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
        shooterPusher.setPosition(PUSHER_CLOSED_POSITION);
        //turn blocker servo 90 degrees counter clockwise
        shooterBlocker.setPosition(BLOCKER_CLOSED_POSITION);
        //stop spinning the shooter motor
        shooterMotor.setPower(0);
    }

    public void shoot3Times() {
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

    public void angleShooter(double degrees) {
        //Method to angle the shooter to a desired angle
    }
}