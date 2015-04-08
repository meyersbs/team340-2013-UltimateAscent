package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.commands.DS_PrintToLCD;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author meyersbs
 */
public class NoSub extends Subsystem {
    
    Timer time = new Timer();
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new DS_PrintToLCD());
    }
    
 public double autoState = SmartDashboard.getNumber("AutoState",1);
 
 public void startTimer()
 {
     time.start();
 }
 
 public void stopTimer()
 {
     time.stop();
 }
 
 public void resetTimer()
 {
     time.reset();
 }
 
 public double getTimer()
 {
     return time.get();
 }
}