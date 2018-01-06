package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "EchoAutonomous")
public class EchoAutonomous extends LinearOpMode {


    private DcMotor rightDrive, leftDrive;
    private DcMotor pullMotor, hand;
    private Servo pusher;

    private int pullMotorStart1 = 1000;

    private int handMaxPosition = 0;
    private int handMinPosition = -150;

    @Override
    public void runOpMode() throws InterruptedException {
        initRobot();

        telemetry.addData("Message", "Don't lose too hard.");

        waitForStart();
        closeHand();

        pullMotor.setTargetPosition(pullMotorStart1);
        pullMotor.setPower(.5);


        leftDrive.setPower(-1);
        rightDrive.setPower(1);
        Thread.sleep(2000);
        leftDrive.setPower(0);
        rightDrive.setPower(0);

        pusher.setPosition(0);

        openHand();

        leftDrive.setPower(0.5);
        rightDrive.setPower(-0.5);

        Thread.sleep(250);

        leftDrive.setPower(0);
        rightDrive.setPower(0);
        pusher.setPosition(0.5);

        stop();

    }


    public void initRobot() {
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        pullMotor = hardwareMap.dcMotor.get("pullMotor");
        hand = hardwareMap.dcMotor.get("hand");
        pusher = hardwareMap.servo.get("pusher");

        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pullMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hand.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pusher.setPosition(0.5);

    }

    private void openHand() {
        hand.setTargetPosition(handMaxPosition);
        hand.setPower(0.1);
    }

    private void closeHand() {
        hand.setTargetPosition(handMinPosition);
        hand.setPower(-0.1);
    }

    private void stopRobot() {
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

}
