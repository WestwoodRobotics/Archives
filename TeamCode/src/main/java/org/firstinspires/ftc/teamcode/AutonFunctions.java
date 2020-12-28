package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class AutonFunctions {
    public DcMotor frontLeftDrive;
    public DcMotor frontRightDrive;
    public DcMotor backLeftDrive;
    public DcMotor backRightDrive;

    public ElapsedTime runtime = new ElapsedTime();

    public AutonFunctions(DcMotor fLDrive, DcMotor fRDrive, DcMotor bLDrive, DcMotor bRDrive){
        frontLeftDrive = fLDrive;
        frontRightDrive = fRDrive;
        backLeftDrive = bLDrive;
        backRightDrive = bRDrive;
    }

    public void moveForward(double seconds) {
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < seconds) {
            frontLeftDrive.setPower(1);
            frontRightDrive.setPower(1);
            backLeftDrive.setPower(1);
            backRightDrive.setPower(1);
        }
        stop();
    }

    public void moveBackwards(double seconds) {
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < seconds) {
            frontLeftDrive.setPower(-1);
            frontRightDrive.setPower(-1);
            backLeftDrive.setPower(-1);
            backRightDrive.setPower(-1);
        }
        stop();
    }

    public void stop() {
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }
}
