/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author meyersbs
 */
public class CG_FinishClimbing_OldShooter extends CommandGroup {
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
    
    public CG_FinishClimbing_OldShooter() {
        addSequential(new CLIMB_GoAtLeastHere(-1.0, PUT_WEIGHT_ON_UPPER_CLAW, 2), 2.0);    
        addSequential(new WT_ShiftWeightForward());

        addSequential(new CLIMB_GoAtLeastHere(-1.0, CLEAR_LOWER_BAR_POS, 2), GET_OVER_BUMPER_DELAY);
        addSequential(new CLIMB_ClearBarWeightBack(-1.0, 2), 4.0);
        
        //addSequential(new CLIMB_WaitPermission());
        //addSequential(new CLIMB_GoAtMostHere(1.0, 325, 3));
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
