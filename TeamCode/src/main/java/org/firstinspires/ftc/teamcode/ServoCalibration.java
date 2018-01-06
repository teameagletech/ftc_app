package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by ishanarya on 11/7/17.
 */

@TeleOp(name = "ServoCalibration")
public class ServoCalibration extends OpMode {


    private Servo servo;

    @Override
    public void init() {
        servo = hardwareMap.servo.get("servo");
    }

    @Override
    public void loop() {

        servo.setPosition(Math.abs(gamepad1.left_stick_y));
        telemetry.addData("Right Hand", servo.getPosition());

    }
}
