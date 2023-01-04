package org.firstinspires.ftc.teamcode;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.hardware.motors.RevRoboticsCoreHexMotor;

import com.qualcomm.robotcore.hardware.CRServo;

import com.qualcomm.robotcore.hardware.ColorSensor;

import com.qualcomm.robotcore.hardware.Servo;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import com.qualcomm.robotcore.hardware.Gyroscope;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import com.qualcomm.robotcore.util.ElapsedTime;



/**

 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either

 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu

 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode

 * class is instantiated on the Robot Controller and executed.

 *

 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot

 * It includes all the skeletal structure that all linear OpModes contain.

 *

 * Remove a @Disabled the on the next line or two (if present) to add this opmode to the Driver Station OpMode list,

 * or add a @Disabled annotation to prevent this OpMode from being added to the Driver Station

 */

@TeleOp

public class Drive extends LinearOpMode {



    public DcMotor frontLeft;

    public DcMotor frontRight;

    public DcMotor backLeft;

    public DcMotor backRight;

    public DcMotor armMotor;

    public DcMotor grabberMotor;

    ColorSensor color_sensor;

    @Override

    public void runOpMode() {



        frontLeft = hardwareMap.dcMotor.get("frontLeft"); //port 3

        frontRight = hardwareMap.dcMotor.get("frontRight"); //port 2

        backLeft = hardwareMap.dcMotor.get("backLeft"); //port 1

        backRight =  hardwareMap.dcMotor.get("backRight");  //port 0

        armMotor = hardwareMap.dcMotor.get("armMotor");

        grabberMotor = hardwareMap.dcMotor.get("grabberMotor");

//        color_sensor = hardwareMap.colorSensor.get("color");

        boolean xToggle = false;
        int position = 0;
        boolean holding = false;

        frontLeft.setDirection(DcMotor.Direction.FORWARD);

        frontRight.setDirection(DcMotor.Direction.FORWARD);

        backLeft.setDirection(DcMotor.Direction.FORWARD);

        backRight.setDirection(DcMotor.Direction.FORWARD);







        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        grabberMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);




        MecanumDrive driveController = new MecanumDrive(frontLeft, frontRight,backLeft,backRight);




        telemetry.addData("Status", "Initialized");

        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad2.x && xToggle) {
                xToggle = false;
            } else if (gamepad2.x && !xToggle) xToggle = true;

            //drive
            double stickLx = this.gamepad1.left_stick_x;

            double stickLy = this.gamepad1.left_stick_y;

            double stickRx = this.gamepad1.right_stick_x;

            boolean rb = this.gamepad1.right_bumper;
            boolean lb = this.gamepad1.left_bumper;

            double driveSpeed = 0.8;
            if (rb) driveSpeed = 0.3;
            else if (lb) driveSpeed = 0.15;


            driveController.moveInTeleop(stickLx, stickLy, stickRx, driveSpeed);
            //drive

            //arm

            double stickLy2 = this.gamepad2.left_stick_y;
            boolean a2 = this.gamepad2.a;
            boolean b2 = this.gamepad2.b;
            boolean y2 = this.gamepad2.y;
            boolean rb2 = this.gamepad2.right_bumper;
            boolean lb2 = this.gamepad2.left_bumper;
            boolean x2 = this.gamepad2.x;
            double rt2 = this.gamepad2.right_trigger;
            double lt2 = this.gamepad2.left_trigger;
            boolean r2 = this.gamepad2.dpad_right;

            int height1 = 200;
            int height2 = 400;
            int height3 = 600;

            armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            double armSpeed = 0.4;
            if (lb2) armSpeed = 0.2;
            else if (rb2) armSpeed = 1;

            if (stickLy2 < 0) armMotor.setPower(-armSpeed);
            else if (stickLy2 > 0) armMotor.setPower(armSpeed);
            else armMotor.setPower(-0.05);

            double armMotorPower = 0.15;

            if (a2) {
                encoderMove(-(height1 - position), armMotor, armMotorPower, 2000);
                position = height1;
            } else if (b2) {
                encoderMove(-(height2 - position), armMotor, armMotorPower, 2000);
                position = height2;
            } else if (y2) {
                encoderMove(-(height3 - position), armMotor, armMotorPower, 2000);
                position = height3;
            } else if (x2) {
                encoderMove(position, armMotor, armMotorPower, 2000);
                position = 0;
            } else if (r2) {
                position = 0;
            }


           //arm

            //grabber

            double grabberPower = 0.1;

            if (rt2 > 0) {
                grabberMotor.setPower(grabberPower);
                holding = false;
            } else if (lt2 > 0) {
                grabberMotor.setPower(-grabberPower * 5);
                holding = true;
            } else if (rt2 == 0 && !holding) {
                grabberMotor.setPower(0);
            }

            //grabber

            telemetry.addData("Motor Position", position);

            telemetry.addData("Motor Position 2 (given)", armMotor.getCurrentPosition());

            telemetry.addData("Status", "Running");

            telemetry.update();



        }
    }
    public void encoderMove(int dist, DcMotor motor, double pow, int sleep) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motor.setTargetPosition(dist);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(Math.abs(pow));

        sleep(sleep);

        motor.setPower(0);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}











