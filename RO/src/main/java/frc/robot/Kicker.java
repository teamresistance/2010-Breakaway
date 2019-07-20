package frc.robot;

import edu.wpi.first.wpilibj.Victor;
import frc.util.Updatable;

public class Kicker implements Updatable {

    private Victor pivot;
    private Victor kicker;

    private double kickerSpeed;
    private double pivotSpeed;

    public Kicker(Victor pivot, Victor kicker)    {
        this.pivot = pivot;
        this.kicker = kicker;
    }

    public void init() {
        kickerSpeed = .5;
        pivotSpeed = .5;
    }

    public void update() {
       if(JoystickIO.coJoystick.getY() <= -.8 && IO.pivotUpperLimit.get()) {
           pivot.set(-pivotSpeed);
       } else if(JoystickIO.coJoystick.getY() >= .8 && IO.pivotLowerLimit.get()) {
           pivot.set(pivotSpeed);
       //} else if(JoystickIO.pivotDownButton.isDown() && JoystickIO.pivotUpButton.isDown()) {
       //    pivot.set(0);
       } else {
           pivot.set(0);
       }

       if(JoystickIO.kickerOutButton.isDown()) {
           kicker.set(kickerSpeed);
       } else if(JoystickIO.kickerBackButton.isDown()) {
           kicker.set(-kickerSpeed);
       } else {
           kicker.set(0);
       }
    }
    
}