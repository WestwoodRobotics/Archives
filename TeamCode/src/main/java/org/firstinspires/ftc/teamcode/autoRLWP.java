package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImpl;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="AutoRLWP", group="Linear Opmode")
public class autoRLWP extends LinearOpMode {


    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftBackDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotor leftFrontDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor conveyorMotor = null;
    private DcMotor shooterMotor = null;
    private DcMotor intakeMotor = null;
    private CRServoImpl wobbleClaw = null;
    private CRServoImpl wobbleClaw2 = null;
    private CRServoImpl shooterAngle = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftBackDrive  = hardwareMap.get(DcMotor.class, "leftBack");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rightBack");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFront");
        leftFrontDrive = hardwareMap.get(DcMotor.class, "leftFront");
        conveyorMotor = hardwareMap.get(DcMotor.class, "conveyorMotor");
        shooterMotor = hardwareMap.get(DcMotor.class,"shooterMotor");
        intakeMotor = hardwareMap.get(DcMotor.class,"intakeMotor");

        wobbleClaw = hardwareMap.get(CRServoImpl.class,"wobbleClaw");
        wobbleClaw2 = hardwareMap.get(CRServoImpl.class,"wobbleClaw2");
        shooterAngle = hardwareMap.get(CRServoImpl.class,"shooterAngle");



        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);

        conveyorMotor.setDirection(DcMotor.Direction.FORWARD);
        shooterMotor.setDirection(DcMotor.Direction.REVERSE);
        intakeMotor.setDirection(DcMotor.Direction.REVERSE);

        wobbleClaw.setDirection(CRServoImpl.Direction.FORWARD);
        wobbleClaw2.setDirection(CRServoImpl.Direction.FORWARD);
        shooterAngle.setDirection(CRServoImpl.Direction.FORWARD);

        waitForStart();
        autonfunctionsR functions = new autonfunctionsR("Hello!");
        runtime.reset();

        while (opModeIsActive()) {


            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}

