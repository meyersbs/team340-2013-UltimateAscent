package edu.wpi.first.wpilibj.templates.commands;
/**
 *
 * @author Adam
 * Shifts weight forward
 */
public class WT_ShiftWeightForward extends CommandBase {
    
    public WT_ShiftWeightForward() 
    {
        requires(weightTransferPID);
    }

    protected void initialize() 
    {
        
    }
    
    /**
     * moves forward
     */
    protected void execute() {
        System.out.println("ShiftWeightForward :" + weightTransferPID.getWeightForwardLimit());
        weightTransferPID.moveForward();
    }
    
    /**
     * 
     * @return boolean when front limit switch is pressed.
     */
    protected boolean isFinished() {
        return(weightTransferPID.getWeightForwardLimit());
    }
    
    /**
     *stops motor. 
     */
    protected void end() {
        weightTransferPID.stopMove();
        System.out.println("Weight Done Shifting Forewards");
    }

    /**
     * Stops motor.
     */
    protected void interrupted() {
        weightTransferPID.stopMove();
    }
}