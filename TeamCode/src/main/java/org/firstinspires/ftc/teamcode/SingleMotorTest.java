package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.MobRobotHardware;


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

@TeleOp(name="Single Motor Test", group="Linear Opmode")
public class SingleMotorTest extends LinearOpMode {

    DcMotor topRight;
    DcMotor backLeft;

    @Override
    public void runOpMode() {

        topRight = hardwareMap.get(DcMotor.class, "motor7");
        backLeft = hardwareMap.get(DcMotor.class, "motor6");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                topRight.setPower(1);
                backLeft.setPower(1);
            }
            else if (gamepad1.b) {
                topRight.setPower(-1);
                backLeft.setPower(-1);
            }
            else {
                topRight.setPower(0);
                backLeft.setPower(0);
            }

            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
