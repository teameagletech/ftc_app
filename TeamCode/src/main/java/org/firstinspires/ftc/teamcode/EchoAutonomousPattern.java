package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

@Autonomous(name = "EagleTechAutonomous")
public class EchoAutonomousPattern extends OpMode {

    VuforiaLocalizer vuforia;
    VuforiaTrackables relicTrackables;
    VuforiaTrackable relicTemplate;

    DcMotor driveLeftFront, driveRightFront, driveLeftBack, driveRightBack;

    RelicRecoveryVuMark patternDirection = RelicRecoveryVuMark.UNKNOWN;

    @Override
    public void init() {

        // Vuforia setup code
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AViOO0H/////AAAAGdOe07SFvkp9o8ayO1bk4XuDzjkGt9iAYhWO7gNOXYyRgcKIqt/Emv1z47NNWKJrRJahGxnoOUYzDaTvKspZCbeAuvna+XJbdvJoECZ1DDEdo/iwXL55N39Y7Jv6veJKnr4FycQROZGBU+r0Ac/EfMomkWXulsarNQuTMLiHIgikYqf+sfjVx1CO648O3WOtPEfTrfPmJB/rvo2NqG8kLmZ218EhwXgWsEGoqb3e24WJimftXKRXuH/4VzIiQLj8p+K84LurwqJjGnq8q3RRzaUCcgrLnQ1RoqA0FT7+OLIRbMkxJCfHnvqsgeKTzJCcjJX5oJPR2jYubleXblt+VKpQ43t6x5/yLYSbRFy9bYoH";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");

        driveLeftFront = hardwareMap.dcMotor.get("dlf");
        driveLeftBack = hardwareMap.dcMotor.get("dlb");
        driveRightFront = hardwareMap.dcMotor.get("drf");
        driveRightBack = hardwareMap.dcMotor.get("drb");

    }

    @Override
    public void start() {
        relicTrackables.activate();
        resetStartTime();
    }

    @Override
    public void loop() {
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

        telemetry.update();

    }

    @Override
    public void stop() {
        super.stop();
        relicTrackables.deactivate();
    }

}