package org.firstinspires.ftc.teamcode;

//import com.arcrobotics.ftclib.controller.PDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="Hunga Munga Test", group="Linear OpMode")
public class Test extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    //Initialize drive train motors
    public DcMotorEx frontLeftDrive;
    public DcMotorEx frontRightDrive;
    public DcMotorEx backLeftDrive;
    public DcMotorEx backRightDrive;
    private int epr = 28;

    //Initialize shooter motors and servos
//    public Servo shooterBlocker;
    public Servo shooterPusher;
    public DcMotorEx shooterMotor;
    public DcMotor scuffedMotor;
//    public DcMotor shooterAngler;

    public Servo clawServo;

    //Create motor power variables for drive train motors
    private double frontLeftPower;
    private double frontRightPower;
    private double backLeftPower;
    private double backRightPower;

//    private final int HIGH_GOAL_ANGLE = 30; //Needs to test for actual value

    //Assign the auton functions class to a variable name
    AutonFunctionsTwo autFunc;

    @Override
    public void runOpMode() {
        /*initialize your motors here using the hardwareMap variable and the .get method within it.
        Map the motor objects to the physical motors using the control hub*/
        frontLeftDrive = hardwareMap.get(DcMotorEx.class, "frontLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotorEx.class, "frontRightDrive");
        backLeftDrive = hardwareMap.get(DcMotorEx.class, "backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotorEx.class, "backRightDrive");

        shooterMotor = hardwareMap.get(DcMotorEx.class, "shooterMotor");
//        shooterAngler = hardwareMap.get(DcMotor.class, "shooterAngler");

//        shooterBlocker = hardwareMap.get(Servo.class, "shooterBlocker");
        shooterPusher = hardwareMap.get(Servo.class, "shooterPusher");

//        clawServo = hardwareMap.get(Servo.class, "clawServo");

        //Set the motor directions
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

        //Set the motor powers to 0 by default
        frontLeftPower = 0;
        frontRightPower = 0;
        backLeftPower = 0;
        backRightPower = 0;

/*        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
 */
        shooterPusher.setPosition(0.36);
//        shooterBlocker.setPosition(0);
        //Set the zero power behavior of the motors to stop quickly

        //Set up telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //Setup the auton functions class so it can access the motors and servos on the robot and so we can use the functions from it
        AutonFunctionsTwo autFunc = new AutonFunctionsTwo(frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive, shooterPusher, shooterMotor, scuffedMotor, clawServo);
        backLeftDrive.setVelocityPIDFCoefficients(20, 0, 0, 0);
        waitForStart();
        backLeftDrive.setVelocity(100);
        double startTime = runtime.seconds();
        while (true) {
            telemetry.addData("backLeftMotor Velocity", backLeftDrive.getVelocity());
            if (runtime.seconds() - startTime > 7) {
                break;
            }
        }
    }

    public void pause(double seconds) {
        double startTime = runtime.seconds();
        while (true) {
            if (runtime.seconds() - startTime > seconds) {
                return;
            }
        }
    }

    public double TpsToRpm(double tps) {
        return tps * 60 / epr;
    }

    public double RpmToTps(double rpm) {
        return rpm * epr / 60;
    }
/*    @Override
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
*/
}