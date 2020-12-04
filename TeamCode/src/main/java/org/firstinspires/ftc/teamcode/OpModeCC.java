package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
public class OpModeCC extends OpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;
    private DcMotor backLeftDrive;
    private DcMotor backRightDrive;


    private double frontLeftPower;
    private double frontRightPower;
    private double backLeftPower;
    private double backRightPower;

    @Override
    public void init() {
        /*initialize your motors here using the hardwareMap variable and the .get method within it.
        Map the motor objects to the physical motors using the control hub*/
        frontLeftDrive = hardwareMap.get(DcMotor.class,"frontLeft");
        frontRightDrive = hardwareMap.get(DcMotor.class,"frontRight");
        backLeftDrive = hardwareMap.get(DcMotor.class,"backLeft");
        backRightDrive = hardwareMap.get(DcMotor.class,"backRight");


        //Set the motor direction
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);


        //Set the motor powers to 0 by default
        frontLeftPower = 0;
        frontRightPower = 0;
        backLeftPower = 0;
        backRightPower = 0;


        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        /*use the left stick of gamepad1 in order to find what angle the left stick is at.
        use gamepad1.left_stick_x and gamepad1.left_stick_y to get the current x and y positions
        of the left stick on the first gamepad.*/

        //Get the values of the joystick input filtered through the corresponding equation for each wheel/motor
        frontLeftPower = gamepad1.left_stick_x + (gamepad1.left_stick_y*-1) + gamepad1.right_stick_x;
        frontRightPower = (gamepad1.left_stick_y*-1) - gamepad1.left_stick_x - gamepad1.right_stick_x;
        backLeftPower = (gamepad1.left_stick_y*-1) - gamepad1.left_stick_x + gamepad1.right_stick_x;
        backRightPower = gamepad1.left_stick_x + (gamepad1.left_stick_y*-1) - gamepad1.right_stick_x;

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

        //Set the motor powers to their corresponding values
        frontLeftDrive.setPower(frontLeftPower);
        frontRightDrive.setPower(frontRightPower);
        backLeftDrive.setPower(backLeftPower);
        backRightDrive.setPower(backRightPower);


        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }
}
