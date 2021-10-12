package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Victor;
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
       if(JS_IO.axLeftY.get() >= 0.8 && IO.pivotUpperLimit.get()) {
           pivot.set(-pivotSpeed);
       } else if(JS_IO.coJoystick.getY() <= -0.8 && IO.pivotLowerLimit.get()) {
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
    }
    
}