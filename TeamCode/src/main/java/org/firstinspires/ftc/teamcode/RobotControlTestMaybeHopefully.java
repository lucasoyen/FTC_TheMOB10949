package org.firstinspires.ftc.teamcode;/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

//package org.firstinspires.ftc.robotcontroller.external.samples;
//this file exists

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When a selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Robot Control Test Maybe Hopefully", group="IDK")
//@Disabled

public class RobotControlTestMaybeHopefully extends LinearOpMode {
    private Servo servo;
    private DcMotor motor;
    private DcMotor motor1;
    private DcMotor motor2;
    private DcMotor motor3;
    private DcMotor motor4;
    ColorSensor color_sensor;

    @Override
    public void runOpMode() {
        servo = hardwareMap.get(Servo.class, "servo0");
        servo.setPosition(0);
        motor = hardwareMap.get(DcMotor.class, "motor4");
        motor1 = hardwareMap.get(DcMotor.class, "frontLeft");
        motor2 = hardwareMap.get(DcMotor.class, "frontRight");
        motor3 = hardwareMap.get(DcMotor.class, "backRight");
        motor4 = hardwareMap.get(DcMotor.class, "backLeft");

        motor1.setDirection(DcMotor.Direction.FORWARD);

        motor2.setDirection(DcMotor.Direction.REVERSE);

        motor4.setDirection(DcMotor.Direction.FORWARD);

        motor3.setDirection(DcMotor.Direction.REVERSE);







        motor4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);







        MecanumDrive driveController = new MecanumDrive(motor1, motor2,motor4,motor3);



        boolean toggleBack = false;

        boolean toggleBack2 = false;

        boolean x = false;

        boolean pressable = true;

        boolean pressable2 = true;

        boolean pressable3 = true;

        boolean pressable4 = true;

        boolean pressable5 = true;

        boolean pressable6 = true;

        telemetry.addData("Status", "Initialized");


        telemetry.addData("Status", "Initialized");


        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {



            if (gamepad1.y && pressable4 == true) {
                servo.setPosition(servo.getPosition() + 2);

                pressable4 = false;
            }

            if (gamepad1.y == false){
                pressable4 = true;
            }

            if (gamepad1.x && pressable5 == true){
                servo.setPosition(servo.getPosition() - 2);

                pressable5 = false;
            }

            if (gamepad1.x == false){
                pressable5 = true;
            }

            //if (gamepad1.x) motor.setPower(1);
           /* if (gamepad1.left_stick_x == 5) motor1.setPower(0.8);
            else if (gamepad1.x){
                motor2.setPower(1);
                motor3.setPower(1);
            }

            else if (gamepad1.a && gamepad2.b) {
                motor4.setPower(1);
                motor3.setPower(1);
                motor2.setPower(1);
                motor1.setPower(1);
            } else if (gamepad1.x && gamepad2.b) {
                motor4.setPower(0);
                motor3.setPower(0);
                motor2.setPower(0);
                motor1.setPower(0);
            } else if (gamepad1.dpad_down) {
                motor4.setPower(1);
                motor3.setPower(-1);
                motor2.setPower(-0.5);
                motor1.setPower(-0.3);
            }*/

            double stickLx = this.gamepad1.left_stick_x;

            double stickLy = this.gamepad1.left_stick_y;

            double stickRx = this.gamepad1.right_stick_x;

            boolean a = this.gamepad1.dpad_down;

            boolean b = this.gamepad1.right_bumper;





            double speed = 1.0;

            if(a) {

                speed = .5;

            }
            else{
                speed = 1.0;
            }

            driveController.moveInTeleop(-stickLx, -stickLy, -stickRx,speed);



            if (gamepad1.a && pressable2 == true) {

                pressable2 = false;

                toggleBack2 = !toggleBack2;

            }

            if (gamepad1.a == false) {

                x = false;

                pressable2 = true;

            }

            if (gamepad1.b && pressable3 == true){
                pressable3 = false;

                motor.setPower(1);
            }

            if (gamepad1.b == false){
                pressable3 = true;
            }

            if (gamepad1.left_bumper){
                servo.setPosition(1);
                motor.setPower(1);
                sleep(250);
                motor.setPower(-1);
                sleep(250);
                motor.setPower(0);
                servo.setPosition(0);
            }

            //ABORT!!!!!!
            if(gamepad1.left_trigger>0){
                servo.setPosition(0);
                motor.setPower(0);
                motor1.setPower(0);
                motor2.setPower(0);
                motor3.setPower(0);
                motor4.setPower(0);
            }

            //macro1
            /*if(b && pressable6==true){

                motor1.setPower(1);
                motor2.setPower(1);
                motor3.setPower(1);
                motor4.setPower(1);
                sleep(500);
                motor1.setPower(-1);
                motor2.setPower(1);
                motor3.setPower(-1);
                motor4.setPower(1);
                sleep(500);
                motor1.setPower(0);
                motor2.setPower(0);
                motor3.setPower(0);
                motor4.setPower(0);
                pressable6 = false;
            }
            else{
                pressable6 = true;
            }*/
            if(b && pressable6){
                driveController.setTargetMode();
                driveController.setMoveForward(1);
                pressable6 = false;
            }
            if(!pressable6 && driveController.movedToTarget2()){
                driveController.setMoveRight(1);
            }
            if(!pressable6 && driveController.movedToTarget2()) {
                pressable6 = true;
            }
            //end of macro1

            telemetry.addData("Color", color_sensor.red() + ", " + color_sensor.green() + ", " + color_sensor.blue());

            telemetry.addData("toggleB", toggleBack);

            telemetry.addData("Status", "Running");

            telemetry.update();
        }
    }
}