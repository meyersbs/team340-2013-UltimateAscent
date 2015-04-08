package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author meyersbs
 */
public class AUTO_DriveUntilTime extends CommandBase {
    
    private double speed = 0;
    private double time = 0;
    
    public AUTO_DriveUntilTime(double s, double t) {
        requires(drive);
        time = t;
        speed = s;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        noSub.startTimer();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drive.arcadeDrive(speed, 0.0, true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (noSub.getTimer() >= time);
    }

    // Called once after isFinished returns true
    protected void end() {
        drive.setLeftDrive(0);
        drive.setRightDrive(0);
        noSub.stopTimer();
        noSub.resetTimer();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        drive.setLeftDrive(0);
        drive.setRightDrive(0);
        noSub.stopTimer();
    }
}
