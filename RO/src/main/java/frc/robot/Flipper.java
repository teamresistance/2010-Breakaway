package frc.robot;

import edu.wpi.first.wpilibj.Victor;
import frc.util.Updatable;

public class Flipper implements Updatable {

    private Victor frameArm;

    private double flipperSpeed;

    public Flipper(Victor frameArm) {
        this.frameArm = frameArm;
    }

    public void init() {
        frameArm.set(0);
        flipperSpeed = .5;
    }

    public void update() {
        if(JoystickIO.armUpButton.isDown()) {
            frameArm.set(flipperSpeed);
        } else if(JoystickIO.armDownButton.isDown()) {
            frameArm.set(-flipperSpeed);
        } else {
            frameArm.set(0);
        }
    }



}