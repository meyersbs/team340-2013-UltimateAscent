package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author Pat Godard
 * Manual override for shifting weight backwards, ignores all sensors
 * ALL MANUAL OVERRIDE METHODS CAN BREAK THE ROBOT USE WITH CAUTION
 */
public class MAN_WeightShiftBack extends CommandBase {
    
    public MAN_WeightShiftBack() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(weightTransferPID);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * Turns the weight transfer motor on in reverse
     */
    protected void execute() {
        System.out.println("Shifting Weight Backwards");
        weightTransferPID.moveBackward(-.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     * Finishes when the manual override button is released
     */
    protected boolean isFinished() {
        return !oi.getWeightBack().get();
    }

    // called once after isFinished returns true
    /**
     * Stops motor
     */
    protected void end() {
        System.out.println("Stopping Backward Weight Shift");
        weightTransferPID.stopMove();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    /**
     * Stops motor
     */
    protected void interrupted() {
        weightTransferPID.stopMove();
    }
}