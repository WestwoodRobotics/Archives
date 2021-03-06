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

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 * <p>
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 * <p>
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name = "Basic: ONE-C Arcade Mecanum Opmode")
public class UNICONTROLLER_ArcDrive extends OpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor bottomleftDrive = null;
    private DcMotor bottomrightDrive = null;
    private DcMotor topleftDrive = null;
    private DcMotor toprightDrive = null;

    private DcMotor elevatorMotor = null;

    //private Servo flipperServo1 = null;
    //private Servo flipperServo2 = null;

    //private DcMotor vacuumMotor = null;

    private Servo IOMotor1 = null, IOMotor2 = null;
    private Servo hookServo1 = null, hookServo2 = null;

    int vacuumPower = 0;

    boolean vStart = false;
    boolean vPressed = false;
    boolean vPause = false;

    boolean slowStart = false;
    boolean slowPressed = false;
    boolean slowPause = false;

    boolean cStart = false, cPressed = false, cPause = false;
    boolean hStart = false, hPressed = false, hPause = false;

    boolean INPUTstarted = false;

    boolean IOstart = false, IOpressed = false, IOpause = false;

    double eHeight = 0;

    /*
    3 * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        //telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        bottomleftDrive = hardwareMap.get(DcMotor.class, "bottom_left_drive");
        bottomrightDrive = hardwareMap.get(DcMotor.class, "bottom_right_drive");
        topleftDrive = hardwareMap.get(DcMotor.class, "top_left_drive");
        toprightDrive = hardwareMap.get(DcMotor.class, "top_right_drive");

        elevatorMotor = hardwareMap.get(DcMotor.class, "elevator_motor");

        //flipperServo1 = hardwareMap.get(Servo.class, "flipper_servo1");
        //flipperServo2 = hardwareMap.get(Servo.class, "flipper_servo2");

        IOMotor1 = hardwareMap.get(Servo.class, "claw_servo1");
        IOMotor2 = hardwareMap.get(Servo.class, "claw_servo2");

        hookServo1 = hardwareMap.get(Servo.class, "hook_servo1");
        hookServo2 = hardwareMap.get(Servo.class, "hook_servo2");


        //vacuumMotor = hardwareMap.get(DcMotor.class, "vacuum_motor");
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        bottomleftDrive.setDirection(DcMotor.Direction.FORWARD);
        bottomrightDrive.setDirection(DcMotor.Direction.REVERSE);
        topleftDrive.setDirection(DcMotor.Direction.FORWARD);
        toprightDrive.setDirection(DcMotor.Direction.REVERSE);

        /*elevatorMotor.setDirection(DcMotor.Direction.FORWARD);

        flipperServo1.setDirection(Servo.Direction.FORWARD);
        flipperServo2.setDirection(Servo.Direction.REVERSE);

        vacuumMotor.setDirection(DcMotor.Direction.FORWARD);*/


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
        //double bottomleftpower, bottomrightpower, topleftpower, toprightpower;
        double leftStickY = -1 * gamepad2.left_stick_y;
        double leftStickX = gamepad2.left_stick_x;

        double angle = Math.atan((leftStickY) / (leftStickX));



        /*if (vPause == false) {
            if ((gamepad2.left_trigger > 0.5) && vStart == false && vPressed == false) {
                vStart = true;
                vPressed = true;
                vacuumMotor.setPower(0);
            } else if (!(gamepad2.left_trigger > 0.5) && vStart == true) {
                vPressed = false;
                vacuumMotor.setPower(1);
            } else if ((gamepad2left_trigger > 0.5) && vStart == true && vPressed == false) {
                vStart = false;
                vPause = true;
                vacuumMotor.setPower(0);
            }
        } else {
            if (!(gamepad2.left_trigger > 0.5)) {
                vPause = false;
            }
        }*/

        //wheel intake/outtake toggle thingy thing

        /*if (IOpause == false) {
            if ((gamepad1.x) && IOstart == false && IOpressed == false) {
                IOstart = true;
                IOpressed = true;
                //vacuumMotor.setPower(0);
            } else if (!(gamepad1.x) && IOstart == true) {
                IOpressed = false;
                IOMotor1.setPower(1);
                IOMotor2.setPower(1);
            } else if ((gamepad1.x) && IOstart == true && IOpressed == false) {
                IOstart = false;
                IOpause = true;
            }
        } else {
            if (!(gamepad2.left_trigger > 0.5)) {
                vPause = false;
            }
        }*/

        // SLOW MODE


        //Tangent Inverse only goes from -pi/2 to pi/2, so I have to add some test cases to make sure
        //the angle is correct

        double[] arr = new double[4];

        if (1 + 1 == 2) {
            if (leftStickX < 0 && leftStickY < 0) { //3rd Quadrant
                angle = Math.atan((leftStickY) / (leftStickX)) + Math.PI;
            } else if (leftStickY == 0 && leftStickX < 0) {
                angle = Math.PI;
            } else if (leftStickY > 0 && leftStickX < 0) {
                angle = Math.atan((leftStickY) / (leftStickX)) + Math.PI;
            } else if (leftStickX == 0 && leftStickY > 0) {
                angle = 0.5 * Math.PI;
            } else if (leftStickX == 0 && leftStickY < 0) {
                angle = 3 * Math.PI / 2;
            } else if (leftStickX > 0 && leftStickY < 0) {
                angle += 2 * Math.PI;
            }
            angle = Math.toDegrees(angle); //Changes angle from radians to degrees

            if (angle == 0) {
                arr = new double[]{1, -1, -1, 1};
            } else if (angle > 0 && angle < 90) {
                arr = new double[]{1, angle / 45 - 1, angle / 45 - 1, 1};
            } else if (angle == 90) {
                arr = new double[]{1, 1, 1, 1};
            } else if (angle > 90 && angle < 180) {
                arr = new double[]{3 - angle / 45, 1, 1, 3 - angle / 45};
            } else if (angle == 180) {
                arr = new double[]{-1, 1, 1, -1};
            } else if (angle > 180 && angle < 270) {
                arr = new double[]{-1, 5 - angle / 45, 5 - angle / 45, -1};
            } else if (angle == 270) {
                arr = new double[]{-1, -1, -1, -1};
            } else if (angle > 270) {
                arr = new double[]{7 - angle / 45, -1, -1, 7 - angle / 45};
            }
        }



        if (gamepad2.y == true) {
            elevatorMotor.setPower(0.75);
            //eHeight += 1;
        } else if (gamepad2.a == true) { //2.a
            elevatorMotor.setPower(-0.75);
            //eHeight -= 1;
        } else {
            elevatorMotor.setPower(0);
        }

        /*
        if (gamepad2.x) {
            flipperServo1.setPosition(180);
            flipperServo2.setPosition(180);
        } else if (gamepad2.b) {
            flipperServo1.setPosition(0);
            flipperServo2.setPosition(0);
        }*/

        if (cPause == false) {
            if (gamepad2.x && cStart == false && cPressed == false) {
                cStart = true;
                cPressed = true;
            } else if (!gamepad2.x && cStart == true) {
                cPressed = false;
                IOMotor1.setPosition(0.5);
                IOMotor2.setPosition(0.5);
            } else if (gamepad2.x && cStart == true && cPressed == false) {
                cStart = false;
                cPause = true;
                IOMotor1.setPosition(0.75);
                IOMotor2.setPosition(0.25);
            }
        } else {
            if (!gamepad2.x) {
                cPause = false;
            }
        }


        if (hPause == false) {
            if (gamepad2.b && hStart == false && hPressed == false) {
                hStart = true;
                hPressed = true;
            } else if (!gamepad2.b && hStart == true) {
                hPressed = false;
                hookServo1.setPosition(0.6);
                hookServo2.setPosition(0.4);
            } else if (gamepad2.b && hStart == true && hPressed == false) {
                hStart = false;
                hPause = true;
                hookServo1.setPosition(0);
                hookServo2.setPosition(1);
            }
        } else {
            if (!gamepad2.b) {
                hPause = false;
            }
        }


        if (gamepad2.left_trigger > 0.5) {
            arr = new double[]{-1, 1, -1, 1};
        } else if (gamepad2.right_trigger > 0.5) {
            arr = new double[]{1, -1, 1, -1};
        }

        /*if(gamepad2.x){
            if(INPUTstarted){
                IOMotor1.setPosition()
                INPUTstarted = false;
            }
        }*/



        // SLOW MODE
        if (slowPause == false) {
            if (gamepad1.a && slowStart == false && slowPressed == false) {
                slowStart = true;
                slowPressed = true;

            } else if (!gamepad1.a && slowStart == true) {
                slowPressed = false;
                arr[0] /= 4.5;
                arr[1] /= 4.5;
                arr[2] /= 4.5;
                arr[3] /= 4.5;
            } else if (gamepad1.a && slowStart == true && slowPressed == false) {
                slowStart = false;
                slowPause = true;
            }
        } else {
            if (!gamepad1.a) {
                slowPause = false;
            }
        }

        //

        // Send calculated power to wheels
        bottomleftDrive.setPower(arr[2]);
        bottomrightDrive.setPower(arr[3]);
        topleftDrive.setPower(arr[0]);
        toprightDrive.setPower(arr[1]);
        //flipperServo1.setPosition(servoPosition1);
        //flipperServo2.setPosition(servoPosition2);


        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "tleft (%.2f), tright (%.2f), bleft (%.2f), bright (%.2f), ANGLE (%.2f), X (%.2f), Y (%.2f)",
                arr[0], arr[1], arr[2], arr[3], angle, leftStickX, leftStickY);
        //telemetry.addData("VACUUM", "VStart (%2.f), VPressed (%2.f), VPause (%.2f)",
        //vStart ? 1.5 : 0, vPressed ? 1.5 : 0, vPause ? 1.5 : 0);
        //telemetry.addData("Motor Powers", "tleft (%.2f), tright (%.2f), bleft (%.2f), bright (%.2f)",
        //topleftDrive.getPower(), toprightDrive.getPower(), bottomleftDrive.getPower(), bottomrightDrive.getPower());


        telemetry.addData("INPUT SERVOS: ", "first (%.5f), second (%.5f)",
                IOMotor1.getPosition(), IOMotor2.getPosition());
        //telemetry.addData("HOOK SERVOS ", "first (%.2f), second (%2.f)",
                //hookServo1.getPosition(), hookServo2.getPosition());

        //telemetry.addData("left Motor Position", topleftDrive.getCurrentPosition());

        //telemetry.addData("elevator motor power", elevatorMotor.getPower());

        //telemetry.addData("vacuumPower", vacuumMotor.getPower());

        telemetry.addData("EHEIGHT:", "height (%.2f)", eHeight);
        telemetry.addData("gp1", "x (%d)", (gamepad1.x) ? 1 : -1);

        /*
         * Code to run ONCE after the driver hits STOP
         */

    }

    public void stop() {
    }
}




