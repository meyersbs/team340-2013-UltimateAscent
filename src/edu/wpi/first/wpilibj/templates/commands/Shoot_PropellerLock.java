/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author meyersbs
 */
public class Shoot_PropellerLock extends CommandBase {
    
    public Shoot_PropellerLock() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(shoot);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        shoot.resetTimer();
        shoot.startTimer();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        shoot.setDiskPropeller(-0.3);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return shoot.getTimer() >= 0.25;
    }

    // Called once after isFinished returns true
    protected void end() {
        shoot.setDiskPropeller(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
