package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;


/**
 *
 * @author Tyler Pawlaczyk (http://rainingbitsandbytes.blogspot.com/)
 */
public class SHOOT_FeedBackward extends CommandBase {

    Timer time;
    public SHOOT_FeedBackward() {
        // Use requires() here to declare subsystem dependencies
        requires(score);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        time = new Timer();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        time.start();
        score.setDiskBlocker(-1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return oi.getCoButtonBack(); //time.get() >= 1.0;
    }

    // Called once after isFinished returns true
    protected void end() {
        score.setDiskBlocker(0);
        time.stop();
        time.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        score.setDiskBlocker(0); //TODO: problem with this.end() being run here.
        time.stop();
        time.reset();
    }
}