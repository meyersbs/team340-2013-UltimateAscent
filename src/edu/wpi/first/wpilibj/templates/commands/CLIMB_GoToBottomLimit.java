package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author meyersbs
 */
public class CLIMB_GoToBottomLimit extends CommandBase {
    
    public CLIMB_GoToBottomLimit() {
        requires(climb);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        climb.setArmLockOff();
        climb.setArmMotorSpeed(1.0); //TODO: Get Value
        System.out.println("GoToBottomLimit; Encoder: " + climb.getArmEncoderValue());
        //System.out.println("Encoder Value: " + climb.getArmEncoderValue() + " A:" + climb.getEncA() + ": B:" + climb.getEncB() + ":");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return climb.getBottomArmSwitch();
    }

    // Called once after isFinished returns true
    protected void end() {
        climb.resetEncoder();
        climb.setArmMotorSpeed(0.0);
        climb.setArmLockOn();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        climb.setArmMotorSpeed(0.0);
        climb.setArmLockOn();        
    }
}