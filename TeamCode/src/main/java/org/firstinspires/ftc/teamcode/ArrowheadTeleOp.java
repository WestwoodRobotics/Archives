package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "TeleOp2", group = "Iterative Opmode")

public class ArrowheadTeleOp extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();

    // initialize motors
    private DcMotor frontLeft, frontRight, backLeft, backRight, intakeLeft, intakeRight, shooter, arm;

    // code to run ONCE when the driver hits INIT
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // put all motors on the hardware map
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        intakeLeft = hardwareMap.get(DcMotor.class, "intakeLeft");
        intakeRight = hardwareMap.get(DcMotor.class, "intakeRight");
        shooter = hardwareMap.get(DcMotor.class, "shooter");
        arm = hardwareMap.get(DcMotor.class, "arm");


        // set turn directions of all motors
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        intakeLeft.setDirection(DcMotor.Direction.FORWARD);
        intakeRight.setDirection(DcMotor.Direction.FORWARD);
        shooter.setDirection(DcMotor.Direction.FORWARD);
        arm.setDirection(DcMotor.Direction.FORWARD);

        // tell driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * CONTROLS:
     *
     * GAMEPAD 1:
     * Position Movement (forward, backward, left, right, diagonal):
     * -- LEFT JOYSTICK (X and Y)
     *
     * Turn movement (turn on center of robot):
     * -- LEFT BUMPER turns LEFT
     * -- RIGHT BUMPER turns RIGHT
     * -- if both bumpers pressed, no turning occurs
     *
     * GAMEPAD 2:
     * Shooter:
     * -- LEFT TRIGGER sets shooter motor to go BACKWARD
     * -- RIGHT TRIGGER sets shooter motor to go FORWARD (shoots)
     *
     * Intakes:
     * -- A turns RIGHT intake FORWARD
     * -- B turns RIGHT intake BACKWARD
     * -- Y turns LEFT intake FORWARD
     * -- X turns LEFT intake BACKWARD
     *
     * Arm:
     * -- LEFT JOYSTICK (only Y)
     */


    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

        // for simplicity and easier readability
        double leftStickX = gamepad1.left_stick_x;
        double leftStickY1 = -gamepad1.left_stick_y;
        double leftStickY2 = -gamepad2.left_stick_y;
        double turnNum = 0;

        // DRIVETRAIN -- turns based on bumpers, moves based on left joystick (GAMEPAD 1)
        // sets turnNum based on bumpers
        if (gamepad1.left_bumper && !gamepad1.right_bumper) {
            turnNum = -1;
        } else if (!gamepad1.left_bumper && gamepad1.right_bumper) {
            turnNum = 1;
        } else {
            turnNum = 0;
        }

        // set powers for drivetrain using left joystick and turnNum (accounts for weird orientation)
        double frontLeftPower = -leftStickY1 + leftStickX + turnNum ;
        double frontRightPower = leftStickY1 + leftStickX - turnNum;
        double backLeftPower = leftStickY1 + leftStickX + turnNum;
        double backRightPower = -leftStickY1 + leftStickX - turnNum;

        // scale down drivetrain power variables to fit within range [-1, 1]
        double maxPower = Math.max(Math.max(Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower)), Math.abs(backLeftPower)), Math.abs(backRightPower));
        if (maxPower > 1) {
            frontLeftPower /= maxPower;
            frontRightPower /= maxPower;
            backLeftPower /= maxPower;
            backRightPower /= maxPower;
        }

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);

        // update runtime and powers of drivetrain motors on telemetry
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "frontLeft (%.2f), frontRight (%.2f), backLeft (%.2f), backRight (%.2f)", frontLeftPower, frontRightPower, backLeftPower, backRightPower);
        telemetry.update();

        // ARM -- sets power of arm motor based on left stick y (GAMEPAD 2)
        arm.setPower(leftStickY2);

        // SHOOTER -- left trigger reverses shooter, right trigger shoots (GAMEPAD 2)
        if (gamepad2.left_trigger != 0) {
            shooter.setPower(-1);
        } else if (gamepad2.right_trigger != 0) {
            shooter.setPower(1);
        } else {
            shooter.setPower(0);
        }

        // INTAKES --
        //   A makes RIGHT intake go FORWARD
        //   B makes RIGHT intake go BACKWARD
        //   Y makes LEFT intake go FORWARD
        //   X makes LEFT intake go BACKWARD
        if (gamepad2.a) {
            intakeRight.setPower(1);
        } else if (gamepad2.b) {
            intakeRight.setPower(-1);
        } else {
            intakeRight.setPower(0);
        }

        if (gamepad2.y) {
            intakeLeft.setPower(1);
        } else if (gamepad2.x) {
            intakeLeft.setPower(-1);
        } else {
            intakeLeft.setPower(0);
        }

        // update runtime and powers of mechanisms on telemetry
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "intakeLeft (%.2f), intakeRight (%.2f), shooter (%.2f), arm (%.2f)", intakeLeft.getPower(), intakeRight.getPower(), shooter.getPower(), arm.getPower());
        telemetry.update();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}