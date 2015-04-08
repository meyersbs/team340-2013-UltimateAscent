package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author Pat Godard
 * Manual override for setting the claw down, ignores all sensors
 * ALL MANUAL OVERRIDE METHODS CAN BREAK THE ROBOT USE WITH CAUTION
 */
public class MAN_ClawDown extends CommandBase {
    
    public MAN_ClawDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(climb);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * Unlocks the motor then sets it in reverse
     */
    protected void execute() {
        System.out.println("Claw Going Down");
        climb.setArmLockOff();
        climb.setArmMotorSpeed(-1);
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     * Finishes when the manual override button is released
     */
    protected boolean isFinished() {
        return !oi.getClawDown().get();
    }

    // Called once after isFinished returns true
    /**
     * Turns off and relocks the motor
     */
    protected void end() {
        System.out.println("Claw Done Going Down");
        climb.setArmMotorSpeed(0);
        climb.setArmLockOn();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    /**
     * Turns off and relocks the motor
     */
    protected void interrupted() {
        climb.setArmMotorSpeed(0);
        climb.setArmLockOn();
    }
}
