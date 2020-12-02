package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Basic: Iterative OpMode", group="driver control")

public class teleop extends OpMode
{

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftBackDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotor leftFrontDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor conveyorMotor = null;
    private DcMotor shooterMotor = null;
    private DcMotor intakeMotor = null;
    private Servo wobbleClaw = null;
    private Servo wobbleClaw2 = null;
    private Servo shooterAngle = null;


    private double scalar = 1;
    private boolean isdpadpressed = false;
    private boolean isdpadpressedtwo = false;
    private double shooterangleposition = 135;




    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        leftBackDrive  = hardwareMap.get(DcMotor.class, "leftBack");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rightBack");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFront");
        leftFrontDrive = hardwareMap.get(DcMotor.class, "leftFront");
//        conveyorMotor = hardwareMap.get(DcMotor.class, "conveyorMotor");
//        shooterMotor = hardwareMap.get(DcMotor.class,"shooterMotor");
//        intakeMotor = hardwareMap.get(DcMotor.class,"intakeMotor");
//
//        wobbleClaw = hardwareMap.get(Servo.class,"wobbleClaw");
//        wobbleClaw2 = hardwareMap.get(Servo.class,"wobbleClaw2");
//        shooterAngle = hardwareMap.get(Servo.class,"shooterAngle");



        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);

        telemetry.update();
    }

    @Override
    public void start() {
        runtime.reset();
    }


    @Override
    public void loop() {

        double leftBackPower;
        double rightBackPower;
        double leftFrontPower;
        double rightFrontPower;
        double turnPower;


        if (gamepad1.left_trigger > 0) {
            turnPower = -1;

        }
        else if (gamepad1.right_trigger > 0) {
            turnPower = 1;
        }
        else {
            turnPower = 0;
        }


        rightFrontPower = -gamepad1.left_stick_y - gamepad1.left_stick_x -turnPower;
        rightBackPower = -gamepad1.left_stick_y + gamepad1.left_stick_x -turnPower;
        leftFrontPower =-gamepad1.left_stick_y + gamepad1.left_stick_x + turnPower;
        leftBackPower = -gamepad1.left_stick_y - gamepad1.left_stick_x + turnPower;

        if(gamepad2.a ) {
        conveyorMotor.setPower(1);
        }
        else if (gamepad2.b) {
            conveyorMotor.setPower(-1);
        }
        else {
                conveyorMotor.setPower(0);
        }

        if(gamepad2.x) {
            wobbleClaw.setPosition(20);
        }
        else if  (gamepad2.y) {
            wobbleClaw.setPosition(90);
        }
        if(gamepad2.right_trigger >0 ) {
            wobbleClaw2.setPosition(90);
        }
        if(gamepad2.left_trigger>0) {
            wobbleClaw2.setPosition(0);
        }

       if(gamepad1.a) {
        intakeMotor.setPower(1);
       }
       else if(gamepad1.b) {
           intakeMotor.setPower(-1);
       }


       if(gamepad2.left_bumper) {
           shooterMotor.setPower((1));
       }
       else if(gamepad2.right_bumper)  {
           shooterMotor.setPower(0);
       }


       shooterAngle.setPosition(shooterangleposition);

       if (!isdpadpressedtwo) {
            if (gamepad2.dpad_up) {
                if (shooterangleposition < 180) {
                    shooterangleposition -= 5;
                    isdpadpressedtwo = true;
                }
            }
            else if (gamepad2.dpad_down ) {
                if (shooterangleposition > 0) {
                    shooterangleposition += 5;
                    isdpadpressedtwo = true;
                }
            }
            else if (gamepad2.dpad_left) {
                shooterangleposition = 30;
                isdpadpressedtwo = true;
            }
            else if (gamepad2.dpad_right) {
                shooterangleposition = 135;
                isdpadpressedtwo = true;
            }

        }
        else if(!gamepad2.dpad_up && !gamepad2.dpad_down && !gamepad2.dpad_right && !gamepad2.dpad_left)  {
            isdpadpressedtwo = false;
        }

        shooterAngle.setPosition(shooterangleposition);
      





        double greatestPower = Math.abs(rightFrontPower);

        if (Math.abs(rightBackPower) > greatestPower) {
            greatestPower = Math.abs(rightBackPower);
        }
        if (Math.abs(leftFrontPower) > greatestPower) {
           greatestPower = Math.abs(leftFrontPower);
        }
        if (Math.abs(leftBackPower) > greatestPower) {
           greatestPower = Math.abs(leftBackPower);
        }

        if (greatestPower != 0) {
            rightBackPower /= greatestPower;
            rightFrontPower /= greatestPower;
            leftBackPower /= greatestPower;
            leftFrontPower /= greatestPower;
         }


        if (!isdpadpressed) {
            if (gamepad1.dpad_up) {
                if (scalar < .9) {
                    scalar += .1;
                    isdpadpressed = true;
                }
            }
            else if (gamepad1.dpad_down ) {
                if (scalar > .1) {
                    scalar -= .1;
                    isdpadpressed = true;
                }
            }
            else if (gamepad1.dpad_left) {
                scalar = 1;
                isdpadpressed = true;
            }
            else if (gamepad1.dpad_right) {
                scalar = .25;
                isdpadpressed = true;
            }

        }
        else if(!gamepad1.dpad_up && !gamepad1.dpad_down && !gamepad1.dpad_right && !gamepad1.dpad_left)  {
            isdpadpressed = false;
        }

        rightBackPower *= scalar;
        rightFrontPower *= scalar;
        leftBackPower *= scalar;
        leftFrontPower *= scalar;





        rightFrontDrive.setPower(rightFrontPower);
        rightBackDrive.setPower(rightBackPower);
        leftFrontDrive.setPower(leftFrontPower);
        leftBackDrive.setPower(leftBackPower);

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }

    @Override
    public void stop() {
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);
        leftBackDrive.setPower(0);
        leftFrontDrive.setPower(0);


    }

}

