package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.templates.commands.SHOOT_SetSpeedToCoJoy;

/*
 * @author CJ Atene
 * @author Matt Stafford
 * @author Adam Audycki
 */

public class Shoot extends Subsystem 
{
    private static final int ShooterSpeedTolerance = 2;
    private static final boolean FIRE_OUT = true;
    private static final boolean FIRE_IN = false;
    private static final double BRUSH_ROLLER_DELAY = 1.0; //TODO: Get Value
    private boolean isThereADisk = false;
    
    public static final double DISC_FALL_DELAY = .2;
    public static final double BRUSH_DISC_IN = 2;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    CANJaguar shooterMotor;
    Victor propeller;
    Timer tyme;
    
    DigitalInput diskChecker;
    DigitalInput propellerChecker;
    boolean isSpeedControl = false;
    double currentSpeed = 0;
    
    static final double Kp = 1;
    static final double Ki = 0;
    static final double Kd = 0;
    
    /**
     *Voltage speed for the high goal during auto.
     */
    public static final double autoSpeed = 11.0;
    
    /**
     * Voltage speed for the pyramid goal while at level 3 on the pyramid.
     */
    public static final double PymSpeed = 4;
    
    public static final double highGoalSpeed = 11.0;
    
    
    
    
    
    static final int ENCPuslePerMinute = 100;
    
    /**
     *CONSTRUCTOR
     */
    public Shoot()
    {
        tyme = new Timer();
        propeller = new Victor(4);
        diskChecker = new DigitalInput(11);
        propellerChecker = new DigitalInput(12);
        
        try
        {
            shooterMotor = new CANJaguar(7);   
            shooterMotor.getPowerCycled();
            LiveWindow.addActuator("Shoot", "ShooterMotor", shooterMotor);
            SmartDashboard.putBoolean("CANJag7", true);
            System.out.println("\nWe made the Shooter jaguar!");
        }catch(CANTimeoutException e)
        {
            e.printStackTrace();
            SmartDashboard.putBoolean("CANJag7", false);
        }
        if(shooterMotor != null)
        {
            this.changeJaguarSettings();//Will not work because shooterMotor is null and shooterMotor calls methods here.
        }
    }
    
    /**
     *
     */
    public void initDefaultCommand() 
    {
        //setDefaultCommand(new SHOOT_SetSpeedToCoJoy());
    }
    
    /**
     * Changes CANJaguar Control mode to Speed controls and enable closed looped support
     * @param none
     * @return none
     * 
     * @author CJ Atene
     */
    public void startSpeedControl()
    {
        try
        {
            shooterMotor.changeControlMode(CANJaguar.ControlMode.kSpeed);
            shooterMotor.enableControl();
            isSpeedControl = true;
            SmartDashboard.putBoolean("CANJag7", true);
        }catch(Exception e)
        {
            e.printStackTrace();
            SmartDashboard.putBoolean("CANJag7", false);
        }
    }
    
    /** 
     * Changes CANJaguars Controls back to default controls and shuts closed looped support
     * @param none
     * @return none
     * 
     * @author CJ Atene
     */
    public void stopSpeedControl()
    {
        try
        {
            shooterMotor.disableControl();
            shooterMotor.changeControlMode(CANJaguar.ControlMode.kVoltage);
            isSpeedControl = false;
            shooterMotor.enableControl();
            SmartDashboard.putBoolean("CANJag7", true);
        }catch(Exception e)
        {
            e.printStackTrace();
            SmartDashboard.putBoolean("CANJag7", false);
        }
    }
    
    /**
     * if is in VBus mood give the motor a proportional voltage and if is in 
     * speed mood defines the set point
     * @param speed
     * @return none
     * 
     * @author CJ Atene
     */
    public void setSpeed(double speed)
    {
        if(!isSpeedControl){
            if( speed > 12)
            {
                speed = 12;
            }
            else if(!isSpeedControl && speed < -12)
            {
                speed = -12;
            }
        }
        try
        {
            shooterMotor.setX(speed);
            currentSpeed = speed;
            SmartDashboard.putNumber("Score-Speed",shooterMotor.getOutputVoltage());            
            SmartDashboard.putBoolean("CANJag7", true);
        }catch(Exception e)
        {
            e.printStackTrace();
            SmartDashboard.putBoolean("CANJag7", false);
        }
    }
    
