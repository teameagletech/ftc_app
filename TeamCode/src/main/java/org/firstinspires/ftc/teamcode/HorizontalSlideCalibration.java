package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by tarunbod on 11/9/17.
 */

@TeleOp(name = "HorizontalSlideCalibration")
public class HorizontalSlideCalibration extends OpMode {

    private DcMotor horizontal;
    private double position = 0;

    @Override
    public void init() {
        horizontal = (DcMotor) hardwareMap.get("horizontal");
    }

    @Override
    public void loop() {
        if (gamepad1.dpad_up) {
            position += 0.05;
        } else if (gamepad1.dpad_down) {
            position -= 0.05;
        }
        horizontal.setPower(position);
        telemetry.addData("Grabber", horizontal.getPower());
    }
}
