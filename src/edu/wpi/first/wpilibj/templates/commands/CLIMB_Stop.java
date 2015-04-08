package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author Adam
 * 
 * Interrupts all of the climbing commands
 */
public class CLIMB_Stop extends CommandBase {
    
    public CLIMB_Stop() {
        requires(climb);
    }

    protected void initialize() {
    }
    /*
     * Stops motor and locks arm.
     */
    protected void execute() 
    {
        climb.setArmMotorSpeed(0);
        climb.setArmLockOn();
    }
    
    /*
     * Done when the wait for permission button is hit.
     */
    protected boolean isFinished() {
        return(true);
    }

    protected void end() 
    {
    }

    protected void interrupted() 
    {
    }
}
