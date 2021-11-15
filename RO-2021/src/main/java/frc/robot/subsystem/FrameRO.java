package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.io.hdw_io.IO;
import frc.robot.io.joysticks.Button;
import frc.robot.io.joysticks.JS_IO;

public class FrameRO{

    //Assign hdw
    private static Victor frameExt = IO.frameArmMotor;  //Pos. spd to extend.

    //Assigned JS's
    private static Button btnExtRollover = JS_IO.roExtButton;   //3JS R6, GP 1(A)
    private static Button btnRetRollover = JS_IO.roRetButton;   //3JS R4, GP 2(B)

    //Assign variables
    private static int state = 0;
    private static double frameSpeed = 0.8; //Speed to Extend & Retract

    /**
     * Runs the top frame to right the robot if upside down.
     * <p>
     * <p>Constructor, not needed, just because.
     */
    public FrameRO(){
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
     * <p>Pressing JS button extends frame.  Pressing another retracts it.
     * <P>
     * <P>Note: Determ was renamed to update & update is now updateSM.
     */
    public static void update() {
        if(btnExtRollover.isDown()){
            state = 1;
        }else if(btnRetRollover.isDown()){
            state = 2;
        }else{
            state = 0;
        }

        updateSM();
        sdbUpdate();
    }

    /**
     * Update state machine.
     * <p>0 = Off.  1 = Extend frame.  2 = Retract frame
     * <p>No end switches.  Stop visually.
     */
    private static void updateSM(){
        switch(state){
            case 0: //All Off
                cmdUpdate(0.0);
            break;
            case 1: //Raise pivot to upper limit
                cmdUpdate(frameSpeed);
            break;
            case 2: //Lower pivot to lower limit
                cmdUpdate(-frameSpeed);
            break;
            default:
                System.out.println("Bad state for FrameRO - " + state);
        }
    }

    /**
     * Issue commands to hardware.  Any safeties MUST be included here
     * to prevent damage to hardware.
     * @param frameSpd
     */
    private static void cmdUpdate(double frameSpd){
        frameExt.set(frameSpd);
    }

    /** Initialize any sdb objects that are later retrieve from sdb. */
    private static void sdbInit(){
        SmartDashboard.putNumber("Frame/Spd Ext & Retract", frameSpeed);
    }

    /** Update sdb objects. */
    public static void sdbUpdate(){
        frameSpeed = SmartDashboard.getNumber("Frame/Spd Ext & Ret", frameSpeed);
    }
}