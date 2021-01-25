package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="Hunga Munga Auton", group="Iterative Opmode")
public class TestRunner extends OpMode
{
    private ElapsedTime runtime = new ElapsedTime();

    //Initialize drive train motors
    public DcMotor frontLeftDrive;
    public DcMotor frontRightDrive;
    public DcMotor backLeftDrive;
    public DcMotor backRightDrive;

    //Initialize shooter motors and servos
/*    public Servo shooterBlocker;
    public Servo shooterPusher;
    public DcMotor shooterMotor;
    public DcMotor shooterAngler;

    public Servo clawServo;

    //Create motor power variables for drive train motors
    private double frontLeftPower;
    private double frontRightPower;
    private double backLeftPower;
    private double backRightPower;

    //Assign the auton functions class to a variable name
    AutonFunctionsTwo autFunc;
**/
    Test testing;

    @Override
    public void init() {
        /*initialize your motors here using the hardwareMap variable and the .get method within it.
        Map the motor objects to the physical motors using the control hub*/
        frontLeftDrive = hardwareMap.get(DcMotor.class,"frontLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class,"frontRightDrive");
        backLeftDrive = hardwareMap.get(DcMotor.class,"backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotor.class,"backRightDrive");

/*        shooterMotor = hardwareMap.get(DcMotor.class, "shooterMotor");
        shooterAngler = hardwareMap.get(DcMotor.class, "shooterAngler");

        shooterBlocker = hardwareMap.get(Servo.class, "shooterBlocker");
        shooterPusher = hardwareMap.get(Servo.class, "shooterBlocker");

        clawServo = hardwareMap.get(Servo.class, "clawServo");
**/
        //Set the motor directions
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

        //Set the motor powers to 0 by default
/*        frontLeftPower = 0;
        frontRightPower = 0;
        backLeftPower = 0;
        backRightPower = 0;
**/
        //Set the zero power behavior of the motors to stop quickly
        stop();

        //Set up telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //Setup the auton functions class so it can access the motors and servos on the robot and so we can use the functions from it
//        AutonFunctionsTwo autFunc = new AutonFunctionsTwo(frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive, shooterBlocker, shooterPusher, shooterMotor, shooterAngler, clawServo);
        Test testing = new Test (frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive);
    }


    @Override
    public void loop() { // Assuming that the shooter stays angled at a fixed angle at all times
       testing.testFR();


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

        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }
}
