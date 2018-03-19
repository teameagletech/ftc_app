package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "EchoAutonomousColor")
public class EchoAutonomousColor extends LinearOpMode {


    private DcMotor driveLeftFront, driveLeftBack, driveRightFront, driveRightBack;
    private DcMotor elevator, hand;
    private Servo pusher;
    private CRServo colorServo;

    private ColorSensor color;

    private int pullMotorStart1 = 1000;

    private int handMaxPosition = 163;
    private int handMinPosition = 0;

    public void initRobot() {
        driveLeftFront = hardwareMap.dcMotor.get("dlf");
        driveLeftBack = hardwareMap.dcMotor.get("dlb");
        driveRightFront = hardwareMap.dcMotor.get("drf");
        driveRightBack = hardwareMap.dcMotor.get("drb");

        elevator = hardwareMap.dcMotor.get("elevator");
        hand = hardwareMap.dcMotor.get("hand");

        pusher = hardwareMap.servo.get("pusher");
        colorServo = hardwareMap.crservo.get("colorServo");

        driveLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driveLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driveRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driveRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        color = hardwareMap.colorSensor.get("color");

        elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hand.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pusher.setPosition(1);
        colorServo.setPower(0);
    }


    @Override
    public void runOpMode() throws InterruptedException {
        initRobot();

        telemetry.addData("Message", "Don't lose too hard.");

        waitForStart();

//        closeHand();

//        elevator.setTargetPosition(pullMotorStart1);
//        elevator.setPower(1);

        // backwards for .1 seconds

        colorServo.setPower(-0.2);
        Thread.sleep(500);
        colorServo.setPower(0);
        color.enableLed(true);

        int red = color.red(), blue = color.blue();
        
        while (blue<100 || red<100 ){
            blue = color.blue();
            red = color.red();

            driveLeftFront.setPower(0.2);
            driveLeftBack.setPower(-0.2);
            driveRightFront.setPower(-0.2);
            driveRightBack.setPower(0.2);
        }
        
        driveLeftFront.setPower(0);
        driveLeftBack.setPower(0);
        driveRightFront.setPower(0);
        driveRightBack.setPower(0);
        Thread.sleep(1000);

        
        blue = color.blue();
        red = color.red();
        
        if (blue > 100) {
            driveLeftFront.setPower(0.3);
            driveLeftBack.setPower(-0.3);
            driveRightFront.setPower(0.0);
            driveRightBack.setPower(0.0);
            Thread.sleep(2000);
        }
        
        else if (red > 100) {
            driveLeftFront.setPower(0.0);
            driveLeftBack.setPower(0.0);
            driveRightFront.setPower(-0.3);
            driveRightBack.setPower(0.3);
            Thread.sleep(2000);
        }
        
        stop();
        
        
        
        
        
        /* while (red < 100 && blue < 100) {
            red = color.red();
            blue = color.blue();
            driveLeftFront.setPower(0.3);
            driveLeftBack.setPower(-0.3);
            driveRightFront.setPower(-0.3);
            driveRightBack.setPower(0.3);
        }
        driveLeftFront.setPower(0);
        driveLeftBack.setPower(0);
        driveRightFront.setPower(0);
        driveRightBack.setPower(0);
        stopRobot();

        telemetry.addData("Red", color.red());
        telemetry.addData("green", color.green());
        telemetry.addData("Blue", color.blue());
        telemetry.addData("argb", color.argb());
        telemetry.addData("alpha", color.alpha());  


        // forward for 2 seconds
//        driveLeftFront.setPower(-1);
//        driveLeftBack.setPower(1);
//        driveRightFront.setPower(1);
//        driveRightBack.setPower(-1);
//        Thread.sleep(2000);
//        driveLeftFront.setPower(0);
//        driveLeftBack.setPower(0);
//        driveRightFront.setPower(0);
//        driveRightBack.setPower(0);
//
//        pusher.setPosition(0.55);
//
//        openHand();
//
//        driveLeftFront.setPower(0.5);
//        driveLeftBack.setPower(-0.5);
//        driveRightFront.setPower(-0.5);
//        driveRightBack.setPower(0.5);
//
//        Thread.sleep(250);
//
//        driveLeftFront.setPower(0);
//        driveLeftBack.setPower(0);
//        driveRightFront.setPower(0);
//        driveRightBack.setPower(0);
//        pusher.setPosition(1);

        stop(); */
    }

    private void openHand() {
        hand.setTargetPosition(handMinPosition);
        hand.setPower(0.5);
    }

    private void closeHand() {
        hand.setTargetPosition(handMaxPosition);
        hand.setPower(-0.5);
    }

    private void stopRobot() {
        driveLeftFront.setPower(0);
        driveLeftBack.setPower(0);
        driveRightFront.setPower(0);
        driveRightBack.setPower(0);
    }

}