    /**
     * if CANJauguar shuts down and is in speed mode, this method 
     * gives the Jag its previous information again
     * @param none
     * @return none
     * 
     * @author CJ Atene
     */
    public void upkeepSpeedControl()
    {
        boolean powerCycle = false;
	short fault = 0;
        try
        {
	    fault = shooterMotor.getFaults();
            powerCycle = shooterMotor.getPowerCycled();
            SmartDashboard.putBoolean("CANJag7", true);
        }catch(Exception e)
        {
            e.printStackTrace();
            SmartDashboard.putBoolean("CANJag7", false);
        }    
        
        if(powerCycle || fault != 0)
        {
            this.changeJaguarSettings();
            
            if(isSpeedControl)
            {
               try
               {
                    this.startSpeedControl();
                    this.setSpeed(currentSpeed);
                    SmartDashboard.putBoolean("CANJag7", true);
               }catch(Exception e)
               {
                  e.printStackTrace();
                  SmartDashboard.putBoolean("CANJag7", false);
               }
            }
        }
    }
    
    /**
     * Checks to see if motor is up to speed in speed mode
     * @param none
     * @return if the motor is up to speed 
     * 
     * @author CJ Atene
     */
    public boolean isSpeed()
    {
        if(currentSpeed == 0 && isSpeedControl)
        {
            return false;
        }
        else if(!isSpeedControl)
        {
            try 
            {
                SmartDashboard.putBoolean("CANJag7", true);
                return this.shooterMotor.getX() !=0;
                
            } catch (CANTimeoutException ex) {
                ex.printStackTrace();
                SmartDashboard.putBoolean("CANJag7", false);
            }
            return false;
        }
        try
        {
            SmartDashboard.putBoolean("CANJag7", true);
            SmartDashboard.putNumber("Score-Speed",shooterMotor.getX());
            return Math.abs(shooterMotor.getX() - shooterMotor.getSpeed()) < ShooterSpeedTolerance;   
            //TODO: ADJUST FOR ACTUALLY SPEED
         }catch(Exception e)
         {
            SmartDashboard.putBoolean("CANJag7", false);
            e.printStackTrace();
            return false;
         } 
    }
    
    public double getShooterSpeed(){
        return currentSpeed;
    }
    
    /**
     * Changes private isSpeedControl to public
     * @param none
     * @return are the Jags in speed control mode
     * 
     * @author CJ Atene
     */
    public boolean isSpeedControl()
    {
        return this.isSpeedControl;
    }
    
    /**
     * Sets the CANJaguars back away from their default settings
     * @param none
     * @return none
     * 
     * @author CJ Atene
     */
    private void changeJaguarSettings()
    {
        try
        {
        shooterMotor.configEncoderCodesPerRev(ENCPuslePerMinute);  //ALTER THIS LATER
        shooterMotor.changeControlMode(CANJaguar.ControlMode.kVoltage);
        this.isSpeedControl = false;
        shooterMotor.setPID(Kp, Ki, Kd);
        shooterMotor.getPowerCycled();
        shooterMotor.enableControl();
        shooterRamp();
        SmartDashboard.putBoolean("CANJag7", true);
        }catch(Exception e)
        {
            e.printStackTrace();
            SmartDashboard.putBoolean("CANJag7", false);
        }
    }
    
    public void shooterRamp()
    {
        try
        {
            shooterMotor.setVoltageRampRate(4.0);            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public double getShooterMotorSpeed(){
       
        try {
             return shooterMotor.getSpeed();
        } catch (CANTimeoutException ex) {
            return -666;
        }
    }
    
    /**
     * 
     * @!return boolean true if there is a disk in the shooter.
     * @author Adam Audycki
     */
    public boolean isThereADisk()
    {
        if(diskChecker.get())
        {
            isThereADisk = true;
        }
        
        else
        {
            isThereADisk = false;
        }
        return isThereADisk;
    }
    
    /**
     * @return true if the banner sensor is tripped.
     * @author Adam Audycki
     */
    public boolean propellerCheck()
    {
        return propellerChecker.get();
    }
    
    public void enable(){
	try
        {
	    shooterMotor.enableControl();
        SmartDashboard.putBoolean("CANJag7", true);
        }catch(Exception e)
        {
            e.printStackTrace();
            SmartDashboard.putBoolean("CANJag7", false);
        }
    }
    
    public void disable(){
	try
        {
	    shooterMotor.disableControl();
        SmartDashboard.putBoolean("CANJag7", true);
        }catch(Exception e)
        {
            e.printStackTrace();
            SmartDashboard.putBoolean("CANJag7", false);
        }
    }
    /**
     * Sets the disk blocker.
     * @return void
     * @author Adam Audycki
     */
    public void setDiskPropeller(double speed)
    {
        propeller.set(speed);
    }
    
    public void startTimer()
    {
        tyme.start();
    }
    
    public double getTimer()
    {
        return tyme.get();
    }
    
    public void resetTimer()
    {
        tyme.reset();
    }
}