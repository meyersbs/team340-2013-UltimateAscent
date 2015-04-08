package edu.wpi.first.wpilibj.templates.commands;

/**
 *Sets motor to a speed for high goal
 * 
 * @author Adam Audycki
 */
public class SHOOT_SetPymGoalShootOnPym extends CommandBase 
{
    
    /**
     *
     */
    public SHOOT_SetPymGoalShootOnPym() 
    {
        // Use requires() here to declare subsystem dependencies
        //requires(score);
    }

    // Called just before this Command runs the first time
    /**
     *If Jaguar is not in speed control, it changes it to speed control
     * Then sets Jaguar to a set speed for High goal NOT SET YET 
     */
    protected void initialize() 
    {
        if(!score.isSpeedControl())
        {
            //score.startSpeedControl();
        }
        score.setSpeed(score.OnPymGoalSpeed);
        System.out.println("SHOOT_SetPymGoal is init!");
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     *
     */
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     *never finish
     * @return boolean false
     */
    protected boolean isFinished() {
        return oi.getCoButtonLB();
    }

    // Called once after isFinished returns true
    /**
     *
     */
    protected void end() {
        score.setSpeed(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    /**
     *
     */
    protected void interrupted() {
    }
}
