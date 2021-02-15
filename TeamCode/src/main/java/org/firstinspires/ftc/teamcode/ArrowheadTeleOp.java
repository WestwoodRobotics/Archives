package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "TeleOp2", group = "Iterative Opmode")

/**
 * This is the TeleOp code for Team 17264 Arrowhead for the 2020-2021 FTC season. It enables
 * drivers to remotely control our robot using two controllers (GAMEPAD1 and GAMEPAD2). This
 * is done by the use of a phone connected to the Control Hub.
 *
 * @version Last updated on 2/14/2021
 */
public class ArrowheadTeleOp extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();

    /**
     * Initializes motors and servos.
     */
    private DcMotor frontLeft, frontRight, backLeft, backRight, intakeLeft, shooter, arm;
    private Servo claw1, claw2, shooterHelper;
    //private CRServo shooterHelper;

    /**
     * The code runs ONCE when the driver hits INIT on the phone. All motors and servos are put
     * on the hardware map. The zero power behavior of all motors are set to BRAKE so that the
     * motors stop moving when their powers are set to 0. Directions of the motors are set
     * individually as determined during testing.
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

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
        //shooterHelper = hardwareMap.get(CRServo.class, "shooterHelper");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //intakeRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // set turn directions of all motors
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        intakeLeft.setDirection(DcMotor.Direction.FORWARD);
        //intakeRight.setDirection(DcMotor.Direction.FORWARD);
        shooter.setDirection(DcMotor.Direction.REVERSE);
        arm.setDirection(DcMotor.Direction.FORWARD);


        // tell driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * CONTROLS:
     *
     * DRIVETRAIN (GAMEPAD1) --
     *      LEFT_STICK controls movement (right, left, forward, backward, diagonals)
     *      LEFT_TRIGGER and RIGHT_TRIGGER control turning
     *
     *
     * SHOOTER (GAMEPAD2) --
     *      LEFT_TRIGGER shoots
     *      LEFT_BUMPER shoots @ const. speed   (press & hold)
     *      A makes shooterHelper go FORWARD    (press & hold)
     *
     * INTAKES (GAMEPAD2) --
     *      RIGHT_TRIGGER makes LEFT intake go FORWARD (sucks in)
     *      RIGHT_BUMPER makes LEFT intake go BACKWARD (rejects)
     *
     * ARM (GAMEPAD2) --
     *      DPAD_UP makes the arm move up
     *      DPAD_DOWN makes the arm move down
     *      DPAD_LEFT opens the claw
     *      DPAD_RIGHT closes the claw
     */


    /**
     * This method runs REPEATEDLY after the driver hits PLAY but before they hit STOP. It
     * allows for the drivers to control all of the mechanisms of the robot.
     */
    @Override
    public void loop() {

        /* DRIVETRAIN (GAMEPAD1) --
         *      LEFT_STICK controls movement (right, left, forward, backward, diagonals)
         *      LEFT_TRIGGER and RIGHT_TRIGGER control turning
         */
        double leftStickX1 = gamepad1.left_stick_x;
        double leftStickY1 = gamepad1.left_stick_y;
        double turnNum;

        // turnNum is set to the difference in the right and left turn values
        turnNum = gamepad1.right_trigger - gamepad1.left_trigger;

        // set powers for drivetrain using left joystick and turnNum (accounts for weird orientation)
        double frontLeftPower = -leftStickY1 + leftStickX1 + turnNum;
        double frontRightPower = leftStickY1 + leftStickX1 + turnNum;
        double backLeftPower = leftStickY1 + leftStickX1 - turnNum;
        double backRightPower = -leftStickY1 + leftStickX1 - turnNum;

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

////////////////////////////////////////////////////////////////////////////////////////////////////

        /* ARM (GAMEPAD2) --
         *      DPAD_UP makes the arm move up
         *      DPAD_DOWN makes the arm move down
         *      DPAD_LEFT opens the claw
         *      DPAD_RIGHT closes the claw
         */
        if (gamepad2.dpad_up) {
            arm.setPower(0.01);
        } else if (gamepad2.dpad_down) {
            arm.setPower(-0.01);
        } else {
            arm.setPower(0);
        }

        if (gamepad2.dpad_left) {
            claw1.setPosition(0);
            //claw2.setPosition(1);
        } else if (gamepad2.dpad_right) {
            claw1.setPosition(1);
            //claw2.setPosition(0);
        }
////////////////////////////////////////////////////////////////////////////////////////////////////

        /* SHOOTER (GAMEPAD2) --
         *      LEFT_TRIGGER shoots
         *      LEFT_BUMPER shoots @ const. speed   (press & hold)
         *      A makes shooterHelper go FORWARD    (press & hold)
         */
        if (gamepad2.left_trigger != 0) {
            shooter.setPower(gamepad2.left_trigger);
        } else if (gamepad2.left_bumper) {
            shooter.setPower(0.822);
        } else {
            shooter.setPower(0);
        }

        if (gamepad2.a) {
            //runtime.reset();

            //while (runtime.seconds() < 1) {
            shooterHelper.setPosition(1);
            //}

            //runtime.reset();

            //while (runtime.seconds() < 1) {
            //    shooterHelper.setPosition(0);
            //}
        } else {
            shooterHelper.setPosition(0);
        }

////////////////////////////////////////////////////////////////////////////////////////////////////

        /* INTAKES (GAMEPAD2) --
         *      RIGHT_TRIGGER makes LEFT intake go FORWARD (sucks in)
         *      RIGHT_BUMPER makes LEFT intake go BACKWARD (rejects)
         */
        if (gamepad2.right_trigger != 0) {
            intakeLeft.setPower(gamepad2.right_trigger * 0.7);
        } else if (gamepad2.right_bumper) {
            intakeLeft.setPower(-0.1);
        } else {
            intakeLeft.setPower(0);
        }

        // update runtime and powers of mechanisms on telemetry
        telemetry.addData("Status", "Run Time: " + runtime.toString());

        telemetry.addData("Motors", "frontLeft (%.2f), frontRight (%.2f), backLeft (%.2f), backRight (%.2f)", frontLeft.getPower(), frontRight.getPower(), backLeft.getPower(), backRight.getPower());
        telemetry.addData("Motors", "intakeLeft (%.2f), shooter (%.2f), arm (%.2f)", intakeLeft.getPower(), shooter.getPower(), arm.getPower());
        //telemetry.addData("Servos", "claw1 (%.2f), claw2 (%.2f), shooterHelper (%.2f)", claw1.getPosition(), claw2.getPosition(), shooterHelper.getPower());
        telemetry.addData("Servos", "claw1 (%.2f), claw2 (%.2f), shooterHelper (%.2f)", claw1.getPosition(), claw2.getPosition(), shooterHelper.getPosition());

        telemetry.update();
    }


    /**
     * The code runs ONCE after the driver hits STOP. It overrides the loop() method.
     */
    @Override
    public void stop() {
    }
}
