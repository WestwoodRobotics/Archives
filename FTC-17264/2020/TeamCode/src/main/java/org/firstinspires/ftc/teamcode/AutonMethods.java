package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * This holds the bulk of the Autonomous code for Team 17264 Arrowhead for the
 * 2020-2021 FTC season.This code holds all of the methods that will be called
 * in the ArrowheadAuton class, which is the official autonomous class that the
 * driver station will run.
 *
 * @version Last updated on 3/3/2021
 */
public class AutonMethods {
    private LinearOpMode opmode;
    private ElapsedTime runtime = new ElapsedTime();
    private Telemetry telemetry;

    private DcMotorEx frontLeft, frontRight, backLeft, backRight;
    private DcMotor intakeLeft, intakeRight, arm;
    private DcMotorEx shooter;

    private Servo claw1, claw2, shooterHelper;

    private final double YARDS_TO_TICKS = 6.97;
    private final double MTRPWR = 1.0;
    private final double DEGREES_TO_TICKS = 0.284;

    private final int MAX_DRIVETRAIN_VELOCITY = 2000;
    private final int SHOOTER_VELOCITY = 2000;

    public AutonMethods(LinearOpMode opmode,
                        Telemetry telemetry,
                        DcMotorEx frontLeft,
                        DcMotorEx frontRight,
                        DcMotorEx backLeft,
                        DcMotorEx backRight,
                        DcMotor intakeLeft,
                        DcMotor intakeRight,
                        DcMotorEx shooter,
                        DcMotor arm,
                        Servo claw1,
                        Servo claw2,
                        Servo shooterHelper) {
        this.opmode = opmode;
        this.telemetry = telemetry;
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

    public void stopForTime(double seconds){
        runtime.reset();
        while(runtime.seconds()<seconds){

        }
    }
    public void goForwardTime(double seconds){
        runtime.reset();
        frontLeft.setPower(.25);
        backRight.setPower(.25);
        backLeft.setPower(-0.25);
        frontRight.setPower(-0.25);
        while(runtime.seconds() < seconds){

        }
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
    }

    public void goBackwardTime(double seconds){
        runtime.reset();
        frontLeft.setPower(-.25);
        backRight.setPower(-.25);
        backLeft.setPower(.25);
        frontRight.setPower(.25);
        while(runtime.seconds() < seconds){

        }
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
    }
    public void goLeftTime(double seconds){
        runtime.reset();
        frontLeft.setPower(-.25);
        backRight.setPower(-.25);
        backLeft.setPower(-.25);
        frontRight.setPower(-.25);
        while(runtime.seconds() < seconds){

        }
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
    }
    public void goRightTime(double seconds){
        runtime.reset();
        frontLeft.setPower(.5);
        backRight.setPower(.5);
        backLeft.setPower(.5);
        frontRight.setPower(.5);
        while(runtime.seconds() < seconds){

        }
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
    }
    public void goForward(double inches) {
        // reset encoders
        //resetAllEncoders();

        // velocity PID
        //frontLeft.setVelocity(MAX_DRIVETRAIN_VELOCITY);
        //frontRight.setVelocity(-MAX_DRIVETRAIN_VELOCITY);
        //backLeft.setVelocity(-MAX_DRIVETRAIN_VELOCITY);
        //backRight.setVelocity(MAX_DRIVETRAIN_VELOCITY);
        //runtime.reset();
        //while(runtime < inches){

        //}
        //brakeAllMotors();
        //setToUsingEncoders();
    }

    public void goBackward(double yards) {
        int ticks = (int) (yards * YARDS_TO_TICKS);

        // reset encoders
        resetAllEncoders();

        // velocity PID
        frontLeft.setVelocity(-MAX_DRIVETRAIN_VELOCITY);
        frontRight.setVelocity(MAX_DRIVETRAIN_VELOCITY);
        backLeft.setVelocity(MAX_DRIVETRAIN_VELOCITY);
        backRight.setVelocity(-MAX_DRIVETRAIN_VELOCITY);

        brakeAllMotors();
        setToUsingEncoders();
    }

    public void goLeft(double yards) {
        if (opmode.opModeIsActive()) {
            int ticks = (int) (yards * YARDS_TO_TICKS);

            // reset encoders
            resetAllEncoders();

            // velocity PID
            frontLeft.setVelocity(-MAX_DRIVETRAIN_VELOCITY);
            frontRight.setVelocity(-MAX_DRIVETRAIN_VELOCITY);
            backLeft.setVelocity(-MAX_DRIVETRAIN_VELOCITY);
            backRight.setVelocity(-MAX_DRIVETRAIN_VELOCITY);

            brakeAllMotors();

            telemetry.addData("Motors", "frontLeft (%.2f), frontRight (%.2f), backLeft (%.2f), backRight (%.2f)", frontLeft.getPower(), frontRight.getPower(), backLeft.getPower(), backRight.getPower());
            telemetry.addData("Motors", "intakeLeft (%.2f), shooter (%.2f), arm (%.2f)", intakeLeft.getPower(), shooter.getPower(), arm.getPower());
            //telemetry.addData("Servos", "claw1 (%.2f), claw2 (%.2f), shooterHelper (%.2f)", claw1.getPosition(), claw2.getPosition(), shooterHelper.getPower());
            telemetry.addData("Servos", "claw1 (%.2f), claw2 (%.2f), shooterHelper (%.2f)", claw1.getPosition(), claw2.getPosition(), shooterHelper.getPosition());
            telemetry.addData("Ticks", ticks);
            telemetry.addData("Yards", yards);

            telemetry.update();

            setToUsingEncoders();
        }
    }

    public void goRight(double yards) {
        if (opmode.opModeIsActive()) {
            int ticks = (int) (yards * YARDS_TO_TICKS);

            // reset encoders
            resetAllEncoders();

            // velocity PID
            frontLeft.setVelocity(MAX_DRIVETRAIN_VELOCITY);
            frontRight.setVelocity(MAX_DRIVETRAIN_VELOCITY);
            backLeft.setVelocity(MAX_DRIVETRAIN_VELOCITY);
            backRight.setVelocity(MAX_DRIVETRAIN_VELOCITY);

            brakeAllMotors();
            setToUsingEncoders();
        }
    }

    public void turnRightCenter(double degrees) {
        int ticks = (int) (degrees * DEGREES_TO_TICKS);

        // reset encoders
        resetAllEncoders();

        // velocity PID
        frontLeft.setVelocity(MAX_DRIVETRAIN_VELOCITY);
        frontRight.setVelocity(MAX_DRIVETRAIN_VELOCITY);
        backLeft.setVelocity(-MAX_DRIVETRAIN_VELOCITY);
        backRight.setVelocity(-MAX_DRIVETRAIN_VELOCITY);

        brakeAllMotors();
        setToUsingEncoders();
    }

    public void turnLeftCenter(double degrees) {
        int ticks = (int) (degrees * DEGREES_TO_TICKS);

        // reset encoders
        resetAllEncoders();

        // velocity PID
        frontLeft.setVelocity(-MAX_DRIVETRAIN_VELOCITY);
        frontRight.setVelocity(-MAX_DRIVETRAIN_VELOCITY);
        backLeft.setVelocity(MAX_DRIVETRAIN_VELOCITY);
        backRight.setVelocity(MAX_DRIVETRAIN_VELOCITY);

        brakeAllMotors();
        setToUsingEncoders();
    }

    public void moveHelper() {
        if (opmode.opModeIsActive()) {
            runtime.reset();

            while(runtime.seconds() < .4) {
                shooterHelper.setPosition(0);
            }

            while(runtime.seconds() < .8) {
                shooterHelper.setPosition(.7);
            }
        }
    }

    public void releaseWobbleGoal() {
        arm.setPower(-1);
        claw1.setPosition(1);
        claw1.setPosition(0);
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
        // reset encoders
        shooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        shooter.setVelocity(SHOOTER_VELOCITY);
    }

    public void shootOff() {
        shooter.setVelocity(0);
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
        frontLeft.setVelocity(0);
        frontRight.setVelocity(0);
        backLeft.setVelocity(0);
        backRight.setVelocity(0);
    }

    public void waitUntilReached() {
        runtime.reset();

        while ((frontLeft.isBusy() || frontRight.isBusy() || backLeft.isBusy() || backRight.isBusy())) {
            // do nothing until target reached for all motors
            telemetry.addData("Motors", "frontLeft (%.2f), frontRight (%.2f), backLeft (%.2f), backRight (%.2f)", frontLeft.getVelocity(), frontRight.getVelocity(), backLeft.getVelocity(), backRight.getVelocity());
            telemetry.addData("Motors", "intakeLeft (%.2f), shooter (%.2f), arm (%.2f)", intakeLeft.getPower(), shooter.getVelocity(), arm.getPower());
            telemetry.addData("Servos", "claw1 (%.2f), claw2 (%.2f), shooterHelper (%.2f)", claw1.getPosition(), claw2.getPosition(), shooterHelper.getPosition());

            telemetry.update();
        }
    }
}