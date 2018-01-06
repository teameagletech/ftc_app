package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by tarunbod on 11/9/17.
 */

@TeleOp(name = "GrabberCalibration")
public class GrabberCalibration extends OpMode {

    private Servo grabber;
    private double position = 0;

    @Override
    public void init() {
        grabber = hardwareMap.servo.get("grabber");

    }

    @Override
    public void loop() {
        if (gamepad1.dpad_up) {
            position += 0.001;
        } else if (gamepad1.dpad_down) {
            position -= 0.001;
        }
        grabber.setPosition(position);
        telemetry.addData("Grabber", grabber.getPosition());
    }

}
