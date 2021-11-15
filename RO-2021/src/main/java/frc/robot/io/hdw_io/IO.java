package frc.robot.io.hdw_io;

import edu.wpi.first.wpilibj.Victor;

public class IO {
    // navX
    public static NavX navX = new NavX();

    //Drive
    public static Victor leftDriveMotor = new Victor(1);
    public static Victor rightDriveMotor = new Victor(0);
    
    //Rollover frame
    public static Victor frameArmMotor = new Victor(2); //+ to extend, - to retract

    //Kicker
    public static Victor kickerMotor = new Victor(3);   //+ only!
    public static Victor pivotMotor = new Victor(4);    //+ is down, - is up

    public static InvertibleDigitalInput pivotLowerLimit = new InvertibleDigitalInput(0, false);    //false stop down
    public static InvertibleDigitalInput pivotUpperLimit = new InvertibleDigitalInput(1, false);    //false stop up


    // Initialize any hardware here
    public static void init() {
    }

    public static void update() {
        
    }

}
