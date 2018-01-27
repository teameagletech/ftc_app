package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "EchoAutonomous")
public class EchoAutonomous extends LinearOpMode {

    CommonMotions commonMotions;


    private DcMotor elevator;

    public void initRobot() {
        elevator = hardwareMap.dcMotor.get("elevator");
        commonMotions = new CommonMotions(hardwareMap, true);
    }


    @Override
    public void runOpMode() throws InterruptedException {
        initRobot();

        telemetry.addData("Message", "Don't lose too hard.");

        waitForStart();
        commonMotions.closeHand();

        elevator.setTargetPosition(commonMotions.elevatorStart2);
        elevator.setPower(1);
        Thread.sleep(2000);

//        driveLeftFront.setPower(backwardsMode * gamepad1.left_stick_y * speedModifier);
//        driveLeftBack.setPower(backwardsMode * -gamepad1.left_stick_y * speedModifier);
//        driveRightFront.setPower(backwardsMode * -gamepad1.right_stick_y * speedModifier);
//        driveRightBack.setPower(backwardsMode * gamepad1.right_stick_y * speedModifier);

        commonMotions.drive(1, 0, 0);
        Thread.sleep(2000);
        commonMotions.stopWheels();

        commonMotions.pusherPush();

        commonMotions.openHand();
        Thread.sleep(2000);

        commonMotions.drive(-1, 0, 0);

        Thread.sleep(500);

        commonMotions.stopWheels();
        elevator.setTargetPosition(commonMotions.elevatorMinTurn);
        commonMotions.closeHand();
        commonMotions.pusherClose();
        Thread.sleep(2000);
        commonMotions.drive(1, 0, 0);
        Thread.sleep(750);
        commonMotions.stopWheels();
        Thread.sleep(2000);
        commonMotions.drive(-1, 0, 0);
        Thread.sleep(200);
        commonMotions.stopWheels();
        Thread.sleep(2000);
        commonMotions.drive(0, 0, 1);
        Thread.sleep(500);
        commonMotions.stopWheels();
        Thread.sleep(2000);

        stop();
    }

}
