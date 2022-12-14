/* Copyright (c) 2019 FIRST. All rights reserved.
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

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

/**
 * This 2022-2023 OpMode illustrates the basics of using the TensorFlow Object Detection API to
 * determine which image is being presented to the robot.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained below.
 */
@Autonomous
public class  Cucumogorange extends LinearOpMode {

    /*
     * Specify the source for the Tensor Flow Model.
     * If the TensorFlowLite object model is included in the Robot Controller App as an "asset",
     * the OpMode must to load it using loadModelFromAsset().  However, if a team generated model
     * has been downloaded to the Robot Controller's SD FLASH memory, it must to be loaded using loadModelFromFile()
     * Here we assume it's an Asset.    Also see method initTfod() below .
     */
//    private static final String TFOD_MODEL_ASSET = "PowerPlay.tflite";
//    private static final String TFOD_MODEL_FILE  = "/Environment.getExternalStorageDirectory().getPath()/FIRST/tflitemodels/CustomTeamModel.tflite";
//    private static final String TFOD_MODEL_FILE  = "/sdcard/FIRST/tflitemodels/amogus.tflite";
    private static final String TFOD_MODEL_FILE  = "/sdcard/FIRST/tflitemodels/mob.tflite";

      private static final String[] LABELS = {
              "B",
                "M",
                "O"
        };



    /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */
    private static final String VUFORIA_KEY =
            "AYBreyn/////AAABmex3dBNyV0o9peox3AFqUSIZBdXEEtbrCYzvGluBC2NjPxjvOXYAqVT9duJdvwm3+MsM2XCgMYapkf6bl9LNBEQzts3UgSm1Cm84J+yfE2UPEy2Z+ulr7sJXtgOMC7JFQmiEhrhjvV3VQJaXeraaZmaRK/4fl7Gx6aCQWySvSCPRA3AltiLiwoPFBNMJkVheZzWIpfkw4gU7j48UATtwnZWQIZtB28cvIvgUMKr1a4S6oUAr7LLBFY1yxXQYVkWvFLKr6F0Ky3WcGh4PXIvhXZuCJxm9T5JFh73aR9l2ZHlj4yTZT1YjnDMh2XPwx4GxvPbQVDDInASsVVRaGcldcPEbmZC4jmRcIVoUG2Osm0WS";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the TensorFlow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        boolean done = false;

        frontLeft.setDirection(DcMotor.Direction.FORWARD);

        frontRight.setDirection(DcMotor.Direction.FORWARD);

        backLeft.setDirection(DcMotor.Direction.FORWARD);

        backRight.setDirection(DcMotor.Direction.FORWARD);

        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        initVuforia();
        initTfod();

        /**
         * Activate TensorFlow Object Detection before we wait for the start command.
         * Do it here so that the Camera Stream window will have the TensorFlow annotations visible.
         **/
        if (tfod != null) {
            tfod.activate();

            // The TensorFlow software will scale the input images from the camera to a lower resolution.
            // This can result in lower detection accuracy at longer distances (> 55cm or 22").
            // If your target is at distance greater than 50 cm (20") you can increase the magnification value
            // to artificially zoom in to the center of image.  For best results, the "aspectRatio" argument
            // should be set to the value of the images used to create the TensorFlow Object Detection model
            // (typically 16/9).
            tfod.setZoom(1.4, 16.0/9.0);
        }

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Objects Detected", updatedRecognitions.size());
                        telemetry.update();
                        // step through the list of recognitions and display image position/size information for each one
                        // Note: "Image number" refers to the randomized image orientation/number
                        if (!done && updatedRecognitions.size() > 0) {
                            Recognition highest = null;
                            for (Recognition recognition : updatedRecognitions) {
                                double col = (recognition.getLeft() + recognition.getRight()) / 2;
                                double row = (recognition.getTop() + recognition.getBottom()) / 2;
                                double width = Math.abs(recognition.getRight() - recognition.getLeft());
                                double height = Math.abs(recognition.getTop() - recognition.getBottom());

                                telemetry.addData("", " ");
                                telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
                                telemetry.addData("- Position (Row/Col)", "%.0f / %.0f", row, col);
                                telemetry.addData("- Size (Width/Height)", "%.0f / %.0f", width, height);
                                telemetry.update();
                                if (highest == null || highest.getConfidence() < recognition.getConfidence()) highest = recognition;
                            }

                            double forwardDist = 0.2;
                            double sideDist = 0.2;
                            double time = 2;
                            double speed = 0.2;

                            if (highest.getLabel().equals("B")) {
                                encoderRight(sideDist, speed, time);
                            } else if (highest.getLabel().equals("M")) {
                                encoderLeft(sideDist, speed, time);
                            }
                            encoderForward(forwardDist, speed, time);

                            done = true;
                            tfod.deactivate();
                            telemetry.update();
                        }
                    }
                }
            }
        }
    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.56f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 300;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);

        // Use loadModelFromAsset() if the TF Model is built in as an asset by Android Studio
        // Use loadModelFromFile() if you have downloaded a custom team model to the Robot Controller's FLASH.
