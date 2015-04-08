package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author meyersbs
 */
public class WT_GoToFeedPos extends CommandBase
{
    
    public WT_GoToFeedPos() 
    {
        requires(weightTransferPID);
        requires(score);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
        score.setDiskBlocker(score.BRUSH_DISC_IN);
        System.out.println("init go to feed position");
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
        if(score.isThereADisk()){
            score.setDiskBlocker(0);
        }
        weightTransferPID.setWeightTransferPIDMotor(1.0); //TODO: Get Value
        System.out.println("feeding/moving");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return weightTransferPID.getWeightForwardLimit();
    }

    // Called once after isFinished returns true
    protected void end() 
    {
        score.setDiskBlocker(0);
        weightTransferPID.setWeightTransferPIDMotor(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
        score.setDiskBlocker(0);
        weightTransferPID.setWeightTransferPIDMotor(0.0);      
    }
}