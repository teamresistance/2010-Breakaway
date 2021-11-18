package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.io.hdw_io.IO;
import frc.robot.io.hdw_io.InvertibleDigitalInput;
import frc.robot.io.joysticks.Axis;
import frc.robot.io.joysticks.Button;
import frc.robot.io.joysticks.JS_IO;

/**
 * Test for ALL hardware.  All other subSystem calls must be
 * commented out. 
 */
public class Test_Hdw {
    //Ref hardware
    private static Victor lDrvMtr = IO.leftDriveMotor;
    private static Victor rDrvMtr = IO.rightDriveMotor;

    private static Victor frameArmMotor = IO.frameArmMotor;
    private static Victor kickerMotor = IO.kickerMotor;

    private static Victor pivotUpDnMtr = IO.pivotMotor;
    private static InvertibleDigitalInput pivotLoPermissive = IO.pivotLowerLimit;
    private static InvertibleDigitalInput pivotUpPermissive = IO.pivotUpperLimit;

    //Joysticks
    private static Axis lDrvCtl = JS_IO.axLeftY;
    private static Axis rDrvCtl = JS_IO.axRightY;

    private static Button frameExtend = JS_IO.roExtButton;
    private static Button frameRetract = JS_IO.roRetButton;

    private static Button kickerSpin = JS_IO.kickerSpin;
    private static Button kickerRaise = JS_IO.kickerHiButton;
    private static Button kickerLower = JS_IO.kickerLowButton;

    //Variables
    
    public static void init(){
        sdbInit();
    }

    public static void update(){
        lDrvMtr.set(lDrvCtl.get());
        rDrvMtr.set(rDrvCtl.get());
        frameArmMotor.set(frameExtend.isDown() ?  1.0 :
                         frameRetract.isDown() ? -1.0 : 0.0);

        kickerMotor.set(kickerSpin.isDown() ? 1.0 : 0.0);

        pivotUpDnMtr.set(kickerRaise.isDown() ? -1.0 :
                         kickerLower.isDown() ?  1.0 : 0.0);

        sdbUpdate();
    }

    /** Initialize any sdb objects that are later retrieve from sdb. */
    private static void sdbInit(){
    }

    /** Update sdb objects. */
    private static void sdbUpdate(){
        SmartDashboard.putNumber("TestHdw/1A. Left Drv Mtr", lDrvCtl.get());
        SmartDashboard.putNumber("TestHdw/1B. Rite Drv Mtr", rDrvCtl.get());

        SmartDashboard.putBoolean("TestHdw/2A. Btn Spin Kicker", kickerSpin.isDown());
        SmartDashboard.putNumber("TestHdw/2A. Kicker Mtr", kickerMotor.get());

        SmartDashboard.putBoolean("TestHdw/3A. Btn Frame Extend", frameExtend.isDown());
        SmartDashboard.putBoolean("TestHdw/3B. Btn Frame Retract", frameRetract.isDown());
        SmartDashboard.putNumber("TestHdw/3C. Frame Mtr", frameArmMotor.get());

        SmartDashboard.putBoolean("TestHdw/4A. Btn Pivot Up", kickerRaise.isDown());
        SmartDashboard.putBoolean("TestHdw/4B. Btn Pivot Dn", kickerLower.isDown());
        SmartDashboard.putNumber("TestHdw/4C. Pivot Mtr", pivotUpDnMtr.get());
        SmartDashboard.putBoolean("TestHdw/4D. Btn Pivot Upper ES", pivotUpPermissive.get());
        SmartDashboard.putBoolean("TestHdw/4E. Btn Pivot Lower ES", pivotLoPermissive.get());

    }
}
