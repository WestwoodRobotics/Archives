package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class Robot {


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
    BNO055IMU imu;
    Orientation angles;

    public autonfunctionsR functions = new autonfunctionsR(leftBackDrive, rightBackDrive, leftFrontDrive,
            rightFrontDrive, conveyorMotor, shooterMotor,intakeMotor, shooterAngle);





    public Robot(HardwareMap hardwareMap){
        leftBackDrive  = hardwareMap.get(DcMotor.class, "leftBack");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rightBack");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFront");
        leftFrontDrive = hardwareMap.get(DcMotor.class, "leftFront");
        conveyorMotor = hardwareMap.get(DcMotor.class, "conveyorMotor");
        shooterMotor = hardwareMap.get(DcMotor.class,"shooterMotor");
        intakeMotor = hardwareMap.get(DcMotor.class,"intakeMotor");
        shooterAngle = hardwareMap.get(CRServoImpl.class,"shooterAngle");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        angles = imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);






        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        conveyorMotor.setDirection(DcMotor.Direction.FORWARD);
        shooterMotor.setDirection(DcMotor.Direction.REVERSE);
        intakeMotor.setDirection(DcMotor.Direction.REVERSE);
        shooterAngle.setDirection(CRServoImpl.Direction.FORWARD);




    }


}

