package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.lang.Thread;

public class Test {
/*    public DcMotor frontLeftDrive;
    public DcMotor frontRightDrive;
    public DcMotor backLeftDrive;
    public DcMotor backRightDrive;
**/
    public Servo shooterBlocker;
    public Servo shooterPusher;
    public DcMotor shooterMotor;
 //   public DcMotor shooterAngler;

    public final double BLOCKER_OPEN_POSITION = 0.5;
    public final double BLOCKER_CLOSED_POSITION = 0;
    public final double PUSHER_OPEN_POSITION = 0.333;
    public final double PUSHER_CLOSED_POSITION = 0;
    public final double PUSHER_RELOAD_POSITION = -0.333;

    public Telemetry telemetry;

    public ElapsedTime runtime = new ElapsedTime();

/*    public Test (DcMotor fLDrive, DcMotor fRDrive, DcMotor bLDrive, DcMotor bRDrive) {
        frontLeftDrive = fLDrive;
        frontRightDrive = fRDrive;
        backLeftDrive = bLDrive;
        backRightDrive = bRDrive;
    }
**/

    public Test (Servo shootB, Servo shootP, DcMotor shootM, Telemetry t){
        shooterBlocker = shootB;
        shooterPusher = shootP;
        shooterMotor = shootM;
        telemetry = t;
    }

    //waits for a given time
    public void pause (long milliseconds) {
        try {
            telemetry.addData("Pause: ", "started");
            telemetry.update();
            Thread.sleep(milliseconds);
            telemetry.addData("Pause:", "finished");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    //assumes robot moves at 5in per sec
/*    public void moveForward () {
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < 3) {
            frontLeftDrive.setPower(0.5);
            frontRightDrive.setPower(0.5);
            backLeftDrive.setPower(0.5);
            backRightDrive.setPower(0.5);
        }
        stop();
    }

    public void moveBackward () {
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < 3) {
            frontLeftDrive.setPower(-0.5);
            frontRightDrive.setPower(-0.5);
            backLeftDrive.setPower(-0.5);
            backRightDrive.setPower(-0.5);
        }
        stop();
    }

    public void moveLeft () {
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < 3) {
            frontLeftDrive.setPower(-0.5);
            frontRightDrive.setPower(0.5);
            backLeftDrive.setPower(0.5);
            backRightDrive.setPower(-0.5);
        }
        stop();
    }

    public void moveRight () {
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < 3) {
            frontLeftDrive.setPower(0.5);
            frontRightDrive.setPower(-0.5);
            backLeftDrive.setPower(-0.5);
            backRightDrive.setPower(0.5);
        }
        stop();
    }
    public void moveDiagFL () {
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < 3) {
            frontRightDrive.setPower(0.5);
            backLeftDrive.setPower(0.5);
        }
        stop();
    }
    public void moveDiagFR () {
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < 3) {
            frontLeftDrive.setPower(0.5);
            backRightDrive.setPower(0.5);
        }
        stop();
    }
    public void moveDiagBL (double inch, double power) {
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < 3) {
            frontLeftDrive.setPower(-0.5);
            backRightDrive.setPower(-0.5);
        }
        stop();
    }
    public void moveDiagBR () {
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < 3) {
            frontRightDrive.setPower(-0.5);
            backLeftDrive.setPower(-0.5);
        }
        stop();
    }

    //assumes robot spins at 90 degrees per second
    public void turnFrontCenterClock (double degrees) {
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < (degrees/90)) {
            backLeftDrive.setPower(1);
            backRightDrive.setPower(-1);
        }
    }

    public void turnFrontCenterCounterClock (double degrees) {
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < (degrees/90)) {
            backLeftDrive.setPower(-1);
            backRightDrive.setPower(1);
        }
    }
**/
    public void shoot() {
        //start spinning the shooter motor
        shooterMotor.setPower(1);
        //turn blocker servo 90 degrees
        shooterBlocker.setPosition(BLOCKER_OPEN_POSITION);
        //turn the shooter push servo 60 degrees and then back 60 degrees
        shooterPusher.setPosition(PUSHER_OPEN_POSITION);

        this.pause(500);
        shooterPusher.setPosition(PUSHER_CLOSED_POSITION);
        //turn blocker servo 90 degrees counter clockwise
        shooterBlocker.setPosition(BLOCKER_CLOSED_POSITION);
        //stop spinning the shooter motor
        shooterMotor.setPower(0);
    }

    public void shoot3times() {
        //start spinning the shooter motor
        shooterMotor.setPower(1);
        //turn blocker servo 90 degrees
        shooterBlocker.setPosition(BLOCKER_OPEN_POSITION);
        //turn the shooter push servo 60 degrees and then back 60 degrees
        for (int i = 0; i < 3 ; i++) {
            shooterPusher.setPosition(PUSHER_OPEN_POSITION);
            //delay
//            pause(1);

            shooterPusher.setPosition(PUSHER_RELOAD_POSITION);
        }
        //turn blocker servo 90 degrees counter clockwise
        shooterBlocker.setPosition(BLOCKER_CLOSED_POSITION);
        //stop spinning the shooter motor
        shooterPusher.setPosition(PUSHER_CLOSED_POSITION);
        shooterMotor.setPower(0);
    }

    /*
    public void testFR () {
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < 5) {
            frontRightDrive.setPower(1);
        }
        frontRightDrive.setPower(0);
    }

    public void testFL () {
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < 5) {
            frontLeftDrive.setPower(1);
        }
        frontLeftDrive.setPower(0);
    }

    public void testBR () {
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < 5) {
            backRightDrive.setPower(1);
        }
        backRightDrive.setPower(0);
    }

    public void testBL () {
        double startTime = runtime.seconds();
        while (runtime.seconds() - startTime < 5) {
            backLeftDrive.setPower(1);
        }
        backLeftDrive.setPower(0);
    }
    public void stop() {
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }

 */
}
