package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Hunga Munga TeleOp", group="Iterative Opmode")
public class OpModeTeleOp extends OpMode {
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
    private int epr = 28;

    //Initialize intake motor and create the power variable
    public DcMotor intakeMotor;
    public DcMotor scuffedMotor;

    //Initialize the servo for opening and closing the claw
    public Servo clawServo;

    //Boolean to store if the claw is open or closed as well as a doubles for setting the claw servo's position
//    private boolean isClawOpen;
//    private double clawServoPosition;
//    private double clawOpenPosition;
//    private double clawClosedPosition;

    //Initialize motors and servos for the shooter
    public Servo shooterBlocker;
    public Servo shooterPusher;
    public DcMotorEx shooterMotor;
//    public DcMotor shooterAngler;

    //Constants representing the servo positions for the shooter blocker and pusher (needs testing for accurate positions)
    public final double BLOCKER_OPEN_POSITION = .5;
    public final double BLOCKER_CLOSED_POSITION = 0;
    public final double PUSHER_OPEN_POSITION = 0.333;
    public final double PUSHER_CLOSED_POSITION = 0;

    public int shooterEncoderCounts; //Variable we use in our move functions to store what the encoder counts should be at once we finish moving the desired distance
//    public final double P_CONST = 0; //not found yet
//    public final double D_CONST = 0; //not found yet

//    private double currentShooterHeight = 0; //Need testing to find default shooter height
//    private double newShooterHeight;
//    private double heightDifference;

//    private final int HIGH_GOAL_ANGLE = 30; //Needs to test for actual value
//    private final int MID_GOAL_ANGLE = 15; //Needs to test for actual value
//    private final int POWER_SHOT_TARGET_ANGLE = 45; //Needs to test for actual value

    //Boolean to store if the intake is on or off
    private boolean isIntakeOn;

    AutonFunctionsTwo autFunc;

    @Override
    public void init() {
        /*initialize your motors here using the hardwareMap variable and the .get method within it.
        Map the motor objects to the physical motors using the control hub*/
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");

        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        scuffedMotor = hardwareMap.get(DcMotor.class, "scuffedMotor");

        clawServo = hardwareMap.get(Servo.class, "clawServo");

        shooterMotor = hardwareMap.get(DcMotorEx.class, "shooterMotor");
//        shooterAngler = hardwareMap.get(DcMotor.class, "shooterAngler");

        shooterBlocker = hardwareMap.get(Servo.class, "shooterBlocker");
        shooterPusher = hardwareMap.get(Servo.class, "shooterPusher");


        //Set the motor directions
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

        intakeMotor.setDirection(DcMotor.Direction.REVERSE);
        shooterMotor.setDirection(DcMotor.Direction.FORWARD);
        scuffedMotor.setDirection(DcMotor.Direction.REVERSE);
        //Set the zero power behavior of the motors to stop quickly
        stop();

        //Set the default values for toggle control booleans such as if the intake is on or off or if the claw is open or closed
//        isIntakeOn = false;
//        isClawOpen = true;

        //Set the minimum and maximum values for the claw servo and set the claw servo position to the open position by default
//        clawClosedPosition = 0;
//        clawOpenPosition = 0.5;

        //Set up telemetry
//        telemetry.addData("Status", "Initialized");
//        telemetry.update();

        AutonFunctionsTwo autFunc = new AutonFunctionsTwo(frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive, shooterPusher, shooterMotor, scuffedMotor, clawServo);
//        shooterMotor.setVelocityPIDFCoefficients(400, 0, 0, 0);
    }