//        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
        tfod.loadModelFromFile(TFOD_MODEL_FILE, LABELS);
    }

    private void encoderDrive(double fl, double fr, double bl, double br, double speed, double time) {
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        int frontLeftTarget = (int)(fl * 1400);
        int frontRightTarget = (int)(fr * 1400);
        int backLeftTarget = (int)(bl * 1400);
        int backRightTarget = (int)(br * 1400);

        frontLeft.setTargetPosition(frontLeftTarget);
        frontRight.setTargetPosition(frontRightTarget);
        backLeft.setTargetPosition(backLeftTarget);
        backRight.setTargetPosition(backRightTarget);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(Math.abs(speed));
        frontRight.setPower(Math.abs(speed));
        backLeft.setPower(Math.abs(speed));
        backRight.setPower(Math.abs(speed));

        runtime.reset();

        while (opModeIsActive() && runtime.seconds() < time && (frontLeft.isBusy() || frontRight.isBusy() || backLeft.isBusy() || backRight.isBusy())) {
            telemetry.addData("Running to", "\nBackLeft: " + backLeftTarget + "\nBackRight: " + backRightTarget + "\nFrontRight: " + frontRightTarget + "\nFrontLeft: " + frontLeftTarget);
            telemetry.addData("Currently at", backLeft.getCurrentPosition() + ", " + backRight.getCurrentPosition() + ", " + frontRight.getCurrentPosition() + ", " + frontLeft.getCurrentPosition());
            telemetry.update();
        }

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    private void encoderRight(double dist, double speed, double time) {
        encoderDrive(dist, -dist, -dist, dist, speed, time);
    }
    private void encoderLeft(double dist, double speed, double time) {
        encoderDrive(-dist, dist, dist, -dist, speed, time);
    }
    private void encoderBack(double dist, double speed, double time) {
        encoderDrive(-dist, -dist, -dist, -dist, speed, time);
    }
    private void encoderForward(double dist, double speed, double time) {
        encoderDrive(dist, dist, dist, dist, speed, time);
    }
    private void move(double fl, double fr, double bl, double br, double time, double speed) {

        frontLeft.setPower(fl * Math.abs(speed));
        frontRight.setPower(fr * Math.abs(speed));
        backLeft.setPower(bl * Math.abs(speed));
        backRight.setPower(br * Math.abs(speed));

        runtime.reset();

        while (opModeIsActive() && runtime.seconds() < time) {
            telemetry.addData("Running to", "\nBackLeft: " + bl + "\nBackRight: " + br + "\nFrontRight: " + fr + "\nFrontLeft: " + fl);
            telemetry.addData("Currently at", backLeft.getCurrentPosition() + ", " + backRight.getCurrentPosition() + ", " + frontRight.getCurrentPosition() + ", " + frontLeft.getCurrentPosition());
            telemetry.update();
        }

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
    }
    private void moveRight(double dist, double speed, double time) {
        move(dist, -dist, -dist, dist, speed, time);
    }
    private void moveLeft(double dist, double speed, double time) {
        move(-dist, dist, dist, -dist, speed, time);
    }
    private void moveBack(double dist, double speed, double time) {
        move(-dist, -dist, -dist, -dist, speed, time);
    }
    private void moveForward(double dist, double speed, double time) {
        move(dist, dist, dist, dist, speed, time);
    }
}
