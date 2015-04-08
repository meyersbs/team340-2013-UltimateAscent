package edu.wpi.first.wpilibj.templates.commands;

/**
 *Stops scoring if something happens
 * @param 
 * @return
 * 
 * @author CJ Atene
 */
public class SHOOT_AllStop extends CommandBase 
{
    
    /**
     *
     */
    public SHOOT_AllStop() 
    {
        // Use requires() here to declare subsystem dependencies
        requires(score);
    }

    // Called just before this Command runs the first time
    /**
     * Stops speed control, sets speed to 0, pulls firing pin in.
     * @param none
     * @return none
     * 
     * @author CJ Atene
     */
    protected void initialize() 
    {
        score.stopSpeedControl();
        score.setSpeed(0.0);
        score.fireIn();
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     *
     */
    protected void execute() 
    {
     
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     *Kills the method when it's finished
     * @param none
     * @return boolean true
     * 
     * CJ Atene
     */
    protected boolean isFinished() 
    {
        return true;
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