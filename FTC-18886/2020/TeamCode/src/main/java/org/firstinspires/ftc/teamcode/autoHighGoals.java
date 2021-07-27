package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

//Assumes robot moves 3ft every 5 seconds
//Assumed robot turns 10 degrees every 1 second
@Autonomous(name="AutoRLWP", group="Linear Opmode")
public class autoHighGoals extends LinearOpMode {


    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        RobotA robot = new RobotA(hardwareMap);
        waitForStart();
        runtime.reset();


        while (opModeIsActive()) {


            robot.functions.moveForward(24);
            robot.functions.pause(.5);
            robot.functions.moveright(7.5);
            robot.functions.pause(.5);
            robot.functions.shootOneRing();
            robot.functions.pause(.5);
            robot.functions.shootOneRing();
            robot.functions.pause(.5);
            robot.functions.shootOneRing();
            robot.functions.pause(.5);
            robot.functions.moveleft(10);
            robot.functions.pause(.5);
            robot.functions.moveForward(44);


            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}


