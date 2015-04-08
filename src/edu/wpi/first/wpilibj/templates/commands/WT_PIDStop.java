package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author meyersbs
 */
public class WT_PIDStop extends CommandBase {
    
    public WT_PIDStop() {
        requires(weightTransferPID);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        weightTransferPID.setWeightTransferPIDMotor(0);
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