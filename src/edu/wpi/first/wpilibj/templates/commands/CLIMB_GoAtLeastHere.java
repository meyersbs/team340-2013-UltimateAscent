package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;


/**
 *
 * @author Ben Meyers
 * 
 * Drives the arm motor until specified encoder value is reached
 */
public class CLIMB_GoAtLeastHere extends CommandBase {
    
    private double motorSpeed;
    private double encoderStopValue;
    private double tolerance;
    private Timer time;
    
    /**
     *Constructor
     * @Parem double speed, double encoderValue
     */
    public CLIMB_GoAtLeastHere(double speed,double encoderValue, double tol) {
        requires(climb);
        motorSpeed = speed;
        tolerance = tol;
        encoderStopValue = encoderValue;
        time = new Timer();
                
        time.start();
    }
    
    /**
     *Called just before this Command runs the first time
     */
    protected void initialize() {
        System.out.println("GoAtLeastHere");
    }

    /**
     *Called repeatedly when this Command is scheduled to run
     *Releases arm lock and drives motor at specified speed.
     */
    protected void execute() {
        climb.setArmLockOff();
        climb.setArmMotorSpeed(motorSpeed);
        System.out.println("GoAtLeastHere; Encoder :" + climb.getArmEncoderValue() + ": time :" + time.get());
    }

    /**
     * Make this return true when this Command no longer needs to run execute()
     * @return boolean when we reach specified encoder value.
     */
    protected boolean isFinished() {
        if(encoderStopValue > climb.getArmEncoderValue() && climb.getTopArmSwitch())
        {
            return true;
        }
        else if(encoderStopValue < climb.getArmEncoderValue() && climb.getBottomArmSwitch())
        {
            return true;
        }
        else if(climb.getArmEncoderValue() > encoderStopValue)
        {
            return true;
        }
        return false;
    }

    /**
     * Called once after isFinished returns true
     * Stop motor, lock arm.
     */
    protected void end() {
        climb.setArmMotorSpeed(0);
        climb.setArmLockOn();
        time.stop();
    }

    /**
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run
     * Stop motor, lock arm.
     */
    protected void interrupted() {
        climb.setArmMotorSpeed(0);
        climb.setArmLockOn();
    }
}