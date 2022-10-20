package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MobRobotHardware {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    public MobRobotHardware() {

    }

    public void init(HardwareMap hw) {
        DcMotor frontLeft = hw.get(DcMotor.class, "frontLeft");
        DcMotor frontRight = hw.get(DcMotor.class, "frontRight");
        DcMotor backLeft = hw.get(DcMotor.class, "frontLeft");
        DcMotor backRight = hw.get(DcMotor.class, "frontLeft");

        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

}
