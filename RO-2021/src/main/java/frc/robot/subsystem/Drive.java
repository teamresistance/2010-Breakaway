package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.io.hdw_io.IO;
import frc.robot.io.joysticks.JS_IO;

public class Drive {

    private static Victor leftDrive = IO.leftDriveMotor;
    private static Victor rightDrive = IO.rightDriveMotor;

    private static double scaleFactor;

    /**
     * Drive the robot using tank drive.
     * <p>
     * <p>Constructor, not needed, just because.
     */
    public Drive() {
    }

    /**
     * Initialize any objects.
     * <p>Usually called from autonomous/telopPeriodic.
     */
    public static void init() {
        scaleFactor = 1;
        sdbInit();
    }

    /**
     * This is called from robot autonomous/telopPeriodic every 20mS to evaluate
     * hardware or joystick actions to set the state.
     * <p>
     * <p>Left JS Y axis drives left drive wheel.  Right drives right. 
     */
    public static void update() {
        leftDrive.set(JS_IO.axLeftDrive.get() * scaleFactor);
        rightDrive.set(-JS_IO.axRightDrive.get() * scaleFactor);
        sdbUpdate();
    }

    /** Initialize any sdb objects that are later retrieve from sdb. */
    private static void sdbInit(){
        SmartDashboard.putNumber("Drive/Scale", scaleFactor);
    }

    /** Update sdb objects. */
    public static void sdbUpdate(){
        SmartDashboard.getNumber("Drive/Scale", scaleFactor);
        SmartDashboard.putNumber("Drive/Left JS", JS_IO.axLeftDrive.get());
        SmartDashboard.putNumber("Drive/Right JS", JS_IO.axRightDrive.get());
    }
}