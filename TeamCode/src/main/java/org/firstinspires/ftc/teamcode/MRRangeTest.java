package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@TeleOp(name="MRRangeTest", group="IDK")

public class MRRangeTest extends LinearOpMode {

    ModernRoboticsI2cRangeSensor rangeSensor;

    @Override public void runOpMode() {

        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range");
        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Raw ultrasonic:", rangeSensor.rawUltrasonic());
            telemetry.addData("\nRaw optical", rangeSensor.rawOptical());
            telemetry.addData("\ncm optical", "%.2f cm", rangeSensor.cmOptical());
            telemetry.addData("\ncm", "%.2f cm", rangeSensor.getDistance(DistanceUnit.CM));
            telemetry.update();
        }
    }
}



