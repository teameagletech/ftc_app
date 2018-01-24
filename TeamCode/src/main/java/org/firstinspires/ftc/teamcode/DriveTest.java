package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by ishanarya on 1/5/18.
 */

@TeleOp(name = "DriveTest")
public class DriveTest extends OpMode {

    private DcMotor driveLeftFront, driveLeftBack, driveRightFront, driveRightBack;

    private double speedModifier = 1;
    @Override
    public void init() {

        driveLeftFront = hardwareMap.dcMotor.get("dlf");
        driveLeftBack = hardwareMap.dcMotor.get("dlb");
        driveRightFront = hardwareMap.dcMotor.get("drf");
        driveRightBack = hardwareMap.dcMotor.get("drb");
    }

    @Override
    public void loop() {

        if(gamepad1.y) {
            speedModifier = 0.5;
        } else if(gamepad1.x) {
            speedModifier = 1;
        }

        double r = Math.hypot(-gamepad1.left_stick_y, gamepad1.left_stick_x);
        double robotAngle = Math.atan2(gamepad1.left_stick_x, -gamepad1.left_stick_y) + Math.PI/4;
        double rightX = gamepad1.right_stick_x;

        final double a = r * Math.sin(robotAngle) + rightX;
        final double b = r * Math.cos(robotAngle) - rightX;
        final double c = r * Math.cos(robotAngle) + rightX;
        final double d = r * Math.sin(robotAngle) - rightX;

        driveLeftFront.setPower(-a);
        driveRightFront.setPower(b);
        driveLeftBack.setPower(-c);
        driveRightBack.setPower(d);

//        //strafe right on positive
//        driveLeftFront.setPower(-gamepad1.left_stick_x * speedModifier); //+
//        driveLeftBack.setPower(gamepad1.left_stick_x * speedModifier); //-
//        driveRightBack.setPower(gamepad1.left_stick_x * speedModifier); //+
//        driveRightFront.setPower(-gamepad1.left_stick_x * speedModifier); //-

        telemetry.addData("Gamepad 1 Left Stick X", gamepad1.left_stick_x);
        telemetry.addData("Gamepad 1 Left Stick Y", -gamepad1.left_stick_y);
        telemetry.addData("Gamepad 1 Right Stick X", gamepad1.right_stick_x);

        telemetry.addData("Left Front", -a);
        telemetry.addData("Right Front", b);
        telemetry.addData("Left Back", -c);
        telemetry.addData("Right Back", d);
    }
}
