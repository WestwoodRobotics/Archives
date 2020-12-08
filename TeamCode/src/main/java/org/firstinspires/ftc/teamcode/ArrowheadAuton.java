package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.*;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "Basic: Linear OpMode", group = "Linear Opmode")
public class ArrowheadAuton extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    //initialize motors
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    public void runOpMode() {

        //put all 4 motors on the hardware map
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        //set directions of all 4 motors
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        //run using encoders
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        waitForStart();

        while (opModeIsActive()) {

            //assuming start pos is at
            frontBack(1, 5);
            rightLeft(1, 5);
        }
    }

    public void frontBack(double speed, double time) {
        runtime.reset();

        while (runtime.seconds() < time) {
            frontLeft.setPower(speed);
            frontRight.setPower(speed);
            backLeft.setPower(speed);
            backRight.setPower(speed);
        }

        brakeAllMotors();
    }

    public void rightLeft(double speed, double time) {
        runtime.reset();

        //when speed is positive --> goes right
        //when speed is negative --> goes left
        //DOES NOT TURN
        while (runtime.seconds() < time) {
            frontLeft.setPower(speed);
            frontRight.setPower(-speed);
            backLeft.setPower(-speed);
            backRight.setPower(speed);
        }

        brakeAllMotors();
    }

    public void turnMid(double speed, double time) {
        runtime.reset();

        //when speed is positive --> goes right
        //when speed is negative --> goes left
        while (runtime.seconds() < time) {
            frontLeft.setPower(speed);
            frontRight.setPower(-speed);
            backLeft.setPower(speed);
            backRight.setPower(-speed);
        }

        brakeAllMotors();
    }

    public void brakeAllMotors() {
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}