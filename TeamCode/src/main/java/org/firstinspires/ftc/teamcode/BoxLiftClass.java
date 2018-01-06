package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by ishanarya on 1/6/18.
 */

@TeleOp(name = "BoxLift")
public class BoxLiftClass extends OpMode {

    private DcMotor elevator, hand;

    private int elevatorMaxTurn = 10600;
    private int elevatorEnd1 = 9600;
    private int elevatorEnd2 = 8600;
    private int elevatorStart2 = 2000;
    private int elevatorStart1 = 1000;
    private int elevatorMinTurn = 0;

    private int handMaxPosition = 154;
    private int handMinPosition = 0;


    @Override
    public void init() {
        elevator = hardwareMap.dcMotor.get("elevator");
        hand = hardwareMap.dcMotor.get("hand");

        elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hand.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void loop() {


        int elevatorPosition = elevator.getCurrentPosition();
        if(gamepad1.dpad_up) {
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
        } else if(gamepad1.dpad_down) {
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

        if(gamepad1.right_bumper) {
            closeHand();
        } else {
            openHand();
        }
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
