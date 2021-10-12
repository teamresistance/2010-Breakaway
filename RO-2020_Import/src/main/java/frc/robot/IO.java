package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import frc.util.InvertibleDigitalInput;

public class IO {

    public static Victor leftDriveMotor = new Victor(0);
    public static Victor rightDriveMotor = new Victor(1);
    
    public static Victor frameArmMotor = new Victor(2);

    public static Victor kickerMotor = new Victor(3);
    public static Victor pivotMotor = new Victor(4);

    public static InvertibleDigitalInput pivotLowerLimit = new InvertibleDigitalInput(0, false);
    public static InvertibleDigitalInput pivotUpperLimit = new InvertibleDigitalInput(1, false);

}