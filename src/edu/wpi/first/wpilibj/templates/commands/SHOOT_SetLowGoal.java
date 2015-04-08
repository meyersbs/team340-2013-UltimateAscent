package edu.wpi.first.wpilibj.templates.commands;

/**
 *Sets the motor to the speed for low goal
 * 
 * @author CJ Atene
 */
public class SHOOT_SetLowGoal extends CommandBase 
{
    
    /**
     *
     */
    public SHOOT_SetLowGoal() 
    {
        // Use requires() here to declare subsystem dependencies
        requires(score);
    }

    // Called just before this Command runs the first time
    /**
     *If Jaguar is not in speed control, put it in speed control
     * Then sets Jaguar to selective speed for Low goal NOT SET YET
     * @param none
     * @return none
     * 
     * @author CJ Atene
     */
    protected void initialize() 
    {
         if(!score.isSpeedControl())
        {
            score.startSpeedControl();
        }
         score.setSpeed(score.lowGoalSpeed);
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     *
     */
    protected void execute() 
    {
        
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     *Never finishes
     * @param none
     * @return false
     * 
     * @author CJ Atene
     */
    protected boolean isFinished() 
    {
        return false;
    }

    // Called once after isFinished returns true
    /**
     *
     */
    protected void end() 
    {
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    /**
     *
     */
    protected void interrupted() 
    {
        
    }
}
