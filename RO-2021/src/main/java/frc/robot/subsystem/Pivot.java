package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.io.hdw_io.IO;
import frc.robot.io.hdw_io.InvertibleDigitalInput;
import frc.robot.io.joysticks.Button;
import frc.robot.io.joysticks.JS_IO;

public class Pivot{

    //Assign hdw
    private static Victor pivot = IO.pivotMotor;
    private static InvertibleDigitalInput pivotLoPermissive = IO.pivotLowerLimit;
    private static InvertibleDigitalInput pivotHiPermissive = IO.pivotUpperLimit;

    //Assigned JS's
    private static Button lowerKicker = JS_IO.kickerLowButton;  //Lower kicker to lo ES.  3JS R5, GP 3(X)
    private static Button raiseKicker = JS_IO.kickerHiButton;   //Raise kicker to hi ES.  3JS R3, GP 4(Y)

    //Assign variables
    private static int state = 0;
    private static double pivotSpeed = 0.5; //Speed to raise & lower kicker.

    /**
     * Pivot raises & lowers the kicker.  Low kicks the soccer ball high
     * and high position kicks the ball level, along the ground.
     * <p>
     * <p>Constructor, not needed, just because.
     */
    public Pivot(){
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
     * <p>Pressing up JS button raises the kicker to the upper end switch.
     * Pressing the down JS button lowers the kicker to the lower end switch.
     * <P>
     * <P>Note: Determ was renamed to update & update is now updateSM.
     */
    public static void update() {
        if(raiseKicker.onButtonPressed()) state = 1;
        if(lowerKicker.onButtonPressed()) state = 2;

        if((state == 1 && !pivotHiPermissive.get()) ||          //If going up & at upper ES or
           (state == 2 && !pivotLoPermissive.get())) state = 0; //if going down & at lower ES STOP.

        updateSM();     //update the state machine
        sdbUpdate();    //update Smartdashboard display
    }

    /**
     * Update state machine.
     * <p>0 = Off.  1 = Raise to upper limit.  2 = lower to lower limit.
     */
    private static void updateSM(){
        switch(state){
            case 0: //All Off
                cmdUpdate(0.0);
            break;
            case 1: //Raise pivot to upper limit
                cmdUpdate(-pivotSpeed);
            break;
            case 2: //Lower pivot to lower limit
                cmdUpdate(pivotSpeed);
            break;
            default:
                System.out.println("Bad state for Pivot - " + state);
        }
    }
    
    /**
     * Issue commands to hardware.  Any safeties MUST be included here
     * to prevent damage to hardware.
     * @param pivotSpd speed to move Pivot.  Note negative speed moves Pivot higher.
     */
    private static void cmdUpdate(double pivotSpd){
        //Check safeties and stop else send cmd
        //pivot spd - is dn and + is up
        if((pivotSpd < 0.0 && !pivotHiPermissive.get()) ||  //neg. going up & not permitted
           (pivotSpd > 0.0 && !pivotLoPermissive.get())){   //pos. going dn & not permitted
            pivot.set(0.0);                                 //STOP
        }else{
            pivot.set(pivotSpd);    //else move
        }
    }

    /**
     * Initialize any sdb objects that are later retrieve from sdb.
     */
    private static void sdbInit(){
        SmartDashboard.putNumber("Pivot/Spd Up & Dn", pivotSpeed);
    }

    /**
     * Update sdb objects.
     */
    public static void sdbUpdate(){
        pivotSpeed = SmartDashboard.getNumber("Pivot/Spd Up & Dn", pivotSpeed);
        SmartDashboard.putBoolean("Pivot/Upper Permissive", IO.pivotUpperLimit.get());
        SmartDashboard.putBoolean("Pivot/Lower Permissive", IO.pivotLowerLimit.get());
        SmartDashboard.putNumber("Pivot/state", state);
    }
}