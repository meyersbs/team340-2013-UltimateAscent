//TODO:Vbus vs speed.
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.templates.subsystems.Score;
/**
 *
 * @author Pat Godard
 * Manual override for setting the shoot speed low, ignores all sensors
 * ALL MANUAL OVERRIDE METHODS CAN BREAK THE ROBOT USE WITH CAUTION
 */
public class MAN_ShootSetSpeedPym extends CommandBase {
    
    private boolean speedControlCheck = score.isSpeedControl();
    
    public MAN_ShootSetSpeedPym() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(score);
    }

    // Called just before this Command runs the first time
    /**
     * turns off speed control systems to activate vBus mode
     */
    protected void initialize() {
         score.stopSpeedControl();
         
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * Turns on the motor to low speed
     */
    protected void execute() {
        System.out.println("Setting Shooter Low" + oi.getShooterPymButton());
        score.setSpeed(score.PymGoalSpeed);
        if(oi.getShooterShootButton())
        {
            System.out.println("Shooting!");
            score.fireOut();
            score.setDiskBlocker(score.BRUSH_DISC_IN);
        }
        else if(!oi.getShooterShootButton())
        {
            System.out.println("Done Shooting");
            score.fireIn();
            score.setDiskBlocker(0);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     * Finishes when the manual override button is released
     */
    protected boolean isFinished() {
        return !oi.getShooterPymButton();
    }

    // Called once after isFinished returns true
    /*
     * Reactivates speed control if it was on
     */
    protected void end() {
        if(speedControlCheck){
            score.startSpeedControl();
        }
        score.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    /*
     * Reactivates speed control if it was on
     */
    protected void interrupted() {
        if(speedControlCheck){
            score.startSpeedControl();
        }
        score.setSpeed(0);
    }
}