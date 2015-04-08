package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.subsystems.Shoot;
/**
 *
 * @author Ben Meyers
 */
public class CG_ClimbAllTheWay extends CommandGroup {
    public static final int ANGLE_BEFORE_SWING = 41; //should be 41
    public static final int APPROACH_HEIGHT = 225;
    public static final double GET_OVER_BUMPER_DELAY = 10.0;
    private static final double TOWARDS_TOP_LIMIT = -0.25;
    private static final double TOWARDS_BOT_LIMIT = 0.25;
    private double barContactAngle = 0;//TODO: Find actual value
    private double scoreFromLevel2Speed = 999999;
    private double scoreFromLevel3Speed = 999999;
    private final double HAND_OFF_POINT = 401;
    private final double PUT_WEIGHT_ON_UPPER_CLAW = 75; //TODO: Find value
    private final double CLEAR_LOWER_BAR_POS = 375;
    private final double CLIMB_SCORE_POS = 999999; //TODO: Find value
    private final double TOP_HOOK_LOCKED_POS = 50; //TODO: Find value
    
    /**
     * Constructor. This command group handles all of the climbing logic.
     */
    public CG_ClimbAllTheWay() 
    {
/**********INITIAL REQUIREMENTS**********/
        addSequential(new WT_ResetGyro());
        addParallel(new SHOOT_SetPymGoalShootOnPym());
        addSequential(new WT_ShiftWeightBackward());
        addSequential(new CLIMB_GoToBottomLimit());
        addParallel(new SHOOT_FeedBackward());
        addSequential(new CLIMB_GoAtLeastHere(-1.0, APPROACH_HEIGHT, 2), 2.0);             
        addSequential(new CLIMB_WaitPermission());
        
/**********CLIMB ONE LEVEL**********/
        addSequential(new CLIMB_GoToTopLimit());
        addSequential(new CLIMB_WaitPermission());
        addSequential(new CLIMB_GoToBottomLimit());
        addSequential(new NOTHING(), 1.0);
        addSequential(new CLIMB_GoToLockPos(TOP_HOOK_LOCKED_POS, 2), 2.0);
    
/***********PUT WEIGHT ON UPPER CLAW***********/          
        addSequential(new CLIMB_WaitPermission());     
        addSequential(new CLIMB_GoAtLeastHere(-1.0, PUT_WEIGHT_ON_UPPER_CLAW, 2), 2.0);             
        addSequential(new WT_ShiftWeightForward());
        
/**********CLEAR BUMPER**********/        
        addSequential(new CLIMB_GoAtLeastHere(-1.0, CLEAR_LOWER_BAR_POS, 2), GET_OVER_BUMPER_DELAY);                
        addSequential(new CLIMB_ClearBarWeightBack(-1.0, 2), 4.0);
       
/**********HANG IN LEVEL 2 & 3**********/
        addSequential(new CLIMB_WaitPermission());
        
/**********CLIMB TO LEVEL 3**********/
        addSequential(new WT_ShiftWeightBackward());
        addSequential(new CLIMB_GoToBottomLimit());
        addSequential(new NOTHING(), 1.0);
/**********SECURED ON TOP BAR**********/
        addSequential(new CLIMB_GoToLockPos(TOP_HOOK_LOCKED_POS, 2), 2.0);

        addSequential(new CLIMB_WaitPermission()); //Was waitforpermission
//        
        addSequential(new CLIMB_GoAtLeastHere(-1.0, PUT_WEIGHT_ON_UPPER_CLAW, 2), 2.0);    
        addSequential(new WT_ShiftWeightForward());
//
        addSequential(new CLIMB_GoAtLeastHere(-1.0, CLEAR_LOWER_BAR_POS, 2), GET_OVER_BUMPER_DELAY);
        addSequential(new CLIMB_ClearBarWeightBack(-1.0, 2), 4.0);
        
        addSequential(new Shoot_ShooterSpeed(RobotMap.pymShooterVoltage));
        addSequential(new Shoot_PropellerOn(.5));
    }
}
