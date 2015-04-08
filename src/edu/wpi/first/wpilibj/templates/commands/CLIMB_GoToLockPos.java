package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author Ben Meyers
 * 
 * Drives the arm motor until specified encoder value is reached
 */
public class CLIMB_GoToLockPos extends CommandBase {
    
    private double encoderStopValue;
    private double tolerance;
    
    /**
     *Constructor
     * @Parem double speed, double encoderValue
     */
    public CLIMB_GoToLockPos(double encoderValue, double tol) {
        requires(climb);
        tolerance = tol;
        encoderStopValue = encoderValue;
    }
    
    /**
     *Called just before this Command runs the first time
     */
    protected void initialize() {
        System.out.println("GoToLockPos");
    }

    /**
     *Called repeatedly when this Command is scheduled to run
     *Releases arm lock and drives motor at specified speed.
     */
    protected void execute() {
        climb.setArmLockOff();
        System.out.println("GoToLockPos; Encoder: " + climb.getArmEncoderValue());
        
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