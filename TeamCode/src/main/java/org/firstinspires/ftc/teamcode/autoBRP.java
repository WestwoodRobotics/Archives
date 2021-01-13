package org.firstinspires.ftc.teamcode;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImpl;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="AutoBRP", group="Linear Opmode")


public class autoBRP extends LinearOpMode {

    private DcMotor leftFrontWheel = null;
    private DcMotor leftBackWheel = null;
    private  DcMotor rightFrontWheel = null;
    private DcMotor rightBackWheel = null;
    private DcMotor shooter = null;

    public void runOpMode(){


        leftBackWheel = hardwareMap.dcMotor.get("leftbackwheel");
        rightBackWheel = hardwareMap.dcMotor.get("rightbackwheel");
        leftFrontWheel = hardwareMap.dcMotor.get("leftfrontwheel");
        rightFrontWheel = hardwareMap.dcMotor.get("rightfrontwheel");
        waitForStart();




    }





}






