package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Hunga Munga TeleOp", group="Iterative Opmode")
public class OpModeTeleOp extends OpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    //Initialize drive train motors
    public DcMotor frontLeftDrive;
    public DcMotor frontRightDrive;
    public DcMotor backLeftDrive;
    public DcMotor backRightDrive;

    //Create motor power variables for drive train motors
    private double frontLeftPower;
    private double frontRightPower;
    private double backLeftPower;
    private double backRightPower;

    //Initialize intake motor and create the power variable
    public DcMotor intakeMotor;

    //Initialize the servo for opening and closing the claw
//    public Servo clawServo;

    //Boolean to store if the claw is open or closed as well as a doubles for setting the claw servo's position
//    private boolean isClawOpen;
//    private double clawServoPosition;
//    private double clawOpenPosition;
//    private double clawClosedPosition;

    //Initialize motors and servos for the shooter
    public Servo shooterBlocker;
    public Servo shooterPusher;
    public DcMotor shooterMotor;
    public DcMotor shooterAngler;

    //Constants representing the servo positions for the shooter blocker and pusher (needs testing for accurate positions)
    public final double BLOCKER_OPEN_POSITION = .5;
    public final double BLOCKER_CLOSED_POSITION = 0;
    public final double PUSHER_OPEN_POSITION = 0.333;
    public final double PUSHER_CLOSED_POSITION = 0;

    //Boolean to store if the intake is on or off
    private boolean isIntakeOn;


    @Override
    public void init() {
        /*initialize your motors here using the hardwareMap variable and the .get method within it.
        Map the motor objects to the physical motors using the control hub*/
        frontLeftDrive = hardwareMap.get(DcMotor.class,"frontLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class,"frontRightDrive");
        backLeftDrive = hardwareMap.get(DcMotor.class,"backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotor.class,"backRightDrive");

        intakeMotor = hardwareMap.get(DcMotor.class,"intakeMotor");

//        clawServo = hardwareMap.get(Servo.class, "clawServo");

        shooterMotor = hardwareMap.get(DcMotor.class, "shooterMotor");
        shooterAngler = hardwareMap.get(DcMotor.class, "shooterAngler");

        shooterBlocker = hardwareMap.get(Servo.class, "shooterBlocker");
        shooterPusher = hardwareMap.get(Servo.class, "shooterPusher");


        //Set the motor directions
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

        intakeMotor.setDirection(DcMotor.Direction.REVERSE);


        //Set the motor powers to 0 by default
        frontLeftPower = 0;
        frontRightPower = 0;
        backLeftPower = 0;
        backRightPower = 0;

        //Set the zero power behavior of the motors to stop quickly
        stop();

        //Set the default values for toggle control booleans such as if the intake is on or off or if the claw is open or closed
        isIntakeOn = false;
//        isClawOpen = true;

        //Set the minimum and maximum values for the claw servo and set the claw servo position to the open position by default
//        clawClosedPosition = 0;
//        clawOpenPosition = 0.5;

        //Set up telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        /*use the left stick of gamepad1 in order to find what angle the left stick is at.
        use gamepad1.left_stick_x and gamepad1.left_stick_y to get the current x and y positions
        of the left stick on the first gamepad.*/

        //Get the values of the joystick input filtered through the corresponding equation for each wheel/motor
        frontLeftPower = gamepad1.left_stick_x - gamepad1.left_stick_y + gamepad1.right_stick_x;
        frontRightPower = -gamepad1.left_stick_x - gamepad1.left_stick_y - gamepad1.right_stick_x;
        backLeftPower = -gamepad1.left_stick_x - gamepad1.left_stick_y + gamepad1.right_stick_x;
        backRightPower = gamepad1.left_stick_x - gamepad1.left_stick_y - gamepad1.right_stick_x;

        /* Incorrect equations:
        frontLeftPower =  gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x;
        frontRightPower=  gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x;
        backLeftPower  = -gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x;
        backRightPower = -gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x; */

        //Keep the motor powers between -1 and 1 (inclusive) without changing the proportion between the values
        double greatestPower = Math.abs(frontLeftPower);
        if (Math.abs(frontRightPower) > greatestPower) {
            greatestPower = Math.abs(frontRightPower);
        }
        if (Math.abs(backLeftPower) > greatestPower) {
            greatestPower = Math.abs(backLeftPower);
        }
        if (Math.abs(backRightPower) > greatestPower) {
            greatestPower = Math.abs(backRightPower);
        }
        if (greatestPower > 1) {
            frontLeftPower /= greatestPower;
            frontRightPower /= greatestPower;
            backLeftPower /= greatestPower;
            backRightPower /= greatestPower;
        }

        //Slo-Mo when holding LB
        if (gamepad1.left_bumper){
            frontLeftPower *= 0.5;
            frontRightPower *= 0.5;
            backLeftPower *= 0.5;
            backRightPower *= 0.5;
        }

        //Set the motor powers to their corresponding values
        frontLeftDrive.setPower(frontLeftPower);
        frontRightDrive.setPower(frontRightPower);
        backLeftDrive.setPower(backLeftPower);
        backRightDrive.setPower(backRightPower);


        //When LB or RB is pressed turn the intake on or off and change the variable to match the current state
        if (gamepad2.left_bumper || gamepad2.right_bumper) {
            if (isIntakeOn) {
                intakeMotor.setPower(0);
                isIntakeOn = false;
            } else if (!isIntakeOn) {
                intakeMotor.setPower(1);
                isIntakeOn = true;
            }
        }


        if (gamepad2.right_trigger > 0) {
            //Turn on the flywheel for the shooter
            shooterMotor.setPower(1);
            //Move the blocker out of the way to the ring can leave the magazine
            shooterBlocker.setPosition(BLOCKER_OPEN_POSITION);
            //Move the pusher to push the ring
            shooterPusher.setPosition(PUSHER_OPEN_POSITION);

            pause(0.5);
            shooterPusher.setPosition(PUSHER_CLOSED_POSITION); //Pull the pusher back allowing another ring to fall into the magazine
            //Move the blocker back into place to prevent rings from leaving the magazine
            shooterBlocker.setPosition(BLOCKER_CLOSED_POSITION);
            //Turn off the flywheel
            shooterMotor.setPower(0);
        }

        if (gamepad2.left_trigger > 0) {
            //Turn on the flywheel for the shooter
            shooterMotor.setPower(1);
            //Move the blocker out of the way to the ring can leave the magazine
            shooterBlocker.setPosition(BLOCKER_OPEN_POSITION);
            //Move the pusher to push the ring and bring it back to allow another ring in 3 times
            for (int i = 0; i < 3; i++ ) {
                shooterPusher.setPosition(PUSHER_OPEN_POSITION); //Pushes the ring
                //delay
                pause(1);
                shooterPusher.setPosition(PUSHER_CLOSED_POSITION); //Pulls back the pusher so another ring can fall into the magazine
            }
            //Move the blocker back into place to prevent rings from leaving the magazine
            shooterBlocker.setPosition(BLOCKER_CLOSED_POSITION);
            //Turn off the flywheel
            shooterMotor.setPower(0);
        }


        //When B is pressed open or close the claw
//        if (gamepad2.b && isClawOpen) {
//            clawServoPosition = clawClosedPosition;
//            isClawOpen = false;
//        } else if (gamepad2.b && !isClawOpen) {
//            clawServoPosition = clawOpenPosition;
//            isClawOpen = true;
//        }
//
//        //Set the position of the servo
//        clawServo.setPosition(clawServoPosition);


        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }

    public void stop() {
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }

    //Use the runtime/elapsed time to start a while loop (which will prevent any other code from running) the ends after a desired amount of time has passed
    public void pause(double seconds) {
        double startTime = runtime.seconds();

        while(runtime.seconds() - startTime < seconds) {}
    }
}
