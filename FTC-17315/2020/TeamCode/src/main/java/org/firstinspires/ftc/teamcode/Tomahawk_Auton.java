/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file illustrates the concept of driving a path based on encoder counts.
 * It uses the common Pushbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 * The code REQUIRES that you DO have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByTime;
 *
 *  This code ALSO requires that the drive Motors have been configured such that a positive
 *  power command moves them forwards, and causes the encoders to count UP.
 *
 *   The desired path in this example is:
 *   - Drive forward for 48 inches
 *   - Spin right for 12 Inches
 *   - Drive Backwards for 24 inches
 *   - Stop and close the claw.
 *
 *  The code is written using a method called: encoderDrive(speed, leftInches, rightInches, timeoutS)
 *  that performs the actual movement.
 *  This methods assumes that each movement is relative to the last stopping place.
 *  There are other ways to perform encoder based moves, but this method is probably the simplest.
 *  This code uses the RUN_TO_POSITION mode to enable the Motor controllers to generate the run profile
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */



/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Basic: Linear OpMode", group="Linear Opmode")
@Disabled
public class Tomahawk_Auton extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotor shooter = null;
    static final double MOTOR_TICK_COUNT= 1120;
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_Front_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_Front_drive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "left_Back_Drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_Back_Drive");




        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();
        /*
        // run until the end of the match (driver presses STOP)
         while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;

            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x;
            leftFrontDrivePower = Range.clip(drive + turn, -1.0, 1.0) ;
            rightBackDrivePower = Range.clip(drive - turn, -1.0, 1.0) ;
            leftFrontDrive = Range.clip(drive + turn, -1.0, 1.0) ;
            rightBackDrive = Range.clip(drive + turn, -1.0, 1.0) ;

            telemetry.addData("Status","Resetting Encoders");
            telemetry.update();



            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y ;
            // rightPower = -gamepad1.right_stick_y ;
            */
            // Send calculated power to wheels
            // Robot moves for power shot




            forward();
            turnOffMotors();
            moveToLeft();
            // shoot rings at power shot
            shooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // SETS TO 0
            shooter.setTargetPosition(420); // number can change after testing)
            shooter.setPower(1);
            shooter.setMode(DcMotor.RunMode.RUN_TO_POSITION); // SETS TO 0
            moveToRight();
            turnOffMotors();
            //shoot rings at high goal
            shooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // SETS TO 0
            shooter.setTargetPosition(420); // number can change after testing)
            shooter.setPower(1);
            shooter.setMode(DcMotor.RunMode.RUN_TO_POSITION); // SETS TO 0





            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftFrontDrive, rightFrontDrive);
            telemetry.update();
        }

    private void forward() {
        runtime.reset();
        while (runtime.seconds() < 5) {
            leftFrontDrive.setPower(1);
            rightFrontDrive.setPower(1);
            leftBackDrive.setPower(1);
            rightBackDrive.setPower(1);
        }
        stop();
    }

    // turn off motors
    private void turnOffMotors() {
        leftFrontDrive.setPower(0);
        rightFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightBackDrive.setPower(0);
    }
    //turn around counterclockwise
    private void turnAroundClockwise() {
        runtime.reset();
        while (runtime.seconds() < 5) {
            leftFrontDrive.setPower(-1);
            leftBackDrive.setPower(1);
            rightFrontDrive.setPower(1);
            rightBackDrive.setPower(-1);
        }
        stop();
    }




    //moving robot for high goal
    private void moveToRight() {

        runtime.reset();
        while (runtime.seconds() < 5) {
            leftFrontDrive.setPower(1);
            leftBackDrive.setPower(-1);
            rightFrontDrive.setPower(-1);
            rightBackDrive.setPower(1);
        }
        stop();
    }

    private void moveToLeft() {

        runtime.reset();
        while (runtime.seconds() < 5) {
            leftFrontDrive.setPower(-1);
            leftBackDrive.setPower(1);
            rightFrontDrive.setPower(1);
            rightBackDrive.setPower(-1);
        }
        stop();
    }


    // stop robot
    private void stopRobot() {
        leftFrontDrive.setPower(0);
        rightFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightBackDrive.setPower(0);
    }

}
