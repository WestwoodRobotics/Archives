//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.CRServoImpl;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.ServoImpl;
//import com.qualcomm.robotcore.util.ElapsedTime;
//import com.qualcomm.robotcore.util.Range;
////Assumes robot moves 3ft every 5 seconds
////Assumed robot turns 10 degrees every 1 second
//@Autonomous(name="AutoRLWP", group="Linear Opmode")
//public class autoRLWP extends LinearOpMode {
//
//
//    private ElapsedTime runtime = new ElapsedTime();
//    private DcMotor leftBackDrive = null;
//    private DcMotor rightBackDrive = null;
//    private DcMotor leftFrontDrive = null;
//    private DcMotor rightFrontDrive = null;
//    private DcMotor conveyorMotor = null;
//    private DcMotor shooterMotor = null;
//    private DcMotor intakeMotor = null;
//    private CRServoImpl wobbleClaw = null;
//    private CRServoImpl wobbleClaw2 = null;
//    private CRServoImpl shooterAngle = null;
//
//    @Override
//    public void runOpMode() {
//        telemetry.addData("Status", "Initialized");
//        telemetry.update();
//
//        leftBackDrive  = hardwareMap.get(DcMotor.class, "leftBack");
//        rightBackDrive = hardwareMap.get(DcMotor.class, "rightBack");
//        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFront");
//        leftFrontDrive = hardwareMap.get(DcMotor.class, "leftFront");
//        conveyorMotor = hardwareMap.get(DcMotor.class, "conveyorMotor");
//        shooterMotor = hardwareMap.get(DcMotor.class,"shooterMotor");
//        intakeMotor = hardwareMap.get(DcMotor.class,"intakeMotor");
//
//        wobbleClaw = hardwareMap.get(CRServoImpl.class,"wobbleClaw");
//        wobbleClaw2 = hardwareMap.get(CRServoImpl.class,"wobbleClaw2");
//        shooterAngle = hardwareMap.get(CRServoImpl.class,"shooterAngle");
//
//
//
//        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
//        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
//        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
//        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
//
//        conveyorMotor.setDirection(DcMotor.Direction.FORWARD);
//        shooterMotor.setDirection(DcMotor.Direction.REVERSE);
//        intakeMotor.setDirection(DcMotor.Direction.REVERSE);
//
//        wobbleClaw.setDirection(CRServoImpl.Direction.FORWARD);
//        wobbleClaw2.setDirection(CRServoImpl.Direction.FORWARD);
//        shooterAngle.setDirection(CRServoImpl.Direction.FORWARD);
//
//        waitForStart();
//        autonfunctionsR functions = new autonfunctionsR( leftBackDrive, rightBackDrive, leftFrontDrive,
//                rightFrontDrive, conveyorMotor, shooterMotor,intakeMotor,
//                wobbleClaw, wobbleClaw2, shooterAngle, runtime);
//        runtime.reset();
//
//
//        while (opModeIsActive()) {
//
//            functions.moveForward(24);
//            functions.pause(.5);
//            functions.moveright(7.5);
//            //read rings
//            functions.pause(.5);
//
//            functions.moveleft(19.5);
//            functions.pause(.5);
//            functions.moveForward(54);
//            functions.pause(.5);
//            functions.moveright(24);
//            functions.pause(.5);
//
//
//            if (functions.numberOfRings == 0) {
//                functions.turnLeftCenter(180);
//                //lower arm, drop wobble goal, pick arm up
//                functions.turnRightCenter(180);
//            }
//
//
//            if (functions.numberOfRings == 1){
//                functions.turnRightCenter(90);
//                //lower arm, drop wobble goal, pick arm up
//                functions.turnLeftCenter(90);
//
//            }
//
//            if (functions.numberOfRings == 4){
//                functions.moveForward(44);
//                functions.pause(.5);
//                functions.turnLeftCenter(180);
//                //lower arm, drop wobble goal, pick arm up
//                functions.turnRightCenter(180);
//                functions.pause(.5);
//                functions.moveBack(44);
//            }
//
//
//
//            //this block of code shoots
//            functions.pause(.5);
//            functions.moveBack(24);
//            functions.pause(.5);
//            functions.moveleft(20.25);
//            functions.shootOneRing();
//            functions.turnLeftCenter(5.94);
//            functions.shootOneRing();
//            functions.turnLeftCenter(5.82);
//            functions.shootOneRing();
//
//            //parks at white line
//            functions.turnRightCenter(11.76);
//            functions.pause(.5);
//            functions.moveForward(21);
//
//
//
//            telemetry.addData("Status", "Run Time: " + runtime.toString());
//            telemetry.update();
//        }
//    }
//}
//
