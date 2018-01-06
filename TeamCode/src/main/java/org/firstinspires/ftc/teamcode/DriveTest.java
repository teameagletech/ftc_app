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

        if(gamepad1.dpad_left) {
            driveLeftFront.setPower(1 * speedModifier);
            driveLeftBack.setPower(1 * speedModifier);
            driveRightFront.setPower(1 * speedModifier);
            driveRightBack.setPower(1 * speedModifier);
        } else if(gamepad1.dpad_right) {
            driveLeftFront.setPower(-1 * speedModifier);
            driveLeftBack.setPower(-1 * speedModifier);
            driveRightFront.setPower(-1 * speedModifier);
            driveRightBack.setPower(-1 * speedModifier);
        } else {
            driveLeftFront.setPower(gamepad1.left_stick_y * speedModifier);
            driveLeftBack.setPower(-gamepad1.left_stick_y * speedModifier);
            driveRightFront.setPower(-gamepad1.right_stick_y * speedModifier);
            driveRightBack.setPower(gamepad1.right_stick_y * speedModifier);
        }
    }
}
