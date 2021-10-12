package frc.robot.io.joysticks;
/*
Original Author: Joey & Anthony
Rewite Author: Jim Hofmann
History:
J&A - 11/6/2019 - Original Release
JCH - 11/6/2019 - Original rework
TODO: Exception for bad or unattached devices.
      Auto config based on attached devices and position?
      Add enum for jsID & BtnID?  Button(eLJS, eBtn6) or Button(eGP, eBtnA)
Desc: Reads joystick (gamePad) values.  Can be used for different stick configurations
    based on feedback from Smartdashboard.  Various feedbacks from a joystick are
    implemented in classes, Button, Axis & Pov.
    This version is using named joysticks to istantiate axis, buttons & axis
*/

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//Declares all joysticks, buttons, axis & pov's.
public class JS_IO {
    public static int jsConfig = 0; // 0=Joysticks, 1=gamePad only, 2=left Joystick only
                                    // 3=Mixed LJS & GP, 4=Nintendo Pad
    // Declare all possible Joysticks
    public static Joystick leftJoystick = new Joystick(0); // Left JS
    public static Joystick rightJoystick = new Joystick(1); // Right JS
    public static Joystick coJoystick = new Joystick(2); // Co-Dvr JS
    public static Joystick gamePad = new Joystick(3); // Normal mode only (not Dual Trigger mode)
    // public static Joystick neoPad = new Joystick(4); // Nintendo style gamepad

    // -------------- Declare all stick controls ---------------
    // Drive
    public static Axis axLeftDrive = new Axis();    // Left Drive
    public static Axis axRightDrive = new Axis();   // Right Drive
    
    public static Axis axLeftY = new Axis();        // Left JS Y - Added for testing in Drive3
    public static Axis axLeftX = new Axis();        // Left JS X
    public static Axis axRightY = new Axis();       // Right JS Y
    public static Axis axRightX = new Axis();       // Right JS X

    //Buttons
    public static Button roExtButton = new Button();    //Rollover extend
    public static Button roRetButton = new Button();    //Rollover retract

    public static Button kickerLowButton = new Button();    //Lower kicker, ball high
    public static Button kickerHiButton = new Button();   //Raise kicker, ball level


    // Constructor
    public JS_IO() {
        init();
    }

    public static void init() {
        SmartDashboard.putNumber("JS/JS_Config", jsConfig);
        chsrInit();
        configJS();
    }

    private static SendableChooser<Integer> chsr = new SendableChooser<Integer>();
    private static String[] chsrDesc = {"3-Joysticks", "2-Joysticks", "Gamepad"};
    private static int[] chsrNum = {0, 1, 2};

    public static void chsrInit(){
        for(int i = 0; i < chsrDesc.length; i++){
            chsr.addOption(chsrDesc[i], chsrNum[i]);
        }
        chsr.setDefaultOption(chsrDesc[0] + " (Default)", chsrNum[0]);   //Default MUST have a different name
        SmartDashboard.putData("JS/Choice", chsr);
        sdbUpdChsr();
    }

    public static void sdbUpdChsr(){
        SmartDashboard.putString("JS/Choosen", chsrDesc[chsr.getSelected()]);   //Put selected on sdb
    }

    // can put this under a button press
    public static void update() { // Chk for Joystick configuration
        //chsr.setDefaultOption doesn't appear to work.  Shouldn't need to trap null.
        //Default MUST have a different name
        if (jsConfig != (chsr.getSelected() == null ? 0 : chsr.getSelected())) {
            caseDefault();      //Clears ALL assignments
            configJS();         //Assigns only those valid for choosen controller
        }
    }

    public static void configJS() { // Default Joystick else as gamepad
        // jsConfig = (int) SmartDashboard.getNumber("JS_Config", 0);
        jsConfig = chsr.getSelected() == null ? 0 : chsr.getSelected();
        SmartDashboard.putNumber("JS/JS_Config", jsConfig);

        switch (jsConfig) {
            case 0: // Normal 3 joystick config
                norm3JS();
                break;

                case 1: // Normal 2 joystick config No CoDrvr
                norm2JS();
                break;

            case 2: // Gamepad only
                a_GP();
                break;

            default: // Bad assignment
                System.out.println("Bad JS choice - " + jsConfig);
                caseDefault();
                break;

        }
    }

    // ================ Controller actions ================

    // ----------- Normal 3 Joysticks -------------
    private static void norm3JS() {

        // All stick axisesssss
        axLeftDrive.setAxis(leftJoystick, 1);
        axRightDrive.setAxis(rightJoystick, 1);
        // axClimb.setAxis(coJoystick, 1);

        axLeftX.setAxis(leftJoystick, 0);
        axLeftY.setAxis(leftJoystick, 1);
        axRightX.setAxis(rightJoystick, 0);
        axRightY.setAxis(rightJoystick, 1);

        // Drive buttons
        roExtButton.setButton(rightJoystick, 6);
        roRetButton.setButton(rightJoystick, 4);

        kickerLowButton.setButton(rightJoystick, 5);
        kickerHiButton.setButton(rightJoystick, 3);
    }

    // ----- gamePad only --------
    private static void a_GP() {
        // All stick axisesssss
        axLeftDrive.setAxis(gamePad, 1); // left stick Y
        axRightDrive.setAxis(gamePad, 5); // right stick Y

        // Drive buttons
        roExtButton.setButton(gamePad, 1);
        roRetButton.setButton(gamePad, 2);

        kickerLowButton.setButton(gamePad, 3);
        kickerHiButton.setButton(gamePad, 4);
    }

    // ----------- Normal 2 Joysticks -------------
    private static void norm2JS() {

        // All stick axisesssss
        axLeftDrive.setAxis(leftJoystick, 1);
        axRightDrive.setAxis(rightJoystick, 1);

        // Drive buttons
        roExtButton.setButton(rightJoystick, 6);
        roRetButton.setButton(rightJoystick, 4);

        kickerLowButton.setButton(rightJoystick, 5);
        kickerHiButton.setButton(rightJoystick, 3);
    }

    // ----------- Case Default -----------------
    private static void caseDefault() {
        // All stick axisesssss
        axLeftDrive.setAxis(null, 0);
        axRightDrive.setAxis(null, 0);

        // Drive buttons
        roExtButton.setButton(null, 0);
        roRetButton.setButton(null, 0);

        kickerLowButton.setButton(null, 0);
        kickerHiButton.setButton(null, 0);
    }
}