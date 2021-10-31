package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.io.hdw_io.IO;
import frc.robot.io.joysticks.JS_IO;

public class Kicker{

    private static Victor pivot = IO.pivotMotor;
    private static Victor kicker = IO.kickerMotor;

    private static double kickerSpeed = 0.8;
    private static double pivotSpeed = 0.5;

    public Kicker(){
    }

    public static void init() {
    }

    public static void update() {
       if(JS_IO.lowerKicker.get() >= 0.8 && IO.pivotUpperLimit.get()) {
           pivot.set(-pivotSpeed);
       } else if(JS_IO.raiseKicker.get() >= 0.8 && IO.pivotLowerLimit.get()) {
           pivot.set(pivotSpeed);
       //} else if(JoystickIO.pivotDownButton.isDown() && JoystickIO.pivotUpButton.isDown()) {
       //    pivot.set(0);
       } else {
           pivot.set(0);
       }

       if(JS_IO.kickerHiButton.isDown()) {
           kicker.set(kickerSpeed);
       } else if(JS_IO.kickerLowButton.isDown()) {
           kicker.set(-kickerSpeed);
       } else {
           kicker.set(0);
       }

       sdbUpdate();
    }
    
    public static void sdbUpdate(){
        SmartDashboard.putNumber("Lower Kicker", JS_IO.lowerKicker.get());
        SmartDashboard.putNumber("Raise Kicker", JS_IO.raiseKicker.get());
        SmartDashboard.putBoolean("Pivot Upper Limit", IO.pivotUpperLimit.get());
        SmartDashboard.putBoolean("Pivot Lower Limit", IO.pivotLowerLimit.get());
    }
}