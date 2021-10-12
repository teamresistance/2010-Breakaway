package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.io.hdw_io.IO;
import frc.robot.io.joysticks.JS_IO;

/**
 * This class controls the "Flipper".  If the robot is upside down
 * the flipper can be used to flip it upright.
 * <p>Autonomous - No auto mode at this time.
 * <p>Teleop - A JS button is used to extend the arm.  There is no ES and is operated visually.
 * A JS button is then used to retract the arm.  Again, no ES, is operated visually.
 */
public class Flipper{

    private static Victor frameArm = IO.frameArmMotor;  //Rollover frame arm

    private static double flipperSpeed; //Speed arm is extended/retracted
    private static int state = 0;       //State machine state

    /**Constructor.  Not needed, just bc. */
    public Flipper() {
    }

    /**Initialize Flipper objects.  Called from auto/teleopInit. */
    public void init() {
        state = 0;
        flipperSpeed = 0.8;
        frameArm.set(0);
        SmartDashboard.putNumber("Flip/Mtr Spd", flipperSpeed);
    }

    /**Determine state from JS buttons.  Called from teleopPeriodoc */
    public void determ() {
        if(JS_IO.roExtBtn.isDown()) {
            state = 1;
        } else if(JS_IO.roRetBtn.isDown()) {
            state = 2;
        } else {
            state = 0;
        }
    }

    /**Update Flipper actions.  Called from teleopPeriodoc */
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

    /**Update command to Flipper, issue command. Any safeties go here.*/
    private static void cmdUpdate(double mtrCmd){
        frameArm.set(mtrCmd);
    }

    /**Update SDB display. */
    private static void sdbUpdate(){
        SmartDashboard.putNumber("Flip/Motor", frameArm.get());
        flipperSpeed = SmartDashboard.getNumber("Flip/Mtr Spd", flipperSpeed);
    }
}