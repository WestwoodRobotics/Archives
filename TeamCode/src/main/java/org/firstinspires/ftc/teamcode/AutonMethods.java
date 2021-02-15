package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This holds the bulk of the Autonomous code for Team 17264 Arrowhead for the 2020-2021 FTC season.
 * This code holds all of the methods that will be called in the ArrowheadAuton class, which is the
 * official autonomous class that the driver station will run.
 *
 * @version Last updated on 2/14/2021
 */
public class AutonMethods {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft, frontRight, backLeft, backRight, intakeLeft, intakeRight, shooter, arm;
    private Servo claw1, claw2, shooterHelper;
    private final int INCHES_TO_TICKS = 5;
    private final double DEGREES_TO_TICKS = 0.284;

    public AutonMethods(DcMotor frontLeft, DcMotor frontRight, DcMotor backLeft, DcMotor backRight, DcMotor intakeLeft, DcMotor intakeRight, DcMotor shooter, DcMotor arm, Servo claw1, Servo claw2, Servo shooterHelper) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;
        this.intakeLeft = intakeLeft;
        this.intakeRight = intakeRight;
        this.shooter = shooter;
        this.arm = arm;
        this.claw1 = claw1;
        this.claw2 = claw2;
        this.shooterHelper = shooterHelper;
    }

    // 1st comp constructor;  will be deleted once intakeRight finished
    public AutonMethods(DcMotor frontLeft, DcMotor frontRight, DcMotor backLeft, DcMotor backRight, DcMotor intakeLeft, DcMotor shooter, DcMotor arm, Servo claw1, Servo claw2, Servo shooterHelper) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;
        this.intakeLeft = intakeLeft;
        this.shooter = shooter;
        this.arm = arm;
        this.claw1 = claw1;
        this.claw2 = claw2;
        this.shooterHelper = shooterHelper;
    }

    /**
     * Puts shooterHelper at extended position (1) and then returns it to its initial position (0).
     */
    public void moveHelper() {
        runtime.reset();

        while (runtime.seconds() < 1.0) {
            shooterHelper.setPosition(1);
        }

        shooterHelper.setPosition(0);
    }

    /**
     * Lowers arm and opens the claws.
     */
    public void releaseWobbleGoal() {
        arm.setPower(-1);
        claw1.setPosition(1);
        claw1.setPosition(0);
    }

    /**
     * Turns both intakes ON.
     */
    public void intakesOn() {
        intakeLeft.setPower(1);
        // intakeRight.setPower(1);
    }

    /**
     * Turns both intakes OFF.
     */
    public void intakesOff() {
        intakeLeft.setPower(0);
        // intakeRight.setPower(0);
    }

    /**
     * Turns the shooter ON.
     */
    public void shootOn() {
        shooter.setPower(1);
    }

    /**
     * Turns the shooter OFF.
     */
    public void shootOff() {
        shooter.setPower(0);
    }

    /**
     * Uses RUN_TO_POSITION (PID) to go the inputted amount of inches forward.
     *
     * @param inches
     */
    public void goForward(double inches) {
        int ticks = (int) (inches * INCHES_TO_TICKS);

        // reset encoders
        resetAllEncoders();

        // set target
        frontLeft.setTargetPosition(ticks);
        frontRight.setTargetPosition(-ticks);
        backLeft.setTargetPosition(-ticks);
        backRight.setTargetPosition(ticks);

        // RUN_TO_POSITION mode
        setToRunToPosition();

        // set power
        frontLeft.setPower(1);
        frontRight.setPower(-1);
        backLeft.setPower(-1);
        backRight.setPower(1);

        // wait until all motors reach target
        waitUntilReached();

        brakeAllMotors();
        setToUsingEncoders();
    }

    /**
     * Uses RUN_TO_POSITION (PID) to go the inputted amount of inches backward.
     *
     * @param inches
     */
    public void goBackward(double inches) {
        int ticks = (int) (inches * INCHES_TO_TICKS);

        // reset encoders
        resetAllEncoders();

        // set target
        frontLeft.setTargetPosition(-ticks);
        frontRight.setTargetPosition(ticks);
        backLeft.setTargetPosition(ticks);
        backRight.setTargetPosition(-ticks);

        // RUN_TO_POSITION mode
        setToRunToPosition();

        // set power
        frontLeft.setPower(-1);
        frontRight.setPower(1);
        backLeft.setPower(1);
        backRight.setPower(-1);

        // wait until all motors reach target
        waitUntilReached();

        brakeAllMotors();
        setToUsingEncoders();
    }

    /**
     * Uses RUN_TO_POSITION (PID) to go the inputted amount of inches left.
     *
     * @param inches
     */
    public void goLeft(double inches) {
        int ticks = (int) (inches * INCHES_TO_TICKS);

        // reset encoders
        resetAllEncoders();

        // set target
        frontLeft.setTargetPosition(-ticks);
        frontRight.setTargetPosition(-ticks);
        backLeft.setTargetPosition(-ticks);
        backRight.setTargetPosition(-ticks);

        // RUN_TO_POSITION mode
        setToRunToPosition();

        // set power
        frontLeft.setPower(-1);
        frontRight.setPower(-1);
        backLeft.setPower(-1);
        backRight.setPower(-1);

        // wait until all motors reach target
        waitUntilReached();

        brakeAllMotors();
        setToUsingEncoders();
    }

    /**
     * Uses RUN_TO_POSITION (PID) to go the inputted amount of inches right.
     *
     * @param inches
     */
    public void goRight(double inches) {
        int ticks = (int) (inches * INCHES_TO_TICKS);

        // reset encoders
        resetAllEncoders();

        // set target
        frontLeft.setTargetPosition(ticks);
        frontRight.setTargetPosition(ticks);
        backLeft.setTargetPosition(ticks);
        backRight.setTargetPosition(ticks);

        // RUN_TO_POSITION mode
        setToRunToPosition();

        // set power
        frontLeft.setPower(1);
        frontRight.setPower(1);
        backLeft.setPower(1);
        backRight.setPower(1);

        // wait until all motors reach target
        waitUntilReached();

        brakeAllMotors();
        setToUsingEncoders();
    }

    /**
     * Uses RUN_TO_POSITION (PID) to turn right the number of inputted degrees with the center of
     * rotation being the center of the robot.
     *
     * @param degrees
     */
    public void turnRightCenter(double degrees) {
        int ticks = (int) (degrees * DEGREES_TO_TICKS);

        // reset encoders
        resetAllEncoders();

        // set target
        frontLeft.setTargetPosition(ticks);
        frontRight.setTargetPosition(ticks);
        backLeft.setTargetPosition(-ticks);
        backRight.setTargetPosition(-ticks);

        // RUN_TO_POSITION mode
        setToRunToPosition();

        // set power
        frontLeft.setPower(1);
        frontRight.setPower(1);
        backLeft.setPower(-1);
        backRight.setPower(-1);

        // wait until all motors reach target
        waitUntilReached();

        brakeAllMotors();
        setToUsingEncoders();
    }

    /**
     * Uses RUN_TO_POSITION (PID) to turn right the number of inputted degrees, with the center of
     * rotation being the center of the robot.
     *
     * @param degrees
     */
    public void turnLeftCenter(double degrees) {
        int ticks = (int) (degrees * DEGREES_TO_TICKS);

        // reset encoders
        resetAllEncoders();

        // set target
        frontLeft.setTargetPosition(-ticks);
        frontRight.setTargetPosition(-ticks);
        backLeft.setTargetPosition(ticks);
        backRight.setTargetPosition(ticks);

        // RUN_TO_POSITION mode
        setToRunToPosition();

        // set power
        frontLeft.setPower(-1);
        frontRight.setPower(-1);
        backLeft.setPower(1);
        backRight.setPower(1);

        // wait until all motors reach target
        waitUntilReached();

        brakeAllMotors();
        setToUsingEncoders();
    }

    /**
     * Sets all motors to run using encoders to simplify other methods.
     */
    public void setToUsingEncoders() {
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Resets the encoder values of all motors to simplify other methods.
     */
    public void resetAllEncoders() {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     * Stops all motors to simplify other methods.
     */
    public void brakeAllMotors() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    /**
     * Sets all motors to run until a set position is reached (according to the encoder values)
     * to simplify other methods.
     */
    public void setToRunToPosition() {
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * Makes sure nothing is done while the drivetrain is active.
     */
    public void waitUntilReached() {
        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
            // do nothing until target reached for all motors
        }
    }

    /**
     * Makes sure nothing is done for the number of seconds inputted.
     *
     * @param seconds
     */
    public void pause(double seconds) {
        runtime.reset();

        while(runtime.seconds() < seconds) {
            // do nothing because it's a pause
        }
    }


    //THIS WAS THE TIME-BASED AUTON WHICH DID NOT WORK

    /*
    public void noEForward() {
        runtime.reset();

        while (runtime.seconds() < 2) {
            frontLeft.setPower(1);
            frontRight.setPower(-1);
            backLeft.setPower(-1);
            backRight.setPower(1);
        }

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public void noEBackward() {
        runtime.reset();

        while (runtime.seconds() < 2) {
            frontLeft.setPower(-1);
            frontRight.setPower(1);
            backLeft.setPower(1);
            backRight.setPower(-1);
        }

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public void noELeft() {
        runtime.reset();

        while (runtime.seconds() < 0.2) {
            frontLeft.setPower(-1);
            frontRight.setPower(-1);
            backLeft.setPower(-1);
            backRight.setPower(-1);
        }

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
*/

}