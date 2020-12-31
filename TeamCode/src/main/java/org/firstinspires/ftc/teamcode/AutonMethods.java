package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class AutonMethods {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private final int INCHES_TO_TICKS = 5;
    private final int DEGREES_TO_TICKS = 360;

    public AutonMethods(DcMotor frontLeft, DcMotor frontRight, DcMotor backLeft, DcMotor backRight) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;
    }

    public void goForward(double inches) {
        int ticks = (int) (inches * INCHES_TO_TICKS);

        //reset encoders
        resetAllEncoders();

        //set target
        frontLeft.setTargetPosition(ticks);
        frontRight.setTargetPosition(-ticks);
        backLeft.setTargetPosition(-ticks);
        backRight.setTargetPosition(ticks);

        //RUN_TO_POSITION mode
        runToPosition();
        //set power
        frontLeft.setPower(1);
        frontRight.setPower(-1);
        backLeft.setPower(-1);
        backRight.setPower(1);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
            //do nothing until target reached but maybe use PID here?
        }

        brakeAllMotors();
        setToUsingEncoders();
    }

    public void goBackward(double inches) {
        int ticks = (int) (inches * INCHES_TO_TICKS);

        //reset encoders
        resetAllEncoders();

        //set target
        frontLeft.setTargetPosition(-ticks);
        frontRight.setTargetPosition(ticks);
        backLeft.setTargetPosition(ticks);
        backRight.setTargetPosition(-ticks);

        //RUN_TO_POSITION mode
        runToPosition();

        //set power
        frontLeft.setPower(-1);
        frontRight.setPower(1);
        backLeft.setPower(1);
        backRight.setPower(-1);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
            //do nothing until target reached but maybe use PID here?
        }

        brakeAllMotors();
        setToUsingEncoders();
    }

    public void goLeft(double inches) {
        int ticks = (int) (inches * INCHES_TO_TICKS);
        resetAllEncoders();

        frontLeft.setTargetPosition(-ticks);
        frontRight.setTargetPosition(-ticks);
        backLeft.setTargetPosition(-ticks);
        backRight.setTargetPosition(-ticks);

        runToPosition();

        //set power
        frontLeft.setPower(-1);
        frontRight.setPower(-1);
        backLeft.setPower(-1);
        backRight.setPower(-1);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
            //do nothing until target reached but maybe use PID here?
        }

        brakeAllMotors();
        setToUsingEncoders();
    }

    public void goRight(double inches) {
        int ticks = (int) (inches * INCHES_TO_TICKS);
        resetAllEncoders();

        frontLeft.setTargetPosition(ticks);
        frontRight.setTargetPosition(ticks);
        backLeft.setTargetPosition(ticks);
        backRight.setTargetPosition(ticks);

        runToPosition();

        //set power
        frontLeft.setPower(1);
        frontRight.setPower(1);
        backLeft.setPower(1);
        backRight.setPower(1);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
            //do nothing until target reached but maybe use PID here?
        }

        brakeAllMotors();
        setToUsingEncoders();
    }

    public void turnRightCenter(double degrees){
        int ticks = (int) (degrees * DEGREES_TO_TICKS);
        resetAllEncoders();

        frontLeft.setTargetPosition(ticks);
        frontRight.setTargetPosition(ticks);
        backLeft.setTargetPosition(-ticks);
        backRight.setTargetPosition(-ticks);

        runToPosition();

        //set power
        frontLeft.setPower(1);
        frontRight.setPower(1);
        backLeft.setPower(-1);
        backRight.setPower(-1);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
            //do nothing until target reached but maybe use PID here?
        }

        brakeAllMotors();
        setToUsingEncoders();
    }

    public void turnLeftCenter(double degrees) {
        int ticks = (int) (degrees * DEGREES_TO_TICKS);
        resetAllEncoders();

        frontLeft.setTargetPosition(-ticks);
        frontRight.setTargetPosition(-ticks);
        backLeft.setTargetPosition(ticks);
        backRight.setTargetPosition(ticks);

        runToPosition();

        //set power
        frontLeft.setPower(-1);
        frontRight.setPower(-1);
        backLeft.setPower(1);
        backRight.setPower(1);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
            //do nothing until target reached but maybe use PID here?
        }

        brakeAllMotors();
        setToUsingEncoders();
    }

    private double setPower(double inches) {
        double power = 0;
        //PID?

        return power;
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

    public void runToPosition() {
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void pause(double seconds) {
        runtime.reset();
        while(runtime.seconds() < seconds) {

        }
    }





}