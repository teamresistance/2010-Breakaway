package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Victor;
import frc.robot.io.hdw_io.IO;
import frc.robot.io.joysticks.JS_IO;

public class Drive {

    private static Victor leftDrive = IO.leftDriveMotor;
    private static Victor rightDrive = IO.rightDriveMotor;

    private double scaleFactor;

    public Drive() {
    }

    public void init() {
        scaleFactor = 1;
    }

    public void update() {
        leftDrive.set(JS_IO.axLeftDrive.get() * scaleFactor);
        rightDrive.set(-JS_IO.axRightDrive.get() * scaleFactor);
    }
}