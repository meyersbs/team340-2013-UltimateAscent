package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author meyersbs
 */
public class WT_GoToDrivePos extends CommandBase {
    
    private double timeout = 2;
    Timer time = new Timer();
    public WT_GoToDrivePos() {
        requires(weightTransferPID);
        time = new Timer();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    public WT_GoToDrivePos(double timeout) {
        requires(weightTransferPID);
        time = new Timer();
        this.timeout = timeout;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        time.reset();
        time.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        time.start();
        weightTransferPID.moveForward();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return weightTransferPID.getWeightForwardLimit() || weightTransferPID.getWeightMiddleLimit()
                || time.get() >= timeout;
    }

    // Called once after isFinished returns true
    protected void end() {
        weightTransferPID.stopMove();
        time.stop();
        time.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        weightTransferPID.stopMove();
        time.stop();
        time.reset();        
    }
}