package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "Basic: Linear OpMode", group = "Linear Opmode")
public class ArrowheadAuton extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    // initialize motors
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private DcMotor intakeLeft = null;
    //private DcMotor intakeRight = null;
    private DcMotor shooter = null;
    private DcMotor arm = null;

    // initialize servos
    private Servo claw1 = null;
    private Servo claw2 = null;
    private Servo shooterHelper = null;

    public void runOpMode() {

        // put all 8 motors and all 3 servos on the hardware map
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        intakeLeft = hardwareMap.get(DcMotor.class, "intakeLeft");
        //intakeRight = hardwareMap.get(DcMotor.class, "intakeRight");
        shooter = hardwareMap.get(DcMotor.class, "shooter");
        arm = hardwareMap.get(DcMotor.class, "arm");
        claw1 = hardwareMap.get(Servo.class, "claw1");
        claw2 = hardwareMap.get(Servo.class, "claw2");
        shooterHelper = hardwareMap.get(Servo.class, "shooterHelper");

        // set directions of all 8 motors
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        intakeLeft.setDirection(DcMotor.Direction.FORWARD);
        //intakeRight.setDirection(DcMotor.Direction.FORWARD);
        shooter.setDirection(DcMotor.Direction.FORWARD);
        arm.setDirection(DcMotor.Direction.FORWARD);

        // run using encoders
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // set ZeroPowerBehavior to brake
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //intakeRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        AutonMethods methods = new AutonMethods(frontLeft, frontRight, backLeft, backRight, intakeLeft, shooter, arm, claw1, claw2, shooterHelper);

        //AutonMethods methods = new AutonMethods(frontLeft, frontRight, backLeft, backRight, intakeLeft, intakeRight, shooter, arm, claw1, claw2, shooterHelper);

        while (opModeIsActive() && runtime.seconds() <= 30.0) {
            methods.setToUsingEncoders();

            // go to launch line
            methods.goForward(60);

            // hit 1st powershot [from right to left]
            methods.goLeft(6);
            methods.pause(1);
            methods.shootOn();
            methods.pause(2);
            methods.shootOff();

            // hit 2nd powershot
            methods.goLeft(7.5);
            methods.pause(1);
            methods.shootOn();
            methods.pause(2);
            methods.shootOff();

            // hit 3rd powershot
            methods.goLeft(7.5);
            methods.pause(1);
            methods.shootOn();
            methods.pause(2);
            methods.shootOff();


            methods.goRight(21 + 24);


            // intake square A
            methods.goForward(12);
            methods.intakesOn();
            methods.goRight(12);
            methods.goLeft(12);

            // intake square B
            methods.goForward(12);
            methods.goLeft(12);
            methods.goRight(12);

            // intake square C
            methods.goForward(12);
            methods.goRight(12);
            methods.goLeft(12);

            // back to shoot in high goal
            methods.goBackward(12 + 24 + 12);
            methods.goLeft(12);
            methods.shootOn();
            methods.pause(5);
            methods.shootOff();
        }
    }
}