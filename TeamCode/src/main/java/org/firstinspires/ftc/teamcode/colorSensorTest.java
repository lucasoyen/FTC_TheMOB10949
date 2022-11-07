package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.hardware.motors.RevRoboticsCoreHexMotor;

import com.qualcomm.robotcore.hardware.CRServo;

import com.qualcomm.robotcore.hardware.ColorSensor;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import com.qualcomm.robotcore.hardware.Gyroscope;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import com.qualcomm.robotcore.util.ElapsedTime;

public class colorSensorTest extends LinearOpMode{
    ColorSensor color_sensor;

    @Override
    public void runOpMode() throws InterruptedException {
        color_sensor = hardwareMap.colorSensor.get("color");
        while(opModeIsActive()) {
            telemetry.addData("Color", color_sensor.red() + ", " + color_sensor.green() + ", " + color_sensor.blue());
            telemetry.update();
        }
    }
}
