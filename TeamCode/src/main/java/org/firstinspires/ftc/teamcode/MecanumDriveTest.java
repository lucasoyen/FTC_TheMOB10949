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



public class MecanumDriveTest extends LinearOpMode {



    private DcMotor frontLeft;

    private DcMotor frontRight;

    private DcMotor backLeft;

    private DcMotor backRight;

    @Override

    public void runOpMode() {



        frontLeft = hardwareMap.dcMotor.get("frontLeft"); //port 3

        frontRight = hardwareMap.dcMotor.get("frontRight"); //port 2

        backLeft = hardwareMap.dcMotor.get("backLeft"); //port 1

        backRight =  hardwareMap.dcMotor.get("backRight");  //port 0





        frontLeft.setDirection(DcMotor.Direction.FORWARD);

        frontRight.setDirection(DcMotor.Direction.REVERSE);

        backLeft.setDirection(DcMotor.Direction.FORWARD);

        backRight.setDirection(DcMotor.Direction.REVERSE);







        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);







        MecanumDrive driveController = new MecanumDrive(frontLeft, frontRight,backLeft,backRight);



        boolean toggleBack = false;

        boolean toggleBack2 = false;

        boolean x = false;

        boolean pressable = true;

        boolean pressable2 = true;

        telemetry.addData("Status", "Initialized");

        telemetry.update();

        // Wait for the game to start (driver presses PLAY)

        waitForStart();



        // run until the end of the match (driver presses STOP)

        while (opModeIsActive()) {



            double stickLx = this.gamepad1.left_stick_x;

            double stickLy = this.gamepad1.left_stick_y;

            double stickRx = this.gamepad1.right_stick_x;

            boolean a = this.gamepad1.a;





            double speed = 1;

            if(a){

                speed = .2;

            }

            driveController.moveInTeleop(stickLx, stickLy, stickRx,speed);



            if (gamepad1.a && pressable2 == true) {

                pressable2 = false;

                toggleBack2 = !toggleBack2;

            }

            if (gamepad1.a == false) {

                x = false;

                pressable2 = true;

            }







            telemetry.addData("toggleB", toggleBack);

            telemetry.addData("Status", "Running");

            telemetry.update();



        }

    }

}











