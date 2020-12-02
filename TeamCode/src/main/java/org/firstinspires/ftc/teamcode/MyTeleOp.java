package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "OpMode", group = "Iterative Opmode")

public class MyTeleOp extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();

    //initialize motors
    private DcMotor frontLeft, frontRight, backLeft, backRight;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        //put all 4 motors on the hardware map
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        //set turn directions of all 4 motors
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        //tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

        //for simplicity and easier readability
        double leftStickX = gamepad1.left_stick_x;
        double leftStickY = gamepad1.left_stick_y;
        double turnNum = 0;

        if (gamepad1.left_bumper && !gamepad1.right_bumper) {
            turnNum = -1;
        } else if (!gamepad1.left_bumper && gamepad1.right_bumper) {
            turnNum = 1;
        } else {
            turnNum = 0;
        }

        //retrieve power values of the motors using the powerArray[] array
        frontLeft.setPower(leftStickY - turnNum - leftStickX);
        frontRight.setPower(leftStickY - turnNum + leftStickX);
        backLeft.setPower(leftStickY + turnNum + leftStickX);
        backRight.setPower(leftStickY + turnNum - leftStickX);

        //show the elapsed game time and wheel power
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
