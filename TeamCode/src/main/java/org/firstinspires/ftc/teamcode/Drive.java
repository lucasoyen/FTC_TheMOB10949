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

    @Override

    public void runOpMode() {



        frontLeft = hardwareMap.dcMotor.get("frontLeft"); //port 3

        frontRight = hardwareMap.dcMotor.get("frontRight"); //port 2

        backLeft = hardwareMap.dcMotor.get("backLeft"); //port 1

        backRight =  hardwareMap.dcMotor.get("backRight");  //port 0

        armMotor = hardwareMap.dcMotor.get("armMotor");




        frontLeft.setDirection(DcMotor.Direction.FORWARD);

        frontRight.setDirection(DcMotor.Direction.FORWARD);

        backLeft.setDirection(DcMotor.Direction.FORWARD);

        backRight.setDirection(DcMotor.Direction.FORWARD);







        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);







        MecanumDrive driveController = new MecanumDrive(frontLeft, frontRight,backLeft,backRight);




        telemetry.addData("Status", "Initialized");

        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {


            //drive
            double stickLx = this.gamepad1.left_stick_x;

            double stickLy = this.gamepad1.left_stick_y;

            double stickRx = this.gamepad1.right_stick_x;

            boolean rb = this.gamepad1.right_bumper;
            boolean lb = this.gamepad1.left_bumper;

            double driveSpeed = 1;
            if (rb) driveSpeed = 0.1;
            else if (lb) driveSpeed = 0.5;


            driveController.moveInTeleop(stickLx, stickLy, stickRx, driveSpeed);
            //drive

            //arm
            double stickLy2 = this.gamepad2.left_stick_y;
            boolean a2 = this.gamepad2.a;
            boolean b2 = this.gamepad2.b;

            double armSpeed = 0.8;
            if (a2) armSpeed = 0.2;
            else if (b2) armSpeed = 0.4;

           if (stickLy2 < 0) armMotor.setPower(-armSpeed);
           else if (stickLy2 > 0) armMotor.setPower(armSpeed);
           else armMotor.setPower(0);
           //arm

            telemetry.addData("Status", "Running");

            telemetry.update();



        }

    }

}











