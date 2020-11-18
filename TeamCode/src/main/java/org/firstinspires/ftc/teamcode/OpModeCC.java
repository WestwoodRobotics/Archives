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

    @Override
    public void init() {
        //initialize your motors here using the hardwareMap variable and the .get method within it.
        //Map the motor objects to the physical motors using the control hub
        frontLeftDrive = hardwareMap.get(DcMotor.class,"frontLeft");
        frontRightDrive = hardwareMap.get(DcMotor.class,"frontRight");
        backLeftDrive = hardwareMap.get(DcMotor.class,"backLeft");
        backRightDrive = hardwareMap.get(DcMotor.class,"backRight");

        //Set the motor direction
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);


        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        //use the left stick of gamepad1 in order to find what angle the left stick is at.
        //use gamepad1.left_stick_x and gamepad1.left_stick_y to get the current x and y positions
        //of the left stick on the first gamepad.

        //Map joystick x and y positions to motor powers
        frontLeftDrive.setPower(Range.clip(gamepad1.left_stick_x + (gamepad1.left_stick_y*-1) + gamepad1.right_stick_x,-1,1));
        frontRightDrive.setPower(Range.clip((gamepad1.left_stick_y*-1) - gamepad1.left_stick_x - gamepad1.right_stick_x,-1,1));
        backLeftDrive.setPower(Range.clip((gamepad1.left_stick_y*-1) - gamepad1.left_stick_x + gamepad1.right_stick_x,-1,1));
        backRightDrive.setPower(Range.clip(gamepad1.left_stick_x + (gamepad1.left_stick_y*-1) - gamepad1.right_stick_x,-1,1));

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }
}
