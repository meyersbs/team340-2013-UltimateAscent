package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author meyersbs
 */
public class WT_Stop extends CommandBase {
    
    public WT_Stop() {
        requires(weightTransferPID);
    }

    protected void initialize() {
    }

    protected void execute() {
        weightTransferPID.stopMove();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (!weightTransferPID.isMotorOn());
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}