package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MobRobotHardware {
    public MobRobotHardware(HardwareMap hw) {
        DcMotor frontLeft = hw.get(DcMotor.class, "frontLeft");
        DcMotor frontRight = hw.get(DcMotor.class, "frontRight");
        DcMotor backLeft = hw.get(DcMotor.class, "frontLeft");
        DcMotor backRight = hw.get(DcMotor.class, "frontLeft");
    }
}
