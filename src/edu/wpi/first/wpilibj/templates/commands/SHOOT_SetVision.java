//TODO: Change smartdashboard stuff,include camera and ultrasonic.
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *Sets the motor speed to the distance with MATH. This command doesn't work yet with MATH.
 * @param 
 * @return
 * 
 * @author CJ Atene
 */
public class SHOOT_SetVision extends CommandBase 
{
        double u1;
        double u2;
        double u3;
        double u4;
        double u5;
        double umean;
        
        double v1;
        double v2; 
        double v3;
        double v4;
        double v5;
        double vmean;
    
    /**
     *
     */
    public SHOOT_SetVision() 
    {
        // Use requires() here to declare subsystem dependencies
        requires(score);
    }

    // Called just before this Command runs the first time
    /**
     *
     */
    protected void initialize() 
    {
         if(!score.isSpeedControl())
        {
            score.startSpeedControl();
        }
         u1 = score.getUltraDistance();
         u2 = -1;
         u3 = -1;
         u4 = -1;
         u5 = -1;
         
         v1 = score.getUltraDistance(); //TODO:
         v2 = -1;
         v3 = -1;
         v4 = -1;
         v5 = -1;
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     *Finds Distance and location of goal and determines speed with MAGIC
     * @param 
     * @return
     * 
     * @author CJ Atene
     */
    protected void execute() 
    {
        u5 = u4;
        u4 = u3;
        u3 = u2;
        u2 = u1;
        u1 = score.getUltraDistance();
        if(u5 != -1)
        {
           umean = (u1 + u2 + u3 + u4 + u5) / 5;
        }
        
        v5 = u4;
        v4 = u3;
        v3 = u2;
        v2 = u1;
        v1 = score.getUltraDistance();
        if(v5 != -1)
        {
            vmean = (v1 + v2 + v3 + v4 + v5) / 5;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     * Never Finishes
     * @return boolean false
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