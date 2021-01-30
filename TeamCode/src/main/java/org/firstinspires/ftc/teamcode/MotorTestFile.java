package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "TestOpModeForCOMP", group = "Iterative Opmode")

public class MotorTestFile extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();

    // initialize motors
    //private DcMotor frontLeft, frontRight, backLeft, backRight, intakeLeft, intakeRight, shooter, arm;
    private DcMotor frontLeft, frontRight, backLeft, backRight, intakeLeft, shooter, arm;
    private Servo claw1, claw2, shooterHelper;

    // code to run ONCE when the driver hits INIT
    @Override
    public void init() {
        // put all motors on the hardware map
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


        // set turn directions of all motors
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        intakeLeft.setDirection(DcMotor.Direction.FORWARD);
        //intakeRight.setDirection(DcMotor.Direction.FORWARD);
        shooter.setDirection(DcMotor.Direction.FORWARD);
        arm.setDirection(DcMotor.Direction.FORWARD);

        // tell driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        while (gamepad1.x) {
            frontLeft.setPower(1);
        }
        while (gamepad1.y) {
            frontRight.setPower(1);
        }
        while (gamepad1.a) {
            backLeft.setPower(1);
        }
        while (gamepad1.b) {
            backRight.setPower(1);
        }

        //arm
        while (gamepad1.dpad_up) {
            arm.setPower(0.2);
        }
        while (gamepad1.dpad_down) {
            arm.setPower(-0.2);
        }

        //intake left
        while (gamepad1.left_trigger != 0) {
            intakeLeft.setPower(0.5);
        }
        while (gamepad1.right_trigger != 0) {
            intakeLeft.setPower(-0.5);
        }

        //shooter
        while (gamepad1.left_bumper) {
            shooter.setPower(1);
        }

        //claw servos
        while (gamepad1.dpad_left) {
            claw1.setPosition(1);
        }
        while (gamepad1.dpad_right) {
            claw1.setPosition(0);
        }

        while (gamepad1.left_stick_button) {
            claw2.setPosition(1);
        }
        while (gamepad1.right_stick_button) {
            claw2.setPosition(0);
        }

        //shooter servo
        while (gamepad1.right_bumper) {
            shooterHelper.setPosition(1);
        }

        // update runtime and powers of mechanisms on telemetry
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        //telemetry.addData("Motors", "intakeLeft (%.2f), intakeRight (%.2f), shooter (%.2f), arm (%.2f)", intakeLeft.getPower(), intakeRight.getPower(), shooter.getPower(), arm.getPower());
        telemetry.addData("Motors", "intakeLeft (%.2f), shooter (%.2f), arm (%.2f)", intakeLeft.getPower(), shooter.getPower(), arm.getPower());

        telemetry.update();
    }
}
