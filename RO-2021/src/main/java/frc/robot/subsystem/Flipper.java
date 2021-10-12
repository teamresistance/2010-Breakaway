package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Victor;
import frc.robot.io.hdw_io.IO;
import frc.robot.io.joysticks.JS_IO;

public class Flipper{

    private Victor frameArm = IO.frameArmMotor;

    private double flipperSpeed;

    public Flipper() {
    }

    public void init() {
        frameArm.set(0);
        flipperSpeed = 0.8;
    }

    public void update() {
        if(JS_IO.roExtButton.isDown()) {
            frameArm.set(flipperSpeed);
        } else if(JS_IO.roRetButton.isDown()) {
            frameArm.set(-flipperSpeed);
        } else {
            frameArm.set(0);
        }
    }
}