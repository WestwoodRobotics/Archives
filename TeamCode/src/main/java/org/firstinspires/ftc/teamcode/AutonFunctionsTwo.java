package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;

public class AutonFunctionsTwo {
    //Drive train motors
    public DcMotor frontLeftDrive;
    public DcMotor frontRightDrive;
    public DcMotor backLeftDrive;
    public DcMotor backRightDrive;

    //Servos and motors involving the shooter
    public DcMotor shooterMotor;
    public DcMotor shooterAngler;

    public Servo shooterBlocker;
    public Servo shooterPusher;

    public Servo clawServo;

    public int encoderCounts; //Variable we use in our move functions to store what the encoder counts should be at once we finish moving the desired distance

    public final int ENCODER_COUNTS_WHEEL_ROTATION = 28;
    public double wheelCircumference;
    public final double P_CONST = 0; //not found yet
    public final double D_CONST = 0; //not found yet

    private boolean isClawOpen = true;
    private double clawServoPosition;
    private double clawOpenPosition = 0.5; //Needs testing for accurate value
    private double clawClosedPosition = 0;

    //Constants representing the servo positions for the shooter blocker and pusher (needs testing for accurate positions)
    public final double BLOCKER_OPEN_POSITION = .5;
    public final double BLOCKER_CLOSED_POSITION = 0;
    public final double PUSHER_OPEN_POSITION = 0.333;
    public final double PUSHER_CLOSED_POSITION = 0;

    private double currentShooterHeight;
    private double newShooterHeight;
    private double heightDifference;

    public ElapsedTime runtime = new ElapsedTime();


    /*The constructor for the class which is called inside the OpModeAuton file (which is the auton file we use to run the robot),
    and allows us to access the motors and servos on the robot from this file*/
    public AutonFunctionsTwo(DcMotor fLDrive, DcMotor fRDrive, DcMotor bLDrive, DcMotor bRDrive, Servo shootB, Servo shootP, DcMotor shootM, DcMotor shootA, Servo clawServo){
        frontLeftDrive = fLDrive;
        frontRightDrive = fRDrive;
        backLeftDrive = bLDrive;
        backRightDrive = bRDrive;
        shooterBlocker = shootB;
        shooterPusher = shootP;
        shooterMotor = shootM;
        shooterAngler = shootA;
        this.clawServo = clawServo;


        wheelCircumference = 9.435; //Circumference of our mecanum wheels may be subject to change
    }


    //Use the runtime/elapsed time to start a while loop (which will prevent any other code from running) the ends after a desired amount of time has passed
    public void pause(double seconds) {
        double startTime = runtime.seconds();

        while(runtime.seconds() - startTime < seconds) {}
    }


    //Turn off all drive train motors
    public void stop() {
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }

    //Used in the different move functions to decrease redundancy (so we don't type the same thing over and over; Abstraction)
    public void move(double in, double fL, double fR, double bL, double bR) {
        if (fL > 0) {
            encoderCounts = (int) frontLeftDrive.getCurrentPosition() +
                    (int) Math.rint(ENCODER_COUNTS_WHEEL_ROTATION * in / wheelCircumference);
            while (frontLeftDrive.getCurrentPosition() < encoderCounts) {
                frontLeftDrive.setPower(fL);
                frontRightDrive.setPower(fR);
                backLeftDrive.setPower(bL);
                frontLeftDrive.setPower(bR);
            }
        } else if (fL < 0) { //Since we are tracking movement to know when to stop using only the FL motor we can just check if it is going forward or backwards
                             // and account for that in our encoder count equations
            encoderCounts = (int) frontLeftDrive.getCurrentPosition() -
                    (int) Math.rint(ENCODER_COUNTS_WHEEL_ROTATION * in / wheelCircumference);
            while (frontLeftDrive.getCurrentPosition() > encoderCounts) {
                frontLeftDrive.setPower(fL);
                frontRightDrive.setPower(fR);
                backLeftDrive.setPower(bL);
                frontLeftDrive.setPower(bR);
            }
        }

        //Uses a while loop (similar to the pause function) to keep the motors running until the FL motor reaches the expected amount of encoder counts



        //Stop the robot after it has finished moving
        stop();
    }
    public void PID (double in, int fL, int fR, int bL, int bR) {
        double prevError = encoderCounts - frontLeftDrive.getCurrentPosition();
        double prevTime = runtime.seconds();
        while (Math.abs(prevError) > 1) {
            double curTime = runtime.seconds();
            double curError = encoderCounts - frontLeftDrive.getCurrentPosition();
            double p = P_CONST * curError;
            double d = D_CONST * (curError - prevError)/(curTime - prevTime);
            double output = p + d;
            frontLeftDrive.setPower(output * fL);
            frontRightDrive.setPower(output * fR);
            backLeftDrive.setPower(output * bL);
            frontLeftDrive.setPower(output * bR);
            prevError = curError;
            prevTime = curTime;
        }

    }

