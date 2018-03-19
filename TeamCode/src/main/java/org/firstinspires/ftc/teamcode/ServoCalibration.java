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
    private double servoPosition = 0.0;

    @Override
    public void init() {
        servo = hardwareMap.servo.get("servo");
    }

    @Override
    public void loop() {
        if(gamepad1.dpad_up && servoPosition < 1) {
            servoPosition += 0.0003;
        } else if(gamepad1.dpad_down && servoPosition > 0) {
            servoPosition -= 0.0003;
        }

        if(Math.abs(gamepad1.left_stick_y) != 0) {
            servoPosition = Math.abs(gamepad1.left_stick_y);
        }
        servo.setPosition(servoPosition);
        telemetry.addData("Servo Position - Variable", servoPosition);
        telemetry.addData("Servo Position - Method", servo.getPosition());

    }
}
