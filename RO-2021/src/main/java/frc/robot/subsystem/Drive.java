package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.io.hdw_io.IO;
import frc.robot.io.joysticks.JS_IO;

public class Drive {

    private static Victor leftDrive = IO.leftDriveMotor;
    private static Victor rightDrive = IO.rightDriveMotor;

    private static double scaleFactor;

    public Drive() {
    }

    public static void init() {
        scaleFactor = 1;
    }

    public static void update() {
        leftDrive.set(JS_IO.axLeftDrive.get() * scaleFactor);
        rightDrive.set(-JS_IO.axRightDrive.get() * scaleFactor);
        sdbUpdate();
    }

    public static void sdbUpdate(){
        SmartDashboard.putNumber("Right Drive", JS_IO.axRightDrive.get());
    }
}