package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.Locale;

@Autonomous(name = "EchoAutonomousRedStraight")
public class EchoAutonomousRedStraight extends LinearOpMode {

    CommonMotions commonMotions;


    //    BNO055IMU imu;
    private DcMotor elevator;

    public void initRobot() {

        elevator = hardwareMap.dcMotor.get("elevator");
        commonMotions = new CommonMotions(hardwareMap, true);

        telemetry.addData("//", "Don't lose too hard.");





        waitForStart(); //Final line
    }


    @Override
    public void runOpMode() throws InterruptedException {
        initRobot();


        commonMotions.closeHand();
        Thread.sleep(500);

        elevator.setTargetPosition(commonMotions.elevatorStart2);
        elevator.setPower(1);
        Thread.sleep(2000);









        commonMotions.drive(.5, 0, 0);
        Thread.sleep(4000);
        commonMotions.stopWheels();

        commonMotions.pusherPush();

        commonMotions.openHand();
        Thread.sleep(2000);

        commonMotions.drive(-0.5, 0, 0);

        Thread.sleep(700);

        commonMotions.stopWheels();
        elevator.setTargetPosition(commonMotions.elevatorMinPosition);
        commonMotions.closeHand();
        commonMotions.pusherClose();
        Thread.sleep(2000);
        commonMotions.drive(.5, 0, 0);
        Thread.sleep(1000);
        commonMotions.stopWheels();
        Thread.sleep(2000);
        commonMotions.drive(-0.5, 0, 0);
        Thread.sleep(350);
        commonMotions.stopWheels();
        Thread.sleep(2000);
        commonMotions.drive(0, 0, -0.5);
        Thread.sleep(750);
        commonMotions.stopWheels();
        commonMotions.openHand();
        Thread.sleep(2000);
        stop();
    }


}
