package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by ishanarya on 11/6/17.
 */

@TeleOp(name = "EchoOp")
public class EchoOp extends OpMode {

    private DcMotor driveLeftFront, driveLeftBack, driveRightFront, driveRightBack, relicGrabber;
    private DcMotor elevator, hand;
    private Servo pusher;
    CommonMotions commonMotions;


    @Override
    public void init() {

        commonMotions = new CommonMotions(hardwareMap, false);


    }

    @Override
    public void loop() {

        // Controller 1

        if (gamepad1.y) {
            commonMotions.enablePrecisionMode();
        } else if(gamepad1.x) {
            commonMotions.disablePrecisionMode();
        }


        commonMotions.drive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

        /**
         * Flap in front of robot
         */
        if(gamepad1.left_bumper) {
            commonMotions.pusherPush();
        } else {
            commonMotions.pusherClose();
        }

        //########################################################################################################################################################################

        // Controller 2

        if(gamepad2.dpad_up) {
            commonMotions.elevatorAscend();
        } else if(gamepad2.dpad_down) {
            commonMotions.elevatorDescend();
        } else {
            commonMotions.stopElevator();
        }

        if(gamepad2.right_bumper) {
            commonMotions.closeHand();
        } else {
            commonMotions.openHand();
        }


        if(gamepad2.left_bumper) {
            commonMotions.resetElevator();
        }

        relicGrabber.setPower(gamepad2.right_trigger);

        commonMotions.relicToolExtend(gamepad2.left_trigger);

        if(gamepad2.b) {
            commonMotions.openRelicGrabber();
        } else if(gamepad2.a) {
            commonMotions.closeRelicGrabber();
        }
        if(gamepad2.y) {
            commonMotions.relicLiftUp();
        } else if(gamepad2.x) {
            commonMotions.relicLiftDown();
        }






        telemetry.addData("Gamepad 1 Left Stick X", gamepad1.left_stick_x);
        telemetry.addData("Gamepad 1 Left Stick Y", -gamepad1.left_stick_y);
        telemetry.addData("Gamepad 1 Right Stick X", gamepad1.right_stick_x);
        telemetry.addData("Gamepad 2 Right Trigger", gamepad2.right_trigger);


        commonMotions.updateTelemetry(telemetry);



    }
}
