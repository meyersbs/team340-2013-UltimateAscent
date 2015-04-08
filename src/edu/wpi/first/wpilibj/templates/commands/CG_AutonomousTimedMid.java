/**
 * 
 * @author Adam Audycki
 */
package edu.wpi.first.wpilibj.templates.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.templates.subsystems.Drive;
import edu.wpi.first.wpilibj.templates.subsystems.Score;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * AutonomousDistanceGo(speed,direction,time to go,ultrasonic)
 */
public class CG_AutonomousTimedMid extends CommandGroup 
{
    /**
     *
     */
    public CG_AutonomousTimedMid() 
    {
        addSequential(new WT_ResetGyro());
        addParallel(new WT_ShiftWeightBackward());
        addParallel(new CLIMB_GoToBottomLimit());
        //addSequential(new AUTO_DistanceGo(-0.6, 0, 4, 33));
        addSequential(new AUTO_TimedGo(-0.6,0,5));
        addSequential(new AUTO_TimedGo(-.3, 0, 2));
        addSequential(new SHOOT_SetMedGoal(), 2);
        addSequential(new SHOOT_FireOnce());
        addSequential(new SHOOT_FireOnce());
        addSequential(new SHOOT_FireOnce());
        addSequential(new SHOOT_AllStop());
        
        /*autoState = SmartDashboard.getNumber("AutoState", 0); //Returns number 0 - 4 given by Dashboard
        
        //Auto state 1. On right side of pyrimid and go score in the 2 point goal.
        if(autoState < 0 || autoState > 4)
        {
            autoState = 0;
        }
        
        if(autoState == 0)
        {
            //DO NOTHING
        }
        
        else if(autoState == 1)
        {
            addSequential(new DRIVE_ShiftLow());
            addSequential(new AUTO_DistanceGo(1,0,1,1));//Go
            addSequential(new SHOOT_SetMedGoal());//Set High Goal
            addSequential(new SHOOT_RapidFire());//Shoot
            addSequential(new AUTO_DistanceGo(-1,0,1,1));//Back Up
            addSequential(new AUTO_DistanceGo(1,1,1,1));//Turn
            addSequential(new AUTO_DistanceGo(1,0,1,1));//Go
            addSequential(new AUTO_DistanceGo(1,0,1,1));//Go
            addSequential(new AUTO_DistanceGo(1,1,1,1));//Turn
            addSequential(new AUTO_DistanceGo(1,0,1,1));//Go
        }
        
        //Auto state 2. On left side of pyrimid and go score in the 2 point goal.
        else if(autoState == 2)
        {
            addSequential(new DRIVE_ShiftLow());
            addSequential(new AUTO_DistanceGo(1,0,1,1));//Go
            addSequential(new SHOOT_SetMedGoal());//Set High Goal
            addSequential(new SHOOT_RapidFire());//Shoot
            addSequential(new AUTO_DistanceGo(-1,0,1,1));//Back Up
            addSequential(new AUTO_DistanceGo(1,1,1,1));//Turn
            addSequential(new AUTO_DistanceGo(1,0,1,1));//Go
            addSequential(new AUTO_DistanceGo(1,1,1,1));//Turn
            addSequential(new AUTO_DistanceGo(1,0,1,1));//Go
            addSequential(new AUTO_DistanceGo(1,1,1,1));//Turn
            addSequential(new AUTO_DistanceGo(1,0,1,1));//Go
            addSequential(new AUTO_DistanceGo(1,1,1,1));//Turn
            addSequential(new AUTO_DistanceGo(1,0,1,1));//Go
        }
        
        //Auto state 3. On right side of pyrimid and go score in the 3 point goal.
        else if(autoState == 3)
        {
            addSequential(new DRIVE_ShiftLow());
            addSequential(new AUTO_DistanceGo(1,0,1,1));//Go
            addSequential(new AUTO_DistanceGo(1,1,1,1));//Turn
            addSequential(new AUTO_DistanceGo(1,0,1,1));//Go
            addSequential(new AUTO_DistanceGo(1,1,1,1));//Turn
            addSequential(new SHOOT_SetHighGoal());//Set High Goal
            addSequential(new SHOOT_RapidFire());//Shoot
            addSequential(new AUTO_DistanceGo(-1,0,1,1));//Back Up
            addSequential(new AUTO_DistanceGo(1,1,1,1));//Turn
            addSequential(new AUTO_DistanceGo(1,0,1,1));//Go
            addSequential(new AUTO_DistanceGo(1,1,1,1));//Turn
            addSequential(new AUTO_DistanceGo(1,0,1,1));//Go
        }
        
        //Auto state 4. On left side of pyrimid and go score in the 3 point goal.
        else if(autoState == 4)
        {
            addSequential(new DRIVE_ShiftLow());
            addSequential(new AUTO_DistanceGo(1,0,1,1));//Go
            addSequential(new AUTO_DistanceGo(1,1,1,1));//Turn
            addSequential(new AUTO_DistanceGo(1,0,1,1));//Go
            addSequential(new AUTO_DistanceGo(1,1,1,1));//Turn
            addSequential(new SHOOT_SetHighGoal());//Set High Goal
            addSequential(new SHOOT_RapidFire());//Shoot
            addSequential(new AUTO_DistanceGo(-1,0,1,1));//Back Up
            addSequential(new AUTO_DistanceGo(1,1,1,1));//Turn
            addSequential(new AUTO_DistanceGo(1,0,1,1));//Go
            addSequential(new AUTO_DistanceGo(1,1,1,1));//Turn
            addSequential(new AUTO_DistanceGo(1,0,1,1));//Go
            addSequential(new AUTO_DistanceGo(1,1,1,1));//Turn
            addSequential(new AUTO_DistanceGo(1,0,1,1));//Go
            addSequential(new AUTO_DistanceGo(1,1,1,1));//Turn
            addSequential(new AUTO_DistanceGo(1,0,1,1));//Go
        }
        else
        {
            //DO NOTHING
        }*/
    }
    
    
}
