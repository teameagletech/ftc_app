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

@Autonomous(name = "VuForiaTest")
public class VuForiaTest extends LinearOpMode {

    CommonMotions commonMotions;
    VuforiaLocalizer vuforia;
    VuforiaTrackables relicTrackables;
    VuforiaTrackable relicTemplate;

    RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.UNKNOWN;


    //    BNO055IMU imu;
    private DcMotor elevator;

    public void initRobot() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
//        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = "AViOO0H/////AAAAGdOe07SFvkp9o8ayO1bk4XuDzjkGt9iAYhWO7gNOXYyRgcKIqt/Emv1z47NNWKJrRJahGxnoOUYzDaTvKspZCbeAuvna+XJbdvJoECZ1DDEdo/iwXL55N39Y7Jv6veJKnr4FycQROZGBU+r0Ac/EfMomkWXulsarNQuTMLiHIgikYqf+sfjVx1CO648O3WOtPEfTrfPmJB/rvo2NqG8kLmZ218EhwXgWsEGoqb3e24WJimftXKRXuH/4VzIiQLj8p+K84LurwqJjGnq8q3RRzaUCcgrLnQ1RoqA0FT7+OLIRbMkxJCfHnvqsgeKTzJCcjJX5oJPR2jYubleXblt+VKpQ43t6x5/yLYSbRFy9bYoH";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");

//        imu = hardwareMap.get(BNO055IMU.class, "imu");

        elevator = hardwareMap.dcMotor.get("elevator");
        commonMotions = new CommonMotions(hardwareMap, true);

        telemetry.addData("//", "Don't lose too hard.");
        relicTrackables.activate();





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
        vuMark = RelicRecoveryVuMark.from(relicTemplate);
        telemetry.addData("VuMark", vuMark.toString());
        telemetry.update();
        Thread.sleep(1000);

        commonMotions.drive(0, 0, 0.5);
        Thread.sleep(1000);

        commonMotions.stopWheels();
        Thread.sleep(1000);

        commonMotions.drive(0.5, 0, 0);
        Thread.sleep(3000);
        commonMotions.stopWheels();
        Thread.sleep(500);
        commonMotions.drive(-0.5, 0, 0);
        Thread.sleep(500);
        commonMotions.stopWheels();


        elevator.setTargetPosition(commonMotions.elevatorMinPosition);
        elevator.setPower(1);
        Thread.sleep(2000);
        commonMotions.openHand();

        Thread.sleep(2000);









//        commonMotions.drive(.5, 0, 0);
//        Thread.sleep(4000);
//        commonMotions.stopWheels();
//
//        commonMotions.pusherPush();
//
//        commonMotions.openHand();
//        Thread.sleep(2000);
//
//        commonMotions.drive(-1, 0, 0);
//
//        Thread.sleep(500);
//
//        commonMotions.stopWheels();
//        elevator.setTargetPosition(commonMotions.elevatorMinTurn);
//        commonMotions.closeHand();
//        commonMotions.pusherClose();
//        Thread.sleep(2000);
//        commonMotions.drive(.5, 0, 0);
//        Thread.sleep(1000);
//        commonMotions.stopWheels();
//        Thread.sleep(2000);
//        commonMotions.drive(-1, 0, 0);
//        Thread.sleep(200);
//        commonMotions.stopWheels();
//        Thread.sleep(2000);
//        commonMotions.drive(0, 0, -1);
//        Thread.sleep(500);
//        commonMotions.stopWheels();
//        Thread.sleep(2000);




        relicTrackables.deactivate();
        stop();
    }


}
