package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Hunga Munga TeleOp", group="Iterative Opmode")
public class OpModeCC extends OpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    //Initialize drive train motors
    public DcMotor frontLeftDrive;
    public DcMotor frontRightDrive;
    public DcMotor backLeftDrive;
    public DcMotor backRightDrive;

    //Create motor power variables for drive train motors
    private double frontLeftPower;
    private double frontRightPower;
    private double backLeftPower;
    private double backRightPower;

    //Initialize intake motor and create the power variable
    private DcMotor intakeMotor;
    private double intakeMotorPower;

    private boolean intakeOn;

    @Override
    public void init() {
        /*initialize your motors here using the hardwareMap variable and the .get method within it.
        Map the motor objects to the physical motors using the control hub*/
        frontLeftDrive = hardwareMap.get(DcMotor.class,"leftFront");
        frontRightDrive = hardwareMap.get(DcMotor.class,"rightFront");
        backLeftDrive = hardwareMap.get(DcMotor.class,"leftBack");
        backRightDrive = hardwareMap.get(DcMotor.class,"rightBack");

        intakeMotor = hardwareMap.get(DcMotor.class,"intake");


        //Set the motor directions
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

        intakeMotor.setDirection(DcMotor.Direction.FORWARD);


        //Set the motor powers to 0 by default
        frontLeftPower = 0;
        frontRightPower = 0;
        backLeftPower = 0;
        backRightPower = 0;

        //Set the zero power behavior of the motors to stop quickly
        stop();

        //Create a boolean to store rather or not the intake is turned on or off, and set it to false by default
        intakeOn = false;

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        /*use the left stick of gamepad1 in order to find what angle the left stick is at.
        use gamepad1.left_stick_x and gamepad1.left_stick_y to get the current x and y positions
        of the left stick on the first gamepad.*/

        //Get the values of the joystick input filtered through the corresponding equation for each wheel/motor
        frontLeftPower = gamepad1.left_stick_x - gamepad1.left_stick_y + gamepad1.right_stick_x;
        frontRightPower = -gamepad1.left_stick_x - gamepad1.left_stick_y - gamepad1.right_stick_x;
        backLeftPower = -gamepad1.left_stick_x - gamepad1.left_stick_y + gamepad1.right_stick_x;
        backRightPower = gamepad1.left_stick_x - gamepad1.left_stick_y - gamepad1.right_stick_x;

        /* Incorrect equations:
        frontLeftPower =  gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x;
        frontRightPower=  gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x;
        backLeftPower  = -gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x;
        backRightPower = -gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x; */

        //Keep the motor powers between -1 and 1 (inclusive) without changing the proportion between the values
        double greatestPower = Math.abs(frontLeftPower);
        if (Math.abs(frontRightPower) > greatestPower) {
            greatestPower = Math.abs(frontRightPower);
        }
        if (Math.abs(backLeftPower) > greatestPower) {
            greatestPower = Math.abs(backLeftPower);
        }
        if (Math.abs(backRightPower) > greatestPower) {
            greatestPower = Math.abs(backRightPower);
        }
        if (greatestPower > 1) {
            frontLeftPower /= greatestPower;
            frontRightPower /= greatestPower;
            backLeftPower /= greatestPower;
            backRightPower /= greatestPower;
        }

        //Slo-Mo when holding LB
        if (gamepad1.left_bumper){
            frontLeftPower *= 0.5;
            frontRightPower *= 0.5;
            backLeftPower *= 0.5;
            backRightPower *= 0.5;
        }

        //Set the motor powers to their corresponding values
        frontLeftDrive.setPower(frontLeftPower);
        frontRightDrive.setPower(frontRightPower);
        backLeftDrive.setPower(backLeftPower);
        backRightDrive.setPower(backRightPower);

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }

    public void stop() {
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeftDrive.setPower(0);
        frontLeftDrive.setPower(0);
        frontLeftDrive.setPower(0);
        frontLeftDrive.setPower(0);
    }
}
