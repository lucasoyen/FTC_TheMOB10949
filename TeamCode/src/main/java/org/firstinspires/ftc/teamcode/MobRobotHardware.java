package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MobRobotHardware {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private Telemetry telemetry;
    private ElapsedTime runtime = new ElapsedTime();

    public MobRobotHardware(HardwareMap hw, Telemetry telemetry) {
        frontLeft = hw.get(DcMotor.class, "frontLeft");
        frontRight = hw.get(DcMotor.class, "frontRight");
        backLeft = hw.get(DcMotor.class, "frontLeft");
        backRight = hw.get(DcMotor.class, "frontLeft");
        this.telemetry = telemetry;

        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private void encoderDrive(double fl, double fr, double bl, double br, double speed, double time) {
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

        while (runtime.seconds() < time && (frontLeft.isBusy() || frontRight.isBusy() || backLeft.isBusy() || backRight.isBusy())) {
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
    public void encoderRight(int dist, double speed, double time) {
        encoderDrive(dist, -dist, -dist, dist, speed, time);
    }
    public void encoderLeft(int dist, double speed, double time) {
        encoderDrive(-dist, dist, dist, -dist, speed, time);
    }
    public void encoderBack(int dist, double speed, double time) {
        encoderDrive(-dist, -dist, -dist, -dist, speed, time);
    }
    public void encoderForward(int dist, double speed, double time) {
        encoderDrive(dist, dist, dist, dist, speed, time);
    }

}
