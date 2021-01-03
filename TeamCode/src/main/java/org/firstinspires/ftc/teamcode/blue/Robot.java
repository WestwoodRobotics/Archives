package org.firstinspires.ftc.teamcode.blue;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Robot{
    private DcMotor leftBack;
    private DcMotor rightBack;
    private DcMotor leftFront;
    private DcMotor rightFront;
    private ElapsedTime runtime = new ElapsedTime();

    public Robot(DcMotor leftBack, DcMotor rightBack, DcMotor leftFront, DcMotor rightFront) {
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.leftBack = leftBack;
        this.rightBack = rightBack;
        this.leftFront = leftFront;
        this.rightFront = rightFront;
    }

    public void moveForward(double inches) {
        double seconds = inches/5.0;
        runtime.reset();
        while (runtime.seconds() < seconds) {
            leftFront.setPower(1);
            rightFront.setPower(-1);
            leftBack.setPower(-1);
            rightBack.setPower(1);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
    }

    public void moveBackward(double inches) {
        double seconds = inches/5.0;
        runtime.reset();
        while (runtime.seconds() < seconds) {
            leftFront.setPower(-1);
            rightFront.setPower(1);
            leftBack.setPower(1);
            rightBack.setPower(-1);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
    }

    public void moveRight(double inches) {
        double seconds = inches/5.0;
        runtime.reset();
        while (runtime.seconds() < seconds) {
            leftFront.setPower(1);
            rightFront.setPower(1);
            leftBack.setPower(1);
            rightBack.setPower(1);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
    }

    public void moveLeft(double inches) {
        double seconds = inches/5.0;
        runtime.reset();
        while (runtime.seconds() < seconds) {
            leftFront.setPower(-1);
            rightFront.setPower(-1);
            leftBack.setPower(-1);
            rightBack.setPower(-1);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
    }
}
