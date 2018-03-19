package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by ishanarya on 1/26/18.
 */

@TeleOp(name = "ControlValuesTest")
public class ControlValuesTest extends OpMode {
    @Override
    public void init() {

    }

    @Override
    public void loop() {


        telemetry.addData("Gamepad 1 Left Stick X", gamepad1.left_stick_x);
        telemetry.addData("Gamepad 1 Left Stick Y", gamepad1.left_stick_y);
        telemetry.addData("Gamepad 1 Right Stick X", gamepad1.right_stick_x);
        telemetry.addData("Gamepad 1 Right Stick Y", gamepad1.right_stick_y);
        telemetry.addData("Gamepad 1 Right Trigger", gamepad1.right_trigger);
        telemetry.addData("Gamepad 1 Left Trigger", gamepad1.left_trigger);
        telemetry.addData("Gamepad 1 DPad Up", gamepad1.dpad_up);
        telemetry.addData("Gamepad 1 DPad Down", gamepad1.dpad_down);
    }
}
