package edu.wpi.first.wpilibj.templates.commands;

/**
 *Sets the shooter speed using the co-driver controller.
 * Purely for testing purposes.
 * @author John
 */
public class SHOOT_SetSpeedToCoJoy extends CommandBase {
    
    public SHOOT_SetSpeedToCoJoy() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(score);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
        if(!score.isSpeedControl())
        {
            score.setSpeed(oi.getCoLftJoyY() * 10);
            System.out.println("\tvoltage:" + oi.getCoLftJoyY() * 10);
        }else{
            score.setSpeed(0);
            System.out.println("\t\t\tWe are in speed control so we stopped");
        }
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        score.setSpeed(oi.getCoLftJoyY() * 10);
        System.out.println("Speed is set to : " +oi.getCoLftJoyY() * 10); 
        //score.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        score.setSpeed(oi.getCoLftJoyY() * 10);
        System.out.println("Speed is set to : " +oi.getCoLftJoyY() * 10);
        //score.setSpeed(0);
    }
}