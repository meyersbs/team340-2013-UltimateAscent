package edu.wpi.first.wpilibj.templates.commands;

/**
 * If the Jaguar is not in speed control, it sets it to speed control
 * Sets the motor to the speed for medium goal NOT SET YET
 * @param 
 * @return
 * 
 * @author CJ Atene
 */
public class SHOOT_SetMedGoal extends CommandBase 
{
    
    /**
     *
     */
    public SHOOT_SetMedGoal() 
    {
        // Use requires() here to declare subsystem dependencies
        requires(score);
    }

    // Called just before this Command runs the first time
    /**
     *If Jaguar is not in speed control, it puts it in speed control
     *Also get the approved control for medium goal
     * @param 
     * @return 
     * 
     * @author CJ Atene
     */
    protected void initialize() 
    {
         if(!score.isSpeedControl())
        {
            //score.startSpeedControl();
        }
        score.setSpeed(score.medGoalSpeed);
        
        System.out.println("SHOOT_SetMedGoal is init!");
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