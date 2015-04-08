package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author Adam Audycki
 */
public class SHOOT_DiskBlocker extends CommandBase {
    
    public SHOOT_DiskBlocker() {
        requires(score);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        score.setDiskBlocker(1.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        score.setDiskBlocker(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        score.setDiskBlocker(0.0);
    }
}
