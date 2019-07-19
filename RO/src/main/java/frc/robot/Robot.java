package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.util.*;

public class Robot extends TimedRobot {

  Drive drive;
  Kicker kicker;
  Flipper flipper;

  @Override
  public void robotInit() {
    drive = new Drive(IO.leftDriveMotor, IO.rightDriveMotor);
    kicker = new Kicker(IO.pivotMotor, IO.kickerMotor);
    flipper = new Flipper(IO.frameArmMotor);

    drive.init();
    flipper.init();
    kicker.init();
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putBoolean("lower", IO.pivotLowerLimit.get());
    SmartDashboard.putBoolean("upper", IO.pivotUpperLimit.get());
  }

  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopPeriodic() {
    drive.update();
    
    kicker.update();
    flipper.update();
    
    JoystickIO.update();
  }

  @Override
  public void testPeriodic() {
    
  }
}
