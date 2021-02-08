package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class TeleOpMethods extends OpMode{
    private ElapsedTime runtime = new ElapsedTime();

    // initialize motors
    //private DcMotor frontLeft, frontRight, backLeft, backRight, intakeLeft, intakeRight, shooter, arm;
    private DcMotor frontLeft, frontRight, backLeft, backRight, intakeLeft, shooter, arm;
    private Servo claw1, claw2, shooterHelper;

    private final double clawMinPos = 0, clawMaxPos = 1;

    public TeleOpMethods(DcMotor frontLeft, DcMotor frontRight, DcMotor backLeft, DcMotor backRight, DcMotor intakeLeft, DcMotor shooter, DcMotor arm, Servo claw1, Servo claw2, Servo shooterHelper) {
        // 1st comp constructor
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;
        this.intakeLeft = intakeLeft;
        this.shooter = shooter;
        this.arm = arm;
        this.claw1 = claw1;
        this.claw2 = claw2;
        this.shooterHelper = shooterHelper;
    }

    /**
     * DRIVETRAIN METHOD
     * (GAMEPAD 1)
     * turns based on bumpers, moves based on left joystick
     * sets turnNum based on bumpers
     */
    public void setDriveTrainPowers() {
        double leftStickX1 = gamepad1.left_stick_x;
        double leftStickY1 = -gamepad1.left_stick_y;
        double turnNum = 0;

        if (gamepad1.left_bumper && !gamepad1.right_bumper) {
            turnNum = -1;
        } else if (!gamepad1.left_bumper && gamepad1.right_bumper) {
            turnNum = 1;
        } else {
            turnNum = 0;
        }

        // set powers for drivetrain using left joystick and turnNum (accounts for weird orientation)
        double frontLeftPower = -leftStickY1 + leftStickX1 + turnNum ;
        double frontRightPower = leftStickY1 + leftStickX1 - turnNum;
        double backLeftPower = leftStickY1 + leftStickX1 + turnNum;
        double backRightPower = -leftStickY1 + leftStickX1 - turnNum;

        // scale down drivetrain power variables to fit within range [-1, 1]
        double maxPower = Math.max(Math.max(Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower)), Math.abs(backLeftPower)), Math.abs(backRightPower));
        if (maxPower > 1) {
            frontLeftPower /= maxPower;
            frontRightPower /= maxPower;
            backLeftPower /= maxPower;
            backRightPower /= maxPower;
        }
    }

    public boolean isClawOpen() {
        if(claw1.getPosition() == clawMaxPos && claw2.getPosition() == clawMinPos) {
            return true;
        } else {
            return false;
        }
    }


}
