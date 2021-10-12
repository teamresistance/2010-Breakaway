package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import frc.util.Button;
import java.util.ArrayList;

public class JoystickIO {

    //Joysticks
    public static Joystick leftJoystick = new Joystick(0);
    public static Joystick rightJoystick = new Joystick(1);
    public static Joystick coJoystick = new Joystick(2);

    //Buttons
    private static ArrayList<Button> buttons = new ArrayList<>();

    //public static Button pivotUpButton = createButton(coJoystick, 8);
    //public static Button pivotDownButton = createButton(coJoystick, 9);

    public static Button armUpButton = createButton(rightJoystick, 6);
    public static Button armDownButton = createButton(rightJoystick, 4);

    public static Button kickerOutButton = createButton(coJoystick, 5);
    public static Button kickerBackButton = createButton(coJoystick, 3);


    public static void update() {
      for (Button b : buttons) {
        b.update();
      }
    }

    
    private static Button createButton(GenericHID stick, int button) {
		Button newButton = new Button(stick, button);
		buttons.add(newButton);
		return newButton;
	}
}