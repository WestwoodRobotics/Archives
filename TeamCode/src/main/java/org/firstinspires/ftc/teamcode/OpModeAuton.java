package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="Hunga Munga Auton", group="Iterative Opmode")
public class OpModeAuton extends OpMode
{
    public DcMotor frontLeftDrive;
    public DcMotor frontRightDrive;
    public DcMotor backLeftDrive;
    public DcMotor backRightDrive;

    //    public Servo shooterBlocker;
    public Servo shooterPusher;
    public DcMotor shooterMotor;
//    public DcMotor shooterAngler;

    public DcMotor intakeMotor;
    public DcMotor scuffedMotor;

    public Telemetry telemetry;

    public ElapsedTime runtime = new ElapsedTime();

//    public Servo clawServo;

    //Create motor power variables for drive train motors
    private double frontLeftPower;
    private double frontRightPower;
    private double backLeftPower;
    private double backRightPower;

//    private final int HIGH_GOAL_ANGLE = 30; //Needs to test for actual value

    //Assign the auton functions class to a variable name
    AutonFunctionsTwo autFunc;

    @Override
    public void init() {
                /*initialize your motors here using the hardwareMap variable and the .get method within it.
        Map the motor objects to the physical motors using the control hub*/
        frontLeftDrive = hardwareMap.get(DcMotor.class,"frontLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class,"frontRightDrive");
        backLeftDrive = hardwareMap.get(DcMotor.class,"backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotor.class,"backRightDrive");

        intakeMotor = hardwareMap.get(DcMotor.class,"intakeMotor");
        scuffedMotor = hardwareMap.get(DcMotor.class, "scuffedMotor");

//        clawServo = hardwareMap.get(Servo.class, "clawServo");

        shooterMotor = hardwareMap.get(DcMotor.class, "shooterMotor");
//        shooterAngler = hardwareMap.get(DcMotor.class, "shooterAngler");

//        shooterBlocker = hardwareMap.get(Servo.class, "shooterBlocker");
        shooterPusher = hardwareMap.get(Servo.class, "shooterPusher");


        //Set the motor directions
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

        intakeMotor.setDirection(DcMotor.Direction.REVERSE);
        shooterMotor.setDirection(DcMotor.Direction.FORWARD);
        scuffedMotor.setDirection(DcMotor.Direction.REVERSE);
        //Set the zero power behavior of the motors to stop quickly
        stop();

        //Set the default values for toggle control booleans such as if the intake is on or off or if the claw is open or closed
//        isIntakeOn = false;
//        isClawOpen = true;

        //Set the minimum and maximum values for the claw servo and set the claw servo position to the open position by default
//        clawClosedPosition = 0;
//        clawOpenPosition = 0.5;

        //Set up telemetry
//        telemetry.addData("Status", "Initialized");
//        telemetry.update();

        AutonFunctionsTwo autFunc = new AutonFunctionsTwo(frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive, shooterPusher, shooterMotor, scuffedMotor);
        /*initialize your motors here using the hardwareMap variable and the .get method within it.
        Map the motor objects to the physical motors using the control hub*/
/*
        frontLeftDrive = hardwareMap.get(DcMotor.class,"frontLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class,"frontRightDrive");
        backLeftDrive = hardwareMap.get(DcMotor.class,"backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotor.class,"backRightDrive");
        shooterMotor = hardwareMap.get(DcMotor.class, "shooterMotor");
//        shooterAngler = hardwareMap.get(DcMotor.class, "shooterAngler");
//        shooterBlocker = hardwareMap.get(Servo.class, "shooterBlocker");
        shooterPusher = hardwareMap.get(Servo.class, "shooterPusher");
//        clawServo = hardwareMap.get(Servo.class, "clawServo");
        //Set the motor directions
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);
        intakeMotor.setDirection(DcMotor.Direction.REVERSE);
        shooterMotor.setDirection(DcMotor.Direction.FORWARD);
        scuffedMotor.setDirection(DcMotor.Direction.REVERSE);
        //Set the zero power behavior of the motors to stop quickly
        stop();
        //Set up telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        //Setup the auton functions class so it can access the motors and servos on the robot and so we can use the functions from it
        AutonFunctionsTwo autFunc = new AutonFunctionsTwo(frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive, shooterPusher, shooterMotor, scuffedMotor);
        */
    }


    @Override
    public void loop() { // Assuming that the shooter stays angled at a fixed angle at all times
//        autFunc.moveForward(54); //Might should be re-calculated distance from start to right behind launch line for shooting
//        autFunc.moveLeft(0); //Needs to be calculated to line up straight with high goal
        autFunc.shoot3times();
//        autFunc.moveForward(12);//Might should be re-calculated distance to get onto launch line for parking points
        autFunc.pause(30);


        /*//forwards 54 inches; front of the robot will be at the 3rd square line
        //spin and shoot 3 times
        //turn 22.28 degrees; 24 - 18.5 + 24 = 29.5, tan^-1(29.5/72) = 22.28
        //shoot
        //turn 4.918 degrees; 24 - 11 + 24 = 37, tan^-1(37/72) = 27.198, 27.198 - 22.28 = 4.918
        //shoot
        //turn 4.520 degrees; 24 - 3.5 + 24 = 44.5, tan^-1(44.5/72) = 31.718, 31.718 - 27.198 = 4.520
        //shoot
        //forwards 12 inches; get on the line
        autFunc.moveForward(54);
        autFunc.turnClockwise(22.28);
        autFunc.shoot();
        autFunc.turnCounterClockwise(4.918);
        autFunc.shoot();
        autFunc.turnCounterClockwise(4.520);
        autFunc.shoot();
        autFunc.moveForward(12);*/
    }


    public void stop() {
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        shooterMotor.setPower(0);
        intakeMotor.setPower(0);
    }
}