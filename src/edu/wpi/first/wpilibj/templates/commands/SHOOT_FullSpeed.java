//TODO:Fix speed vs vbus mode in execute.
package edu.wpi.first.wpilibj.templates.commands;

/**
 *Puts shooterMotor up to full speed in VBus mode
 * @param none
 * @return none
 * 
 * @author CJ Atene
 */
public class SHOOT_FullSpeed extends CommandBase 
{
    
    /**
     *
     */
    public SHOOT_FullSpeed() 
    {
        // Use requires() here to declare subsystem dependencies
        requires(score);
    }

    // Called just before this Command runs the first time
    /**
     *Checks to see if it is in speed control. If it is STOP speed control.
     * @param none
     * @return none
     * 
     * @author CJ Atene
     */
    protected void initialize() 
    {
        if(score.isSpeedControl())
        {
            score.stopSpeedControl();
        }
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     *Set shooterMotor to full speed ahead
     * @param none
     * @return none
     * 
     * @author CJ Atene
     */
    protected void execute() 
    {
        score.setSpeed(1.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     *Never finishes 
     * @param none
     * @return boolean false
     * 
     * @author CJ Atene
     */
    protected boolean isFinished() 
    {
        return false;
    }

    // Called once after isFinished returns true
    /**
     *
     */
    protected void end() 
    {
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    /**
     *
     */
    protected void interrupted() 
    {
        
    }
}