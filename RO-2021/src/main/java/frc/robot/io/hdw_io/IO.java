package frc.robot.io.hdw_io;

import edu.wpi.first.wpilibj.Victor;

public class IO {
    // navX
    public static NavX navX = new NavX();

    //Drive
    public static Victor leftDriveMotor = new Victor(0);
    public static Victor rightDriveMotor = new Victor(1);
    
    //Rollover frame
    public static Victor frameArmMotor = new Victor(2);

    //Kicker
    public static Victor kickerMotor = new Victor(3);
    public static Victor pivotMotor = new Victor(4);

    public static InvertibleDigitalInput pivotLowerLimit = new InvertibleDigitalInput(0, false);
    public static InvertibleDigitalInput pivotUpperLimit = new InvertibleDigitalInput(1, false);


    // Initialize any hardware here
    public static void init() {
    }

    public static void update() {
        
    }

}
