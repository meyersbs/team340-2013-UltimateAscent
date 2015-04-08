package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author Pat Godard
 * Manual override for shooting, ignores all sensors
 * ALL MANUAL OVERRIDE METHODS CAN BREAK THE ROBOT USE WITH CAUTION
 */
public class MAN_ShooterShoot extends CommandBase {
    
    public MAN_ShooterShoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(score);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        score.fireOut();
        score.setDiskBlocker(score.BRUSH_DISC_IN);
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * Piston pushes disks into motors
     * MAKE SURE THE MOTORS ARE RUNNING FIRST
     */
    protected void execute() {
//        if(score.isSpeed())
//        {
            System.out.println("Shooting!");
            
//        }
//        else
//            System.out.println("Shooter is not to proper speed");
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     * Finishes when the manual override button is released
     */
    protected boolean isFinished() {
        return !oi.getShooterShootButton();
    }

    // Called once after isFinished returns true
    /**
     * retracts the piston
     */
    protected void end() {
        System.out.println("Done Shooting");
        score.fireIn();
        score.setDiskBlocker(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    /**
     * retracts the piston
     */
    protected void interrupted() {
        score.fireIn();
    }
}