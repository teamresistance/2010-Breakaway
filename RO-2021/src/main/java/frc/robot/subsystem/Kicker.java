package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.io.hdw_io.IO;
import frc.robot.io.joysticks.Button;
import frc.robot.io.joysticks.JS_IO;

public class Kicker{

    //Assign hdw
    private static Victor kicker = IO.kickerMotor;

    //Assigned JS's
    private static Button kickBall = JS_IO.kickerSpin;  //3JS R1, GP 6(RB)

    //Assign variables
    private static int state = 0;
    private static double kickerSpeed = 0.8;    //Speed to spin kicker

    /**
     * Runs the kicker bar to kick balls down the field.
     * <p>
     * <p>Constructor, not needed, just because.
     */
    public Kicker(){
    }

    /**
     * Initialize any objects.
     * <p>Usually called from autonomous/telopPeriodic.
     */
    public static void init() {
        state = 0;
        sdbInit();
    }

    /**
     * This is called from robot autonomous/telopPeriodic every 20mS to evaluate
     * hardware or joystick actions to set the state.
     * <p>
     * <p>Pressing JS button toggles kicker on & off.
     * <P>
     * <P>Note: Determ was renamed to update & update is now updateSM.
     */
    public static void update() {
        if(kickBall.onButtonPressed()) state = state > 0 ? 0 : 1;

        updateSM();
        sdbUpdate();
    }

    /**
     * Update state machine.
     * <p>0 = Off.  1 = Run kicker.
     */
    private static void updateSM(){
        switch(state){
            case 0: //All Off
                cmdUpdate(0.0);
            break;
            case 1: //Run kicker
                cmdUpdate(kickerSpeed);
            break;
            default:
            System.out.println("Bad state for Kicker - " + state);
        }
    }

    /**
     * Issue commands to hardware.  Any safeties MUST be included here
     * to prevent damage to hardware.
     * @param kickerSpd
     */
    private static void cmdUpdate(double kickerSpd){
        kicker.set(Math.abs(kickerSpd));    //Forward only
    }

    /** Initialize any sdb objects that are later retrieve from sdb. */
    private static void sdbInit(){
        SmartDashboard.putNumber("Kicker/Kicker Spd", kickerSpeed);
    }

    /** Update sdb objects. */
    public static void sdbUpdate(){
        kickerSpeed = SmartDashboard.getNumber("Kicker/Kicker Spd", kickerSpeed);
    }
}