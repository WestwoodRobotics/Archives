package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is the Autonomous code for Team 17264 Arrowhead for the 2020-2021 FTC season.
 * This code calls several methods from the AutonMethods class. And this is the official
 * code that will be run from the driver station.
 *
 * @version Last updated on 3/9/2021
 */

@Autonomous(name = "ArrowheadAuton", group = "Linear Opmode")
public class ArrowheadAuton extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    // initialize motors
    private DcMotorEx frontLeft = null;
    private DcMotorEx frontRight = null;
    private DcMotorEx backLeft = null;
    private DcMotorEx backRight = null;
    private DcMotor intakeLeft = null;
    private DcMotor intakeRight = null;
    private DcMotorEx shooter = null;
    private DcMotor arm = null;

    // initialize servos
    private Servo claw1 = null;
    private Servo claw2 = null;
    private Servo shooterHelper = null;

    /**
     * This method runs once. It initializes all motors on the hardware map, sets their direction,
     */
    public void runOpMode() {
        // put all 8 motors and all 3 servos on the hardware map
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");
        intakeLeft = hardwareMap.get(DcMotor.class, "intakeLeft");
        intakeRight = hardwareMap.get(DcMotor.class, "intakeRight");
        shooter = hardwareMap.get(DcMotorEx.class, "shooter");
        arm = hardwareMap.get(DcMotor.class, "arm");
        claw1 = hardwareMap.get(Servo.class, "claw1");
        claw2 = hardwareMap.get(Servo.class, "claw2");
        shooterHelper = hardwareMap.get(Servo.class, "shooterHelper");

        // set directions of all 8 motors
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        intakeLeft.setDirection(DcMotor.Direction.REVERSE);
        intakeRight.setDirection(DcMotor.Direction.REVERSE);
        shooter.setDirection(DcMotor.Direction.REVERSE);
        arm.setDirection(DcMotor.Direction.FORWARD);

        // run using encoders
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // set ZeroPowerBehavior to brake
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // set PIDF coefficients for all motors using PID
        //frontLeft.setVelocityPIDFCoefficients(30, 0, 0, 0);
        //frontRight.setVelocityPIDFCoefficients(30, 0, 0, 0);
        //backLeft.setVelocityPIDFCoefficients(30, 0, 0, 0);
        //backRight.setVelocityPIDFCoefficients(30, 0, 0, 0);

        //shooter.setVelocityPIDFCoefficients(30, 0, 0, 0);


        waitForStart();

        AutonMethods methods = new AutonMethods(this, telemetry, frontLeft, frontRight, backLeft, backRight, intakeLeft, intakeRight, shooter, arm, claw1, claw2, shooterHelper);

        int stopInt = 0;

        while (stopInt == 0 && opModeIsActive() && runtime.seconds() < 30) {
            methods.goLeftTime(.9);
            methods.stopForTime(2);
            methods.goForwardTime(1.8);
            methods.goLeftTime(.25);

            //methods.setToUsingEncoders();

            // hit 1st powershot [from right to left]
            //methods.goLeft(1);
            //methods.goRight(1);

            /*methods.shootOn();
            methods.pause(1);
            methods.moveHelper();
            methods.shootOff();
            */

            /*
            methods.goForward(72);
            methods.shootOn();
            methods.pause(1);
            methods.moveHelper();*/
/*
            // hit 2nd powershot
            methods.goLeft(7.5);
            methods.pause(1);
            methods.moveHelper();

            // hit 3rd powershot
            methods.goLeft(7.5);
            methods.pause(1);
            methods.moveHelper();
            methods.shootOff();*/
            /**** AT THIS POINT:
             *    3 POWERSHOTS SHOULD HAVE BEEN SCORED,
             *    0 RINGS REMAIN IN THE ROBOT****/
/*
            // go intake the rings in the pile
            methods.intakesOn();
            methods.goBackward(50);
            methods.goRight(30);
            // just for good measure
            methods.goLeft(10);
            methods.goRight(10);
            methods.intakesOff();*/
            /**** AT THIS POINT:
             *    3 POWERSHOTS SHOULD HAVE BEEN SCORED,
             *    3 RINGS REMAIN IN THE ROBOT****/
/*
            // score in the goals
            methods.shootOn();
            methods.goBackward(12);
            methods.goLeft(9);
            methods.pause(1);
            methods.moveHelper();
            methods.pause(1);
            methods.moveHelper();
            methods.pause(1);
            methods.moveHelper();
            methods.shootOff();*/
            /**** AT THIS POINT:
             *    3 POWERSHOTS SHOULD HAVE BEEN SCORED,
             *    3 GOALS SHOULD HAVE BEEN SCORED
             *    0 RINGS REMAIN IN THE ROBOT****/
/*
            // park on launch line
            methods.goForward(72);*/

            stopInt++;
        }
    }
}