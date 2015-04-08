package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author meyersbs
 */
public class SHOOT_GoToFeed extends CommandBase
{
    
    public SHOOT_GoToFeed() 
    {
        requires(score);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
        score.startTimer();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
        score.setSpeed(1); //TODO: Adjust Later
        if(weightTransferPID.getWeightBackwardLimit())
        {
            score.setDiskBlocker(1);    //TODO: Adjust later
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return score.getTimer() >= 1;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
        score.setDiskBlocker(0); //TODO: Adjust later
        score.resetTimer();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
        score.setDiskBlocker(0); //TODO: Adjust later
        score.resetTimer();        
    }
}