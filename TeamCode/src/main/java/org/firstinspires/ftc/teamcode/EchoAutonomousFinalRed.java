package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by tarunbod on 1/25/18.
 */

@Autonomous(name = "EchoAutoFinalRed")
public class EchoAutonomousFinalRed extends OpMode {

    private DcMotor driveLeftFront, driveLeftBack, driveRightFront, driveRightBack;
    private DcMotor elevator, hand;
    private Servo pusher;

    VuforiaLocalizer vuforia;
    VuforiaTrackables relicTrackables;
    VuforiaTrackable relicTemplate;

    RelicRecoveryVuMark patternDirection = RelicRecoveryVuMark.UNKNOWN;

    private int pullMotorStart1 = 1000;

    private int handMaxPosition = 163;
    private int handMinPosition = 0;

    private void openHand() {
        hand.setTargetPosition(handMinPosition);
        hand.setPower(0.5);
    }

    private void closeHand() {
        hand.setTargetPosition(handMaxPosition);
        hand.setPower(-0.5);
    }

    private void stopRobot() {
        driveLeftFront.setPower(0);
        driveLeftBack.setPower(0);
        driveRightFront.setPower(0);
        driveRightBack.setPower(0);
    }

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

        elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hand.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pusher.setPosition(1);

        // Vuforia setup code
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AViOO0H/////AAAAGdOe07SFvkp9o8ayO1bk4XuDzjkGt9iAYhWO7gNOXYyRgcKIqt/Emv1z47NNWKJrRJahGxnoOUYzDaTvKspZCbeAuvna+XJbdvJoECZ1DDEdo/iwXL55N39Y7Jv6veJKnr4FycQROZGBU+r0Ac/EfMomkWXulsarNQuTMLiHIgikYqf+sfjVx1CO648O3WOtPEfTrfPmJB/rvo2NqG8kLmZ218EhwXgWsEGoqb3e24WJimftXKRXuH/4VzIiQLj8p+K84LurwqJjGnq8q3RRzaUCcgrLnQ1RoqA0FT7+OLIRbMkxJCfHnvqsgeKTzJCcjJX5oJPR2jYubleXblt+VKpQ43t6x5/yLYSbRFy9bYoH";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");

        closeHand();
        elevator.setTargetPosition(pullMotorStart1);
        elevator.setPower(1);
    }

    @Override
    public void loop() {
        telemetry.addData("Message", "Don't lose too hard.");

        // 1 - Color Sensor Code


        // 2 - Pattern Detection
        if (getRuntime() < 3) {
            patternDirection = RelicRecoveryVuMark.from(relicTemplate);
        } else if (getRuntime() < 3.05) {
            if (patternDirection == RelicRecoveryVuMark.RIGHT) {
                driveLeftFront.setPower(-1);
                driveLeftBack.setPower(1);
                driveRightFront.setPower(-1);
                driveRightBack.setPower(1);
            } else if (patternDirection == RelicRecoveryVuMark.LEFT) {
                driveLeftFront.setPower(1);
                driveLeftBack.setPower(-1);
                driveRightFront.setPower(1);
                driveRightBack.setPower(-1);
            }
        } else if (getRuntime() < 5.2) {
            driveLeftFront.setPower(-1);
            driveLeftBack.setPower(1);
            driveRightFront.setPower(1);
            driveRightBack.setPower(-1);
        } else {
            driveLeftFront.setPower(0);
            driveLeftBack.setPower(0);
            driveRightFront.setPower(0);
            driveRightBack.setPower(0);
        }


    }
}
