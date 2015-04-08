package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author meyersbs
 */
public class CG_AUTO_2POINT extends CommandGroup {
    
    public CG_AUTO_2POINT() {
        addSequential(new WT_ResetGyro());
        addParallel(new WT_ShiftWeightBackward());
        addParallel(new CLIMB_GoToBottomLimit());
        addSequential(new AUTO_DriveUntilTime(-0.6, 7)); //Speed, Time
        addSequential(new SHOOT_SetMedGoal(), 2);
        addSequential(new SHOOT_FireOnceForAuto());
        addSequential(new NOTHING(), .5);
        addSequential(new SHOOT_SetMedGoal(), 2);
        addSequential(new SHOOT_FireOnceForAuto());
        addSequential(new NOTHING(), .5);
        addSequential(new SHOOT_SetMedGoal(), 2);
        addSequential(new SHOOT_FireOnceForAuto());
        addSequential(new SHOOT_AllStop());
        addSequential(new SHOOT_FireIn());
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
