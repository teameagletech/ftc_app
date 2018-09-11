package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by ishanarya on 1/26/18.
 */

public class CommonMotions {


    private DcMotor driveLeftFront, driveLeftBack, driveRightFront, driveRightBack;
    private DcMotor elevator, hand, relicTool;
    private Servo relicGrabber, relicLift;
    private Servo pusher;

    final public int elevatorMaxPosition = 10000;
    final public int elevatorEnd1 = 9000;
    final public int elevatorEnd2 = 8000;
    final public int elevatorStart2 = 2000;
    final public int elevatorStart1 = 1000;
    final public int elevatorMinPosition = 0;

    final public double handPower = 0.49;
    final public int handMaxPosition = 175;
    final public int handMinPosition = 0;

    final public double pusherClosePosition = 0.7;
    final public double pusherOpenPosition = 0;

    private double relicToolPower = 0.5;
    private double relicGrabberClosedPosition = 0.56;
    private double relicGrabberOpenPosition = 1;
    private double relicGrabberInitPosition = 0.47;
    private double relicLiftInitPosition = 0.116;
    private double relicLiftUpPosition = 1;
    private double relicLiftDownPosition = 0.169;

    private double speedModifier = 1;


    public CommonMotions(HardwareMap hardwareMap, boolean inAutonomous) {
        driveLeftFront = hardwareMap.dcMotor.get("dlf");
        driveLeftBack = hardwareMap.dcMotor.get("dlb");
        driveRightFront = hardwareMap.dcMotor.get("drf");
        driveRightBack = hardwareMap.dcMotor.get("drb");

        elevator = hardwareMap.dcMotor.get("elevator");
        hand = hardwareMap.dcMotor.get("hand");

        relicTool = hardwareMap.dcMotor.get("relicTool");

        relicGrabber = hardwareMap.servo.get("rg");
        relicLift = hardwareMap.servo.get("rl");

        pusher = hardwareMap.servo.get("pusher");

        driveLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driveLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driveRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driveRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        if(inAutonomous) {
            elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        } else {
            elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        hand.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        setRelicLiftInitPosition();
        setRelicGrabberInitPosition();
        pusherClose();
    }

    public void drive(double leftY, double leftX, double rightX) {
        double r = Math.hypot(leftY, leftX);
        double robotAngle = Math.atan2(leftX, leftY) + Math.PI/4;

        final double a = r * Math.sin(robotAngle) + rightX;
        final double b = r * Math.cos(robotAngle) - rightX;
        final double c = r * Math.cos(robotAngle) + rightX;
        final double d = r * Math.sin(robotAngle) - rightX;

        driveLeftFront.setPower(-a * speedModifier);
        driveRightFront.setPower(b * speedModifier);
        driveLeftBack.setPower(-c * speedModifier);
        driveRightBack.setPower(d * speedModifier);
    }
    public void stopWheels() {
        drive(0, 0, 0);
    }


    public void enablePrecisionMode() {
        speedModifier = 0.4;
    }
    public void disablePrecisionMode() {
        speedModifier = 1;
    }


    public void elevatorAscend() {
        double elevatorPosition = elevator.getCurrentPosition();
        if(elevatorPosition < elevatorStart1) {
            elevator.setPower(0.25);
        } else if(elevatorPosition < elevatorStart2) {
            elevator.setPower(0.5);
        } else if(elevatorPosition < elevatorEnd2) {
            elevator.setPower(1);
        } else if(elevatorPosition < elevatorEnd1) {
            elevator.setPower(0.5);
        } else if(elevatorPosition < elevatorMaxPosition) {
            elevator.setPower(0.25);
        } else {
            stopElevator();
        }
    }
    public void elevatorDescend() {
        double elevatorPosition = elevator.getCurrentPosition();
        if(elevatorPosition > elevatorEnd1) {
            elevator.setPower(-0.25);
        } else if(elevatorPosition > elevatorEnd2) {
            elevator.setPower(-0.5);
        } else if(elevatorPosition > elevatorStart2) {
            elevator.setPower(-1);
        } else if(elevatorPosition > elevatorStart1) {
            elevator.setPower(-0.5);
        } else if(elevatorPosition > elevatorMinPosition) {
            elevator.setPower(-0.25);
        } else {
            stopElevator();
        }
    }
    public void stopElevator() {
        elevator.setPower(0);
    }
    public void resetElevator() {
        elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void openHand() {
        hand.setTargetPosition(handMinPosition);
        hand.setPower(handPower);
    }

    public void closeHand() {
        hand.setTargetPosition(handMaxPosition);
        hand.setPower(-handPower);
    }

    public void pusherPush() {
        pusher.setPosition(pusherOpenPosition);
    }
    public void pusherClose() {
        pusher.setPosition(pusherClosePosition);
    }

    public void relicToolExtend(double power) {
        relicTool.setPower(power * relicToolPower);
    }
    public void setRelicGrabberInitPosition() {
        relicGrabber.setPosition(relicGrabberInitPosition);
    }
    public void openRelicGrabber() {
        relicGrabber.setPosition(relicGrabberOpenPosition);
    }
    public void closeRelicGrabber() {
        relicGrabber.setPosition(relicGrabberClosedPosition);
    }
    public void relicLiftUp() {
        relicLift.setPosition(relicLiftUpPosition);
    }
    public void setRelicLiftInitPosition() {
        relicLift.setPosition(relicLiftInitPosition);
    }
    public void relicLiftDown() {
        relicLift.setPosition(relicLiftDownPosition);
    }

    public void updateTelemetry(Telemetry telemetry) {
        telemetry.addData("Elevator Position", elevator.getCurrentPosition());
        telemetry.addData("Hand Position", hand.getCurrentPosition());
        telemetry.addData("Relic Tool Power", relicTool.getPower());
        telemetry.addData("Relic Grabber Position", relicGrabber.getPosition());
        telemetry.addData("Relic Lift Position", relicLift.getPosition());
    }

//    public void relicGrabberPower() {
//        relicGrabber.setPower(gamepad2.right_trigger);
//    }





}