    @Override
    public void loop() {
        /*use the left stick of gamepad1 in order to find what angle the left stick is at.
        use gamepad1.left_stick_x and gamepad1.left_stick_y to get the current x and y positions
        of the left stick on the first gamepad.*/
        //Set up telemetry
//        telemetry.addData("Velocity", this.TpsToRpm(shooterMotor.getVelocity()));
        telemetry.addData("Velocity", shooterMotor.getVelocity());
        telemetry.addData("clawServoPosition", clawServo.getPosition());
        telemetry.update();


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
        if (gamepad1.left_bumper) {
            frontLeftPower *= 0.5;
            frontRightPower *= 0.5;
            backLeftPower *= 0.5;
            backRightPower *= 0.5;
        }

        else if (gamepad1.right_bumper) {
            frontLeftPower *= 0.25;
            frontRightPower *= 0.25;
            backLeftPower *= 0.25;
            backRightPower *= 0.25;
        }
        //Set the motor powers to their corresponding values
        frontLeftDrive.setPower(frontLeftPower);
        frontRightDrive.setPower(frontRightPower);
        backLeftDrive.setPower(backLeftPower);
        backRightDrive.setPower(backRightPower);


        //When LB or RB is pressed turn the intake on or off and change the variable to match the current state
        if (gamepad2.left_bumper) {
            intakeMotor.setPower(1);
        } else if (gamepad2.right_bumper) {
            intakeMotor.setPower(-1);
        } else {
            intakeMotor.setPower(0);
        }

        if (gamepad2.x) {
            scuffedMotor.setPower(1);
        } else if (gamepad2.y) {
            scuffedMotor.setPower(-1);
        } else {
            scuffedMotor.setPower(0);
        }

        if (gamepad2.right_trigger > 0) {
//            shooterMotor.setVelocity(RpmToTps(3650));
            shooterMotor.setVelocity(1695);
        } else if (gamepad2.a) {
//            shooterMotor.setPower(0.55);
//            shooterMotor.setVelocity(RpmToTps(3100));
            shooterMotor.setVelocity(1450);
        } else if (gamepad2.b) {
//            shooterMotor.setPower(0.68);
//            shooterMotor.setVelocity(RpmToTps(3500));
            shooterMotor.setVelocity(1650);
        } else {
            shooterMotor.setPower(0);
        }

        if (gamepad2.left_trigger > 0) {
            //turn blocker servo 90 degrees
//            shooterBlocker.setPosition(0.5);
//            this.pause(1);
            //turn the shooter push servo 60 degrees and then back 60 degrees
            shooterPusher.setPosition(0.1);
            this.pause(1);
            shooterPusher.setPosition(0.36);
            //turn blocker servo 90 degrees counter clockwise
            this.pause(0.75);
//            shooterBlocker.setPosition(0);
        }

        if (gamepad2.dpad_up) {
            clawServo.setPosition(0);
        }

        else if (gamepad2.dpad_down) {
            clawServo.setPosition(0.75);
        }

        else if (gamepad2.dpad_right) {
            clawServo.setPosition(0.25);
        }

        else if (gamepad2.dpad_left) {
            clawServo.setPosition(0.5);
        }
/*        if (gamepad2.right_trigger > 0) {
            //start spinning the shooter motor
            shooterMotor.setPower(1);
            //turn blocker servo 90 degrees
            shooterBlocker.setPosition(BLOCKER_OPEN_POSITION);
            this.pause(0.2);
            //turn the shooter push servo 60 degrees and then back 60 degrees
            shooterPusher.setPosition(PUSHER_OPEN_POSITION);
            this.pause(0.3);
            shooterPusher.setPosition(PUSHER_CLOSED_POSITION);
            //turn blocker servo 90 degrees counter clockwise
            this.pause(0.3);
            shooterBlocker.setPosition(BLOCKER_CLOSED_POSITION);
            //stop spinning the shooter motor
            shooterMotor.setPower(0);
        }
        if (gamepad2.left_trigger > 0) {
            //start spinning the shooter motor
            shooterMotor.setPower(1);
            //turn blocker servo 90 degrees
            shooterBlocker.setPosition(BLOCKER_OPEN_POSITION);
            //turn the shooter push servo 60 degrees and then back 60 degrees
            for (int i = 0; i < 3 ; i++) {
                //delay
                this.pause(0.5);
                shooterPusher.setPosition(PUSHER_OPEN_POSITION);
                this.pause(0.5);
                shooterPusher.setPosition(PUSHER_CLOSED_POSITION);
            }
            //turn blocker servo 90 degrees counter clockwise
            shooterBlocker.setPosition(BLOCKER_CLOSED_POSITION);
            //stop spinning the shooter motor
            shooterMotor.setPower(0);
        }
*/
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


/*        if (gamepad2.dpad_left) {
            angleShooter(POWER_SHOT_TARGET_ANGLE);
        }
        if (gamepad2.dpad_up) {
            angleShooter(HIGH_GOAL_ANGLE);
        }
        if (gamepad2.dpad_right) {
            angleShooter(MID_GOAL_ANGLE);
        }
*/

//        telemetry.addData("Status", "Run Time: " + runtime.toString());
//        telemetry.update();
    }

    public void stop() {
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        shooterMotor.setPower(0);
        intakeMotor.setPower(0);
    }

/*    public void shooterPID (int shooterAnglerPower) {
        double prevError = shooterEncoderCounts - shooterAngler.getCurrentPosition();
        double prevTime = runtime.seconds();
        while (Math.abs(prevError) > 1) {
            double curTime = runtime.seconds();
            double curError = shooterEncoderCounts - shooterAngler.getCurrentPosition();
            double p = P_CONST * curError;
            double d = D_CONST * (curError - prevError)/(curTime - prevTime);
            double output = p + d;
            shooterAngler.setPower(shooterAnglerPower);
            prevError = curError;
            prevTime = curTime;
        }
    }
    */

    //Use the runtime/elapsed time to start a while loop (which will prevent any other code from running) the ends after a desired amount of time has passed
    public void pause (double seconds) {
        double startTime = runtime.seconds();
        while (true) {
            if (runtime.seconds() - startTime > seconds) {
                return;
            }
        }
    }


/*    public void angleShooter(int angle) {
        newShooterHeight = angleToHeight(angle);
        heightDifference = newShooterHeight - currentShooterHeight;
        //Translate heightDifference into encoder counts and use PID to move the motor correctly in order to angle the shooter
        if (heightDifference < 0) {
            shooterEncoderCounts = 1; //Use current height and new height to calculate what the encoder counts should be when the angling is done (factor in current encoder counts - or + depending on direction/height difference sign)
        } else if (heightDifference > 0) {
            shooterEncoderCounts = -1;//Use current height and new height to calculate what the encoder counts should be when the angling is done (factor in current encoder counts - or + depending on direction/height difference sign)
        }
        currentShooterHeight = newShooterHeight;
    }
    public static double angleToHeight (int angleDesired) {
        double radAngle = Math.toRadians(angleDesired);
        return Math.tan(radAngle) * (96.0 - (41.0/Math.sin(radAngle)));
    }
 */
    public double TpsToRpm (double tps) {
        return tps * 60 / epr;
    }

    public double RpmToTps (double rpm) {
        return rpm * epr / 60;
    }
}