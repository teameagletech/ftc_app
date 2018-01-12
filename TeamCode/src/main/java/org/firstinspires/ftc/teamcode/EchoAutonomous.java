package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "EchoAutonomous")
public class EchoAutonomous extends LinearOpMode {


    private DcMotor driveLeftFront, driveLeftBack, driveRightFront, driveRightBack;
    private DcMotor elevator, hand;
    private Servo pusher;

    private ColorSensor color;

    private int pullMotorStart1 = 1000;

    private int handMaxPosition = 0;
    private int handMinPosition = -150;

    public void initRobot() {
        driveLeftFront = hardwareMap.dcMotor.get("dlf");
        driveLeftBack = hardwareMap.dcMotor.get("dlb");
        driveRightFront = hardwareMap.dcMotor.get("drf");
        driveRightBack = hardwareMap.dcMotor.get("drb");

        elevator = hardwareMap.dcMotor.get("elevator");
        hand = hardwareMap.dcMotor.get("hand");
        pusher = hardwareMap.servo.get("pusher");

        driveLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driveLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driveRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driveRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        color = hardwareMap.colorSensor.get("color");

        elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hand.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }


    @Override
    public void runOpMode() throws InterruptedException {
        initRobot();

        telemetry.addData("Message", "Don't lose too hard.");

        waitForStart();
        closeHand();

        elevator.setTargetPosition(pullMotorStart1);
        elevator.setPower(.5);

//        driveLeftFront.setPower(backwardsMode * gamepad1.left_stick_y * speedModifier);
//        driveLeftBack.setPower(backwardsMode * -gamepad1.left_stick_y * speedModifier);
//        driveRightFront.setPower(backwardsMode * -gamepad1.right_stick_y * speedModifier);
//        driveRightBack.setPower(backwardsMode * gamepad1.right_stick_y * speedModifier);

        driveLeftFront.setPower(1);
        driveLeftBack.setPower(-1);
        driveRightFront.setPower(-1);
        driveRightBack.setPower(1);
        Thread.sleep(2000);
        driveLeftFront.setPower(0);
        driveLeftBack.setPower(0);
        driveRightFront.setPower(0);
        driveRightBack.setPower(0);

        pusher.setPosition(0);

        openHand();

        driveLeftFront.setPower(0.5);
        driveLeftBack.setPower(-0.5);
        driveRightFront.setPower(0.5);
        driveRightBack.setPower(-0.5);

        Thread.sleep(250);

        driveLeftFront.setPower(0);
        driveLeftBack.setPower(0);
        driveRightFront.setPower(0);
        driveRightBack.setPower(0);
        pusher.setPosition(0.5);

        stop();
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
        driveLeftFront.setPower(0);
        driveLeftBack.setPower(0);
        driveRightFront.setPower(0);
        driveRightBack.setPower(0);
    }

}
