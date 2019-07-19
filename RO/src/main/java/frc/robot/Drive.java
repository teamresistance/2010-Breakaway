package frc.robot;

import edu.wpi.first.wpilibj.Victor;
import frc.util.Updatable;
import frc.robot.JoystickIO;

public class Drive implements Updatable {

    private Victor leftDrive;
    private Victor rightDrive;

    private double scaleFactor;

    public Drive(Victor leftDrive, Victor rightDrive) {
        this.leftDrive = leftDrive;
        this.rightDrive = rightDrive;
    }

    public void init() {
        scaleFactor = 1;
    }

    public void update() {
        leftDrive.set(JoystickIO.leftJoystick.getY() * scaleFactor);
        rightDrive.set(-(JoystickIO.rightJoystick.getY()) * scaleFactor);
    }




}