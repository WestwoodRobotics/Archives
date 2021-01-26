package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class AutonMethods {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft, frontRight, backLeft, backRight, intakeLeft, intakeRight, shooter, arm;
    private final int INCHES_TO_TICKS = 5;
    private final double DEGREES_TO_TICKS = 0.284;

    public AutonMethods(DcMotor frontLeft, DcMotor frontRight, DcMotor backLeft, DcMotor backRight, DcMotor intakeLeft, DcMotor intakeRight, DcMotor shooter, DcMotor arm) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;
        this.intakeLeft = intakeLeft;
        this.intakeRight = intakeRight;
        this.shooter = shooter;
        this.arm = arm;
    }

    public void intakesOn() {
        intakeLeft.setPower(1);
        intakeRight.setPower(1);
    }

    public void intakesOff() {
        intakeLeft.setPower(0);
        intakeRight.setPower(0);
    }

    public void shootOn() {
        shooter.setPower(1);
    }

    public void shootOff() {
        shooter.setPower(0);
    }

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

    public void setToUsingEncoders() {
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void resetAllEncoders() {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void brakeAllMotors() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public void setToRunToPosition() {
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void waitUntilReached() {
        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
            // do nothing until target reached for all motors
        }
    }

    public void pause(double seconds) {
        runtime.reset();
        while(runtime.seconds() < seconds) {
            // do nothing because it's a pause
        }
    }
}