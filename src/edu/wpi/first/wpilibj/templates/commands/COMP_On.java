package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author CJ
 */
public class COMP_On extends CommandBase 
{
    
    public COMP_On() 
    {
        requires(comp);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
        if(comp.isSwitchOn()){
            comp.compressorOff();
        }else{
            comp.compressorOn();
        }
        
        //System.out.println("Comp_On Command Called." + comp.isCompressorRunning());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
        
    }
}
