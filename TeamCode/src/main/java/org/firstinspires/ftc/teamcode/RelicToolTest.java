package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by ishanarya on 1/11/18.
 */

@TeleOp(name = "RelicToolTest")
public class RelicToolTest extends OpMode {

    private DcMotor relicMotor;

    private Servo lift;
    private CRServo grab;


    @Override
    public void init() {
        relicMotor = hardwareMap.dcMotor.get("relicMotor");
        grab = hardwareMap.crservo.get("grab");
        lift = hardwareMap.servo.get("lift");
    }

    @Override
    public void loop() {
        if(gamepad1.dpad_up) {
            relicMotor.setPower(0.5);
        } else if(gamepad1.dpad_down) {
            relicMotor.setPower(-0.5);
        } else {
            relicMotor.setPower(gamepad1.left_stick_y);
        }

        if(gamepad1.y) {
            grab.setPower(1);
        } else if(gamepad1.b) {
            grab.setPower(-1);
        } else {
            grab.setPower(0);
        }

        lift.setPosition(gamepad1.right_stick_y);


    }
}
