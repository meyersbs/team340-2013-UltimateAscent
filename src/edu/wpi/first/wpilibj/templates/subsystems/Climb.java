/*
 * @author Adam Audycki
 * @author Ben Meyers
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.templates.commands.CG_ClimbAllTheWay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * @author Adam Audycki & Ben Meyers
 * Subsystem where all of the climbing sensors/inputs are made.
 */
public class Climb extends Subsystem
{
    Victor topArmVic;
    Victor lowArmVic;
    
    DigitalInput encA;
    DigitalInput encB;
    
    Encoder armEncoder;
    Solenoid armLock;
    DigitalInput topArmLimitSwitch;
    DigitalInput bottomArmLimitSwitch;

    /**
     * @param none
     * Constructs the Climb Subsystem.
     */
    public Climb()
    {
        encA = new DigitalInput(7);
        encB = new DigitalInput(8);
        armEncoder = new Encoder(encA, encB, true, CounterBase.EncodingType.k1X);
        armEncoder.start();
        armLock = new Solenoid(1);
        topArmLimitSwitch = new DigitalInput(9);
        bottomArmLimitSwitch = new DigitalInput(10);
        topArmVic = new Victor(1);
        lowArmVic = new Victor(3);
    }
    
    /**
     * @return void
     * @param none
     * Sets the default command for climbing.
     */
    public void initDefaultCommand()
    {
        //setDefaultCommand(new ClimbAllTheWay());
    }
    
    /**
     * @param none
     * @return double: encoder value for the arm.
     * Returns arm's encoder value.
     */
    public double getArmEncoderValue()
            
    {
        return armEncoder.get();
    }
    
    /**
     * @param double: motor speed for the arm.
     * @return void
     * Sets arm speed.
     */
    public void setArmMotorSpeed(double speed)
    {
        if(speed > 1)
        {
            speed = 1;
        }
        
        else if(speed < -1)
        {
            speed = -1;
        }
        try
        {
            System.out.println("Arm Speed: " + speed);
            lowArmVic.set(speed);  
            topArmVic.set(speed);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * @return void
     * Locks the arm.
     */
    public void setArmLockOn()
    {
        armLock.set(false);
    }
    
    /**
     * @return void
     * Unlocks the arm.
     */
    public void setArmLockOff()
    {
        armLock.set(true);
    }
    
    /**
     * 
     * @return boolean when top arm limit switch is pressed.
     */
    public boolean getTopArmSwitch()
    {
        //System.out.println("Arm Top Switch: " + !topArmLimitSwitch.get()); //Wired normally true.
        return !topArmLimitSwitch.get();
    }
    
    /**
     * 
     * @return boolean when bottom limit switch is pressed.
     */
    public boolean getBottomArmSwitch()
    {
        //System.out.println("Arm Bot Switch: " + !bottomArmLimitSwitch.get()); //Wired normally true.
        return !bottomArmLimitSwitch.get();
    }
    
    /**
     * @return void
     * Reset Encoder.
     */
    public void resetEncoder()
    {
        armEncoder.reset();
        System.out.println("ENCODER RESET!");
    }

    public boolean getEncA() {
        return encA.get();
    }

    public boolean getEncB() {
        return encB.get();
    }
}