    //Move forward a desired amount of inches
    public void moveForward(double inches) {
        move(inches, 1, 1, 1, 1);
    }

    //Move backwards a desired amount of inches
    public void moveBackwards(double inches) {
        move(inches, -1, -1, -1, -1);
    }

    //Move left a desired amount of inches
    public void moveLeft(double inches) {
        move(inches, -1, 1, 1, -1);
    }

    //Move right a desired amount of inches
    public void moveRight(double inches) {
        move(inches, 1, -1, -1, 1);
    }

    /*public void moveFL(double inches) { //Will not work because encoder checks are based on front left motor which is not moving
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
    }*/

    /*public void turnClockwise(double deg) {
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
    }*/

    //Shoot once
    public void shoot() {
        //Turn on the flywheel for the shooter
        shooterMotor.setPower(1);
        //Move the blocker out of the way to the ring can leave the magazine
        shooterBlocker.setPosition(BLOCKER_OPEN_POSITION);
        //Move the pusher to push the ring
        shooterPusher.setPosition(PUSHER_OPEN_POSITION);

        pause(0.5);
        shooterPusher.setPosition(PUSHER_CLOSED_POSITION); //Pull the pusher back allowing another ring to fall into the magazine
        //Move the blocker back into place to prevent rings from leaving the magazine
        shooterBlocker.setPosition(BLOCKER_CLOSED_POSITION);
        //Turn off the flywheel
        shooterMotor.setPower(0);
    }

    //Shoot 3 times
    public void shoot3Times() {
        //Turn on the flywheel for the shooter
        shooterMotor.setPower(1);
        //Move the blocker out of the way to the ring can leave the magazine
        shooterBlocker.setPosition(BLOCKER_OPEN_POSITION);
        //Move the pusher to push the ring and bring it back to allow another ring in 3 times
        for (int i = 0; i < 3; i++ ) {
            shooterPusher.setPosition(PUSHER_OPEN_POSITION); //Pushes the ring
            //delay
            pause(1);
            shooterPusher.setPosition(PUSHER_CLOSED_POSITION); //Pulls back the pusher so another ring can fall into the magazine
        }
        //Move the blocker back into place to prevent rings from leaving the magazine
        shooterBlocker.setPosition(BLOCKER_CLOSED_POSITION);
        //Turn off the flywheel
        shooterMotor.setPower(0);
    }

    public void angleShooter(int angle) {
        newShooterHeight = angleToHeight(angle);
        heightDifference = newShooterHeight - currentShooterHeight;

        //Translate heightDifference into encoder counts and use PID to move the motor correctly in order to angle the shooter
    }

    public void toggleClaw() {
        //When called open or close the claw
        if (isClawOpen) {
            clawServoPosition = clawClosedPosition;
            isClawOpen = false;
        } else if (!isClawOpen) {
            clawServoPosition = clawOpenPosition;
            isClawOpen = true;
        }

        //Set the position of the servo
        clawServo.setPosition(clawServoPosition);
    }

    public void angleClaw() {

    }

    public static double angleToHeight (int angleDesired) {
        double radAngle = Math.toRadians(angleDesired);
        return Math.tan(radAngle) * (96.0 - (41.0/Math.sin(radAngle)));
    }
}