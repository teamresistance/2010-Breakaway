package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.io.hdw_io.IO;
import frc.robot.io.joysticks.JS_IO;

public class Flipper{

    private static Victor frameArm = IO.frameArmMotor;  //Rollover frame arm

    private static double flipperSpeed; //Speed arm is extended/retracted
    private static int state = 0;       //State machine state

    public Flipper() {
    }

    public void init() {
        state = 0;
        flipperSpeed = 0.8;
        frameArm.set(0);
        SmartDashboard.putNumber("Flip/Mtr Spd", flipperSpeed);
    }

    public void determ() {
        if(JS_IO.roExtBtn.isDown()) {
            state = 1;
        } else if(JS_IO.roRetBtn.isDown()) {
            state = 2;
        } else {
            state = 0;
        }
    }

    public void update() {
        determ();
        sdbUpdate();

        switch(state){
            case 0: //Retracted motor off
            cmdUpdate(0.0);
            break;
            case 1: //Extending, motor +
            cmdUpdate(flipperSpeed);
            break;
            case 2: //Retracting, motor -
            cmdUpdate(-flipperSpeed);
            break;
            default:    //Bad state, motor off
            cmdUpdate(0.0);
            System.out.println("Bad Flipper state - " + state);
        }
    }

    private static void cmdUpdate(double mtrCmd){
        frameArm.set(mtrCmd);
    }

    private static void sdbUpdate(){
        SmartDashboard.putNumber("Flip/Motor", frameArm.get());
        flipperSpeed = SmartDashboard.getNumber("Flip/Mtr Spd", flipperSpeed);
    }
}