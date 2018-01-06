package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by ishanarya on 11/7/17.
 */

@TeleOp(name = "MotorCalibration")
public class MotorCalibration extends OpMode {

    private DcMotor motor;


    @Override
    public void init() {
        motor = hardwareMap.dcMotor.get("motor");
    }

    @Override
    public void loop() {

        if(gamepad1.dpad_up) {
            motor.setPower(0.5);
        } else if(gamepad1.dpad_down) {
            motor.setPower(-0.5);
        } else if(gamepad1.dpad_right) {
            motor.setPower(0.1);
        } else if(gamepad1.dpad_left) {
            motor.setPower(-0.1);
        } else {
            motor.setPower(gamepad1.left_stick_y);
        }

        telemetry.addData("Motor Position", motor.getCurrentPosition());
        telemetry.addData("Motor Power", motor.getPower());
    }
}
