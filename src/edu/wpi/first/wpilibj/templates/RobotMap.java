package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap 
{
   public static AnalogChannel DRIVE_GYRO = new AnalogChannel(2);;
   public static AnalogChannel ULTRA = new AnalogChannel(3);
   
   public static final double pymShooterVoltage = 4.0;
   public static final double highShooterVoltage = 11.0;
   public RobotMap()
   {
       //DRIVE_GYRO = new AnalogChannel(2);
       //ULTRA = new AnalogChannel(3);
   }
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
}
