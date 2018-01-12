package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by ishanarya on 1/10/18.
 */

@TeleOp(name = "ColorTest")
public class ColorTest extends OpMode {

    private ColorSensor color;

    @Override
    public void init() {
        color = hardwareMap.colorSensor.get("color");
    }

    @Override
    public void loop() {
        int red = color.red();
        int blue = color.blue();
        telemetry.addData("Red", red);
        telemetry.addData("Blue", blue);

        telemetry.addData("Blue > Red: ", blue > red);
    }
}
