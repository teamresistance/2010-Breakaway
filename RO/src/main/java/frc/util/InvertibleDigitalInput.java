    
package frc.util;


import edu.wpi.first.wpilibj.DigitalInput;

public class InvertibleDigitalInput {
  private final DigitalInput limitSwitch;
  private final boolean isInverted;

  //Makes Object and sets it to either inverted or not
  public InvertibleDigitalInput(int channel, boolean isInverted) {
    this.isInverted = isInverted;
    limitSwitch = new DigitalInput(channel);
  }
//Returns the state of the object
  public boolean get() {
    return (isInverted ? !limitSwitch.get() : limitSwitch.get());
  }
}