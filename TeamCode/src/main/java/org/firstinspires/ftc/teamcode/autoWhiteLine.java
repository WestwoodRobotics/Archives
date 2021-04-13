package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

//Assumes robot moves 3ft every 5 seconds
//Assumed robot turns 10 degrees every 1 second
@Autonomous(name="AutoRLWP", group="Linear Opmode")
public class autoWhiteLine extends LinearOpMode {


    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        RobotA robot1 = new RobotA(hardwareMap,telemetry);
        waitForStart();
        runtime.reset();


        while (opModeIsActive()) {

            robot1.functions.moveForward(72);


//            telemetry.addData("Status", "Run Time: " + runtime.toString());
//            telemetry.update();
        }
    }
}


