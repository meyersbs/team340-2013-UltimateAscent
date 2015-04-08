package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Adam Audycki
 */
public class AUTO_Autonomous extends CommandBase {
    
    //CommandGroup autoMid;
    //CommandGroup autoHi;
    CommandGroup autoMidTimed;
    CommandGroup autoHiTimed;
    CommandGroup autoNewMid;
    CommandGroup autoNewHigh;
    public AUTO_Autonomous() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(noSub);

        //autoMid = new CG_AutonomousMid();
        //autoHi = new CG_AutonomousHi();
        autoMidTimed = new CG_AutonomousTimedMid();
        autoHiTimed = new CG_AutonomousTimedHi();
        
        autoNewMid = new CG_AUTO_2POINT();
        autoNewHigh = new CG_AUTO_3POINT();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
       
        if(noSub.autoState == 0)
        {
            
        }
        else if(noSub.autoState == 1)
        {
            autoNewMid.start();
        }
        else if(noSub.autoState == 2)
        {
            //autoHiTimed.start();
        }
        
        else if(noSub.autoState ==3)
        {
            autoMidTimed.start();
        }
        
        else if(noSub.autoState == 4)
        {
            autoNewHigh.start();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
