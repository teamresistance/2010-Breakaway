package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Victor;
import frc.robot.io.hdw_io.IO;
import frc.robot.io.joysticks.JS_IO;

public class Flipper{

    private static Victor frameArm = IO.frameArmMotor;

    private static double flipperSpeed;

    public Flipper() {
    }

    public static void init() {
        frameArm.set(0);
        flipperSpeed = 0.8;
    }

    public static void update() {
        if(JS_IO.roExtButton.isDown()) {
            frameArm.set(flipperSpeed);
        } else if(JS_IO.roRetButton.isDown()) {
            frameArm.set(-flipperSpeed);
        } else {
            frameArm.set(0);
        }
    }
}