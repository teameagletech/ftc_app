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

    private DcMotor driveLeftFront, driveLeftBack, driveRightFront, driveRightBack;
    private DcMotor elevator, hand;
    private Servo pusher;


    private int elevatorMaxTurn = 10000;
    private int elevatorEnd1 = 9000;
    private int elevatorEnd2 = 8000;
    private int elevatorStart2 = 2000;
    private int elevatorStart1 = 1000;
    private int elevatorMinTurn = 0;

    private int handMaxPosition = 175;
    private int handMinPosition = 0;

    private double speedModifier = 1;

    private int backwardsMode = 1;

    @Override
    public void init() {

        driveLeftFront = hardwareMap.dcMotor.get("dlf");
        driveLeftBack = hardwareMap.dcMotor.get("dlb");
        driveRightFront = hardwareMap.dcMotor.get("drf");
        driveRightBack = hardwareMap.dcMotor.get("drb");

        elevator = hardwareMap.dcMotor.get("elevator");
        hand = hardwareMap.dcMotor.get("hand");
        pusher = hardwareMap.servo.get("pusher");

        driveLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driveLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driveRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driveRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hand.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        pusher.setPosition(1);

    }

    @Override
    public void loop() {

        // Controller 1

        if (gamepad1.y) {
            speedModifier = 0.3;
        } else if(gamepad1.x) {
            speedModifier = 1;
        }

        if (gamepad1.b) {
            backwardsMode = -1;
        } else if (gamepad1.a) {
            backwardsMode = 1;
        }

        if(gamepad1.dpad_left) {
            driveLeftFront.setPower(backwardsMode * 1 * speedModifier);
            driveLeftBack.setPower(backwardsMode * 1 * speedModifier);
            driveRightFront.setPower(backwardsMode * 1 * speedModifier);
            driveRightBack.setPower(backwardsMode * 1 * speedModifier);
        } else if(gamepad1.dpad_right) {
            driveLeftFront.setPower(backwardsMode * -1 * speedModifier);
            driveLeftBack.setPower(backwardsMode * -1 * speedModifier);
            driveRightFront.setPower(backwardsMode * -1 * speedModifier);
            driveRightBack.setPower(backwardsMode * -1 * speedModifier);
        } else {
            driveLeftFront.setPower(backwardsMode * gamepad1.left_stick_y * speedModifier);
            driveLeftBack.setPower(backwardsMode * -gamepad1.left_stick_y * speedModifier);
            driveRightFront.setPower(backwardsMode * -gamepad1.right_stick_y * speedModifier);
            driveRightBack.setPower(backwardsMode * gamepad1.right_stick_y * speedModifier);
        }

        /**
         * Flap in front of robot
         */
        if(gamepad1.left_bumper) {
            pusher.setPosition(0.55);
        } else {
            pusher.setPosition(1);
        }

        //########################################################################################################################################################################

        // Controller 2

        int elevatorPosition = elevator.getCurrentPosition();
        if(gamepad2.dpad_up) {
            if(elevatorPosition < elevatorStart1) {
                elevator.setPower(0.25);
            } else if(elevatorPosition < elevatorStart2) {
                elevator.setPower(0.5);
            } else if(elevatorPosition < elevatorEnd2) {
                elevator.setPower(1);
            } else if(elevatorPosition < elevatorEnd1) {
                elevator.setPower(0.5);
            } else if(elevatorPosition < elevatorMaxTurn) {
                elevator.setPower(0.25);
            } else {
                elevator.setPower(0);
            }
        } else if(gamepad2.dpad_down) {
            if(elevatorPosition > elevatorEnd1) {
                elevator.setPower(-0.25);
            } else if(elevatorPosition > elevatorEnd2) {
                elevator.setPower(-0.5);
            } else if(elevatorPosition > elevatorStart2) {
                elevator.setPower(-1);
            } else if(elevatorPosition > elevatorStart1) {
                elevator.setPower(-0.5);
            } else if(elevatorPosition > elevatorMinTurn) {
                elevator.setPower(-0.25);
            } else {
                elevator.setPower(0);
            }
        } else {
            elevator.setPower(0);
        }

        if(gamepad2.right_bumper) {
            closeHand();
        } else {
            openHand();
        }


        if(gamepad2.y) {
            elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        telemetry.addData("Elevator Position", elevator.getCurrentPosition());
        telemetry.addData("Hand Position", hand.getCurrentPosition());
        telemetry.addData("Zero Position", elevatorMinTurn);
    }

    private void openHand() {
        hand.setTargetPosition(handMinPosition);
        hand.setPower(0.5);
    }

    private void closeHand() {
        hand.setTargetPosition(handMaxPosition);
        hand.setPower(-0.5);
    }
}
