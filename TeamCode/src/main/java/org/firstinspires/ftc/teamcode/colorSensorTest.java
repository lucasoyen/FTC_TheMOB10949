package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="colrTst ?!123", group="IDK")
public class colorSensorTest extends LinearOpMode{
    ColorSensor color_sensor;

    @Override
    public void runOpMode() throws InterruptedException {
        color_sensor = hardwareMap.colorSensor.get("color");
        waitForStart();
        while(opModeIsActive()) {
            telemetry.addData("Color: ", color_sensor.red() + ", " + color_sensor.green() + ", " + color_sensor.blue());
            telemetry.addData("Collision: ", (color_sensor.red()>250 && color_sensor.green()>250 && color_sensor.blue()>250));
            telemetry.update();
        }
    }
}
