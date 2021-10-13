package frc.robot.subsystem;

import frc.robot.io.hdw_io.IO;

public class LedBtn {
    
    public static void update(){
        IO.redLed.set(IO.redBtn.get());
    }
}
