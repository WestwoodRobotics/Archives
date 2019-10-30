package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Basic: Iterative OpMode", group="Iterative OpMode")
public class TeleopOpMode extends OpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor centerDrive = null;

    Double SLOWNESS = 0.2;
    Double SPEED_MULTIPLIER = 1.25;

    boolean buttonStateSlow=true;
    boolean numButtonSlow=true;

    double leftPower;
    double rightPower;
    double centerPower;

    /*
     * Code to run ONCE when t+he driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftDrive  = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        centerDrive = hardwareMap.get(DcMotor.class, "centerDrive");


        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        centerDrive.setDirection(DcMotor.Direction.REVERSE);



        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override

    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry




        // Choose to drive using either Tank Mode, or POV Mode
        // Comment out the method that's not used.  The default below is POV.
        //
        //        // POV Mode uses left stick to go forward, and right stick to turn.
        //        // - This uses basic math to combine motions and is easier to drive straight.
        //double drive = -gamepad1.left_stick_y;
        //double turn  =  gamepad1.right_stick_x;

        //Checking if B button is pressed and changing hold to click for Slow mode
        if (gamepad1.b){
            if (numButtonSlow) {
                buttonStateSlow= !buttonStateSlow;
                numButtonSlow=false;
            }
        }
        else {
            numButtonSlow=true;
        }

        if (gamepad1.left_trigger>0 &&  gamepad1.right_trigger>0){
            centerPower=0;
        }
        else if (gamepad1.left_trigger>0) {
            centerPower = -gamepad1.left_trigger;

        }else if (gamepad1.right_trigger>0){
            centerPower=gamepad1.right_trigger;
        }else{
            centerPower=0;
        }

        if (buttonStateSlow){
            centerPower=centerPower*SLOWNESS*2;
            leftPower  = gamepad1.left_stick_y * SLOWNESS;
            rightPower = gamepad1.right_stick_y * SLOWNESS ;
        }else{
            leftPower  = gamepad1.left_stick_y;
            rightPower = gamepad1.right_stick_y;
        }




        // Show the elapsed game time and wheel power.

        centerDrive.setPower(centerPower);
        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);
        telemetry.addData("Slow Mode: ", " " + buttonStateSlow);
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Power", "Horizontal: (%.2f)", centerPower);
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
