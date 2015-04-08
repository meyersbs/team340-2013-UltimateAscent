package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author Adam
 */
public class WT_ShiftWeightBackward extends CommandBase {
    
    public WT_ShiftWeightBackward() 
    {
        requires(weightTransferPID);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println("ShiftWeightBackwards: " + weightTransferPID.getGyroAngle());
        weightTransferPID.moveBackward(-.75);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return(weightTransferPID.getWeightBackwardLimit());
    }

    // Called once after isFinished returns true
    protected void end() {
        weightTransferPID.stopMove();
        System.out.println("Weight Done Shifting Backwards");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        weightTransferPID.stopMove();
    }
